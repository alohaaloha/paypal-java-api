package travelsafe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import travelsafe.service.impl.MailService;

/**
 * Created by Dorian on 1/4/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TravelsafeApplication.class)
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendMail(){
        mailService.sendMail("bane93@gmail.com","Dee si cogara, upravo ti saljem mail iz MailServiceTest klase!");
    }

}
