package travelsafe.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travelsafe.model.Item;
import travelsafe.model.dto.ItemDTO;
import travelsafe.repository.ItemRepository;
import travelsafe.service.GenericService;

import java.sql.Date;
import java.util.List;

/**
 * Created by aloha on 24-Nov-16.
 */
@Service
public class ItemService implements GenericService<Item> {

    private static final Logger LOG = LoggerFactory.getLogger(ItemService.class);

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

    public List<ItemDTO> getItemsByTypeOfRiskByLang(String lang, String typeOfRisk){
        LOG.debug("Get item by {} language and {} type of risk", lang, typeOfRisk);
        if (lang.equals("en"))
            return itemRepository.getItemByTypeOfRiskEN(typeOfRisk);
        else if (lang.equals("ser"))
            return itemRepository.getItemByTypeOfRiskSRB(typeOfRisk);
        else
            return null;
    }

    public List<ItemDTO> getItemsByOptionalByLang(String lang, boolean optional){
        LOG.debug("Get item by {} language and {} optional", lang, optional);
        if(lang.equals("en"))
            return itemRepository.getItemByOptionalEN(optional);
        else if(lang.equals("ser"))
            return itemRepository.getItemByOptionalSRB(optional);
        else
            return null;
    }

}
