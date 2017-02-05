package travelsafe;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import travelsafe.model.TypeOfRisk;
import travelsafe.repository.TypeOfRiskRepository;

import java.util.List;

/**
 * Created by Dorian on 2/3/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TravelsafeApplication.class)
public class TypeOfRiskTest {

    @Autowired
    private TypeOfRiskRepository typeOfRiskRepository;


    public void testGetAll(){
        List<TypeOfRisk> risks = typeOfRiskRepository.findAll();


        Assert.assertEquals(3L,risks.get(1).getItems().size());
        Assert.assertEquals(2L, typeOfRiskRepository.findAll().size());
    }

    @Test
    public void testByOptional(){
        List<TypeOfRisk> risks = typeOfRiskRepository.findByOptional(true);
        Assert.assertEquals(2l,risks.size());
    }

}
