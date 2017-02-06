package travelsafe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelsafe.model.Item;
import travelsafe.model.dto.ItemDTO;
import travelsafe.service.impl.ItemService;

import java.util.List;

/**
 * Created by Drazen on 25.11.2016..
 */
@RestController
@RequestMapping("/api")
public class ItemController {

    private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/Items",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return null;
    }

    @RequestMapping(value = "/Items",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        return null;
    }

    @RequestMapping(value = "/Items",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Item> getAllItems() {
        return null;
    }

    @RequestMapping(value = "/ItemsByTypeOfRisk/{lang}/{risk}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDTO>> getItemByTypeOfRisk(@PathVariable String lang, @PathVariable String risk) {

        List<ItemDTO> items = itemService.getItemsByTypeOfRiskByLang(lang,risk);
        // List<ItemDTO> items = itemService.getActualItemsByTypeOfRiskByLang(lang, risk, Date.valueOf(LocalDate.now()));  // TODO Try to use this

        if(items == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<List<ItemDTO>>(items, HttpStatus.OK);

    }

    @RequestMapping(value = "/ItemsByOpt/{lang}/{optional}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDTO>> getItemByOptional(@PathVariable String lang, @PathVariable Long optional) {

        boolean option = false;
        if(optional == 0)
            option = false;
        else if(optional == 1)
            option = true;
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<ItemDTO> items = itemService.getItemsByOptionalByLang(lang,option);
        // List<ItemDTO> items = itemService.getActualItemsByOptionalByLang(lang, option, Date.valueOf(LocalDate.now()));  // TODO Try to use this

        if(items == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<List<ItemDTO>>(items, HttpStatus.OK);

    }

    @RequestMapping(value = "/Items/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        return null;
    }

}
