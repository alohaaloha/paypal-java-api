package travelsafe.service.impl;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.*;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelsafe.model.TravelInsurance;

/**
 * Created by Drazen on 15.12.2016..
 */
@Service
@Transactional
public class PriceCalculatorService {

    private static KnowledgeBase knowledgeBase;

    static {
       knowledgeBase = readKnowledgeBase();
    }

    public Double calculatePrice(TravelInsurance travelInsurance) {
        StatefulKnowledgeSession statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();

        statefulKnowledgeSession.insert(travelInsurance);

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
