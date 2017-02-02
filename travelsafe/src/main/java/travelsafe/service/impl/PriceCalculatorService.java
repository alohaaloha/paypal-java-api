package travelsafe.service.impl;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelsafe.model.InsuranceRebate;
import travelsafe.model.Item;
import travelsafe.model.Price;
import travelsafe.model.TravelInsurance;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
  * Created by Dražen on 16.12.2016..
  */
@Service
@Transactional
public class PriceCalculatorService {

    private static KnowledgeBase knowledgeBase;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private InsuranceRebateService insuranceRebateService;

    public void calculatePrice(TravelInsurance travelInsurance) throws Exception {
        Date currentDate = new Date();

        if (knowledgeBase == null) {
            initializeKnowledgeBase();
        }

        StatefulKnowledgeSession knowledgeSession = knowledgeBase.newStatefulKnowledgeSession();

        List<Item> items = itemService.getActual();
        List<Price> prices = priceService.getActual();
        List<InsuranceRebate> insuranceRebates = insuranceRebateService.getAll();

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

        knowledgeSession.fireAllRules();

        knowledgeSession.dispose();
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