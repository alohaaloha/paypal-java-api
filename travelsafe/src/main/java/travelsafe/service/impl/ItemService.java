package travelsafe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travelsafe.model.Item;
import travelsafe.repository.ItemRepository;
import travelsafe.service.GenericService;

import java.sql.Date;
import java.util.List;

/**
 * Created by aloha on 24-Nov-16.
 */
@Service
public class ItemService implements GenericService<Item> {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public List<Item> getActual(Date date) {
        return itemRepository.getActual(date);
    }

    @Override
    public Item save(Item entity) {
        return null;
    }

}
