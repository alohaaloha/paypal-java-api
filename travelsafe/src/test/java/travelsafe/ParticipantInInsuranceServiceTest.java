package travelsafe;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import travelsafe.service.impl.ParticipantInInsuranceService;

/**
 * Created by Dorian on 2/3/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TravelsafeApplication.class)
public class ParticipantInInsuranceServiceTest {

    @Autowired
    private ParticipantInInsuranceService service;



}
