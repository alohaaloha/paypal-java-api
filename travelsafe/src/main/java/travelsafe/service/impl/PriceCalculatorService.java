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

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private PriceService priceService;

    @Autowired
    private TypeOfRiskService typeOfRiskService;

    @Autowired
    private ItemService itemService;

    private static void initializeKnowledgeBase() throws Exception {
        LOG.debug("Initializing variable of class KnowledgeBase");

        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("price.drl"), ResourceType.DRL);

        if (knowledgeBuilder.hasErrors()) {
            for (KnowledgeBuilderError error: knowledgeBuilder.getErrors()) {
                LOG.debug("KnowledgeBuilderError: {}", error);

                System.err.println(error);
            }

            throw new Exception("Unsuccessful initialization of price calculator (Instance of class KnowledgeBase).");
        }

        knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
    }

    public Double calculate(TravelInsurance travelInsurance) throws Exception {
        return this.calculate(travelInsurance, null, null, null);
    }

    public Double calculate(TravelInsurance travelInsurance, Integer lt18, Integer btw1865, Integer gt65) throws Exception {
        LOG.debug("Calculating price for travel insurance: {}", travelInsurance);

        if (knowledgeBase == null) {
            initializeKnowledgeBase();
        }

        Double calculatedTotalPrice = null;
        Double oldTotalPrice = travelInsurance.getTotalPrice();
        Date currentDate = Date.valueOf(LocalDate.now());
        List<Price> prices = priceService.getActual(currentDate);
        List<TypeOfRisk> typesOfRisks = typeOfRiskService.getAll();
        List<Item> items = itemService.getActual(currentDate);
        StatefulKnowledgeSession knowledgeSession = knowledgeBase.newStatefulKnowledgeSession();

        // Insert facts
        List<Double> peopleAgeRanges = new ArrayList<>();
        peopleAgeRanges.add((double)lt18);
        peopleAgeRanges.add((double)btw1865);
        peopleAgeRanges.add((double)gt65);
        knowledgeSession.setGlobal("peopleAgeRanges", peopleAgeRanges);
        knowledgeSession.insert(currentDate);
        knowledgeSession.insert(travelInsurance);
        for (Price price : prices) {
            knowledgeSession.insert(price);
        }
        for (TypeOfRisk typeOfRisk : typesOfRisks) {
            knowledgeSession.insert(typeOfRisk);
        }
        for (Item item : items) {
            knowledgeSession.insert(item);
        }
        if (travelInsurance.getParticipantInInsurances() != null) {
            for (ParticipantInInsurance participantInInsurance : travelInsurance.getParticipantInInsurances()) {
                knowledgeSession.insert(participantInInsurance);
            }
        }
        if (travelInsurance.getHomeInsurances() != null) {
            for (HomeInsurance homeInsurance : travelInsurance.getHomeInsurances()) {
                knowledgeSession.insert(homeInsurance);
            }
        }
        if (travelInsurance.getCarInsurances() != null) {
            for (CarInsurance carInsurance : travelInsurance.getCarInsurances()) {
                knowledgeSession.insert(carInsurance);
            }
        }

        // Fire rules
        knowledgeSession.fireAllRules();

        // Dispose
        knowledgeSession.dispose();

        calculatedTotalPrice = BigDecimal.valueOf(travelInsurance.getTotalPrice())
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
        travelInsurance.setTotalPrice(oldTotalPrice);

        return calculatedTotalPrice;
    }

}