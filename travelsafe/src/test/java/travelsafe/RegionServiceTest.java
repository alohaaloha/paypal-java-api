package travelsafe;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import travelsafe.service.impl.RegionService;

/**
 * Created by Dorian on 1/4/2017.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TravelsafeApplication.class)
public class RegionServiceTest {

    @Autowired
    private RegionService regionService;

    @Test
    public void testRegionInEnglish(){
        Assert.assertNotNull(regionService.getRegionsByLang("en"));
        Assert.assertEquals(5, regionService.getRegionsByLang("en").size());
    }

    @Test
    public void testRegionInSerbian(){
        Assert.assertNotNull(regionService.getRegionsByLang("ser"));
        Assert.assertEquals(5, regionService.getRegionsByLang("ser").size());
    }

}
