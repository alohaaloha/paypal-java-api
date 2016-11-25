package travelsafe;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import travelsafe.model.Item;
import travelsafe.repository.ItemRepository;
import travelsafe.service.impl.ItemService;

import java.util.List;

/**
 * Created by Dorian on 11/22/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TravelsafeApplication.class)
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void testFindAll(){
        List<Item> items = itemService.getAll();
        Assert.assertEquals(4,items.size());
    }

}
