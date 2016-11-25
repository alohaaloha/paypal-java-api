package travelsafe.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelsafe.model.Price;

import java.util.List;

/**
 * Created by Drazen on 25.11.2016..
 */
@RestController
@RequestMapping("/Price")
public class PriceController {

    @RequestMapping(value = "/Prices",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Price> createPrice(@RequestBody Price price) {
        return null;
    }

    @RequestMapping(value = "/Prices",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Price> updatePrice(@RequestBody Price price) {
        return null;
    }

    @RequestMapping(value = "/Prices",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Price> getAllPrices() {
        return null;
    }

    @RequestMapping(value = "/Prices/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Price> getPrice(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(value = "/Prices/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletePrice(@PathVariable Long id) {
        return null;
    }

}
