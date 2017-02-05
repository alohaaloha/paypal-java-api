package travelsafe.service.impl;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelsafe.model.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
  * Created by Dražen on 16.12.2016..
  */
@Service
@Transactional
public class PriceCalculatorService {

    private static final Logger LOG = LoggerFactory.getLogger(PriceCalculatorService.class);

    private static KnowledgeBase knowledgeBase;

    @Autowired
    private TypeOfRiskService typeOfRiskService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private InsuranceRebateService insuranceRebateService;

    public Double calculatePrice(TravelInsurance travelInsurance) throws Exception {
        LOG.debug("Calculating price for travel insurance: " + travelInsurance);
        Double calculatedTotalPrice;
        Double oldTotalPrice = travelInsurance.getTotalPrice();
        Date currentDate = Date.valueOf(LocalDate.now());

        if (knowledgeBase == null) {
            initializeKnowledgeBase();
        }

        StatefulKnowledgeSession knowledgeSession = knowledgeBase.newStatefulKnowledgeSession();

        List<TypeOfRisk> typesOfRisk = typeOfRiskService.getAll();
        List<Item> items = itemService.getActual(currentDate);
        List<Price> prices = priceService.getActual(currentDate);
        List<InsuranceRebate> insuranceRebates = insuranceRebateService.getActual(currentDate);

        for (TypeOfRisk typeOfRisk : typesOfRisk) {
            knowledgeSession.insert(typeOfRisk);
        }
        for (Item item : items) {
            knowledgeSession.insert(item);
        }
        for (Price price: prices) {
            knowledgeSession.insert(price);
        }
        for (InsuranceRebate insuranceRebate : insuranceRebates) {
            knowledgeSession.insert(insuranceRebate);
        }
        knowledgeSession.insert(travelInsurance);
        if (travelInsurance.getHomeInsurances() != null) {
            for (HomeInsurance homeInsurance : travelInsurance.getHomeInsurances()) {
                knowledgeSession.insert(homeInsurance);
            }
        }
        if (travelInsurance.getCarInsurances() != null) {
            for (CarInsurance carInsurance : travelInsurance.getCarInsurances()) {
                knowledgeSession.insert(carInsurance);

                for (CarPackage carPackage : carInsurance.getCarPackages()) {
                    knowledgeSession.insert(carPackage);
                }
            }
        }

        knowledgeSession.fireAllRules();

        knowledgeSession.dispose();

        calculatedTotalPrice = travelInsurance.getTotalPrice();
        travelInsurance.setTotalPrice(oldTotalPrice);

        return calculatedTotalPrice;
    }

    private static void initializeKnowledgeBase() throws Exception {
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("price.drl"), ResourceType.DRL);

        if (knowledgeBuilder.hasErrors()) {
            for (KnowledgeBuilderError error: knowledgeBuilder.getErrors()) {
                System.err.println(error);
            }

            throw new Exception("Unsuccessful initialization of price calculator.");
        }

        knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
    }

}