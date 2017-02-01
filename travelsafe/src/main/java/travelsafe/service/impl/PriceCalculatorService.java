package travelsafe.service.impl;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.*;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelsafe.model.Item;
import travelsafe.model.TravelInsurance;
import travelsafe.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Drazen on 15.12.2016..
 */
@Service
@Transactional
public class PriceCalculatorService {

    private static final Logger LOG = LoggerFactory.getLogger(PriceCalculatorService.class);

    private static KnowledgeBase knowledgeBase;

    @Autowired
    private ItemRepository itemRepository;

    static {
       knowledgeBase = readKnowledgeBase();
    }

    public Double calculatePrice(TravelInsurance travelInsurance) {

        LOG.debug("Calculating price for travel insurance with {} ID", travelInsurance.getId());

        StatefulKnowledgeSession statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();

        statefulKnowledgeSession.insert(travelInsurance);
        
        /*
        List<Item> items = itemRepository.findAll();
        statefulKnowledgeSession.insert(items);
        */

        statefulKnowledgeSession.fireAllRules();

        return travelInsurance.getAmount();
    }

    public static KnowledgeBase readKnowledgeBase() {
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        knowledgeBuilder.add(ResourceFactory.newClassPathResource("price.drl"), ResourceType.DRL);

        KnowledgeBuilderErrors errors = knowledgeBuilder.getErrors();

        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }

            return null;
        }

        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();

        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());

        return knowledgeBase;
    }

}
