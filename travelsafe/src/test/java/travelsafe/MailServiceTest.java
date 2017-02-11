package travelsafe;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import travelsafe.model.*;
import travelsafe.service.impl.MailService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dorian on 1/4/2017.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TravelsafeApplication.class)
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendMail(){
        mailService.sendMailWithAttachment(getTI());
    }


    private TravelInsurance getTI(){
        TravelInsurance t = new TravelInsurance();
        t.setDuration(5L);
        t.setMaxAmount(500D);
        t.setNumberOfPeople(5L);
        ParticipantInInsurance pi1 = new ParticipantInInsurance();
        pi1.setName("Milos");
        pi1.setSurname("Savic");
        pi1.setCarrier(false);
        ParticipantInInsurance pi2 = new ParticipantInInsurance();
        pi2.setName("Branislav");
        pi2.setSurname("Cogic");
        pi2.setCarrier(true);
        pi2.setEmail("bane93@gmail.com");
        List<ParticipantInInsurance> pis = new ArrayList<>();
        pis.add(pi1);
        pis.add(pi2);
        t.setParticipantInInsurances(pis);
        Region region = new Region();
        region.setEn_translation("Spanija");
        Item item = new Item();
        item.setName_en("Spanija");
        t.setRegion(item);
        Price price = new Price();
        price.setAmount(55000D);
        t.setPrice(price);

        CarInsurance ci = new CarInsurance();
        ci.setBrand("BMW");
        ci.setChassisNumber("1654AD8Q56PO");
        ci.setOwnersName("Branislav");
        ci.setOwnersSurname("Cogic");
        ci.setRegistrationNumber("NS 012-BC");
        ci.setType("Car");
        ci.setYearOfProduction(2016L);

        List<CarInsurance> cis = new ArrayList<>();
        cis.add(ci);

        t.setCarInsurances(cis);
        t.setHomeInsurances(null);

        return t;
    }



}
