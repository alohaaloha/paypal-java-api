package travelsafe.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelsafe.model.HomeInsurance;

import java.util.List;

/**
 * Created by Drazen on 25.11.2016..
 */
@RestController
@RequestMapping("/api")
public class HomeInsuranceController {



    @RequestMapping(value = "/HomeInsurances",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HomeInsurance> createHomeInsurance(@RequestBody HomeInsurance homeInsurance) {
        return null;
    }

    @RequestMapping(value = "/HomeInsurances",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HomeInsurance> updateHomeInsurance(@RequestBody HomeInsurance homeInsurance) {
        return null;
    }

    @RequestMapping(value = "/HomeInsurances",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HomeInsurance> getAllHomeInsurances() {
        return null;
    }

    @RequestMapping(value = "/HomeInsurances/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HomeInsurance> getHomeInsurance(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(value = "/HomeInsurances/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteHomeInsurance(@PathVariable Long id) {
        return null;
    }

}
