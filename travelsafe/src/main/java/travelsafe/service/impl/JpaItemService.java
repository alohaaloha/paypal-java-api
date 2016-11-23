package travelsafe.service.impl;

import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelsafe.model.Item;
import travelsafe.repository.ItemRepository;
import travelsafe.service.ItemService;

import java.util.List;

/**
 * Created by Dorian on 11/22/2016.
 */
@Service
@Transactional
public class JpaItemService implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }
}
