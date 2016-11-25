package travelsafe.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelsafe.model.TravelInsurance;

import java.util.List;

/**
 * Created by Drazen on 25.11.2016..
 */
@RestController
@RequestMapping("/TravelInsurance")
public class TravelInsuranceController {

    @RequestMapping(value = "/TravelInsurances",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TravelInsurance> createTravelInsurance(@RequestBody TravelInsurance travelInsurance) {
        return null;
    }

    @RequestMapping(value = "/TravelInsurances",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TravelInsurance> updateTravelInsurance(@RequestBody TravelInsurance travelInsurance) {
        return null;
    }

    @RequestMapping(value = "/TravelInsurances",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TravelInsurance> getAllTravelInsurances() {
        return null;
    }

    @RequestMapping(value = "/TravelInsurances/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TravelInsurance> getTravelInsurance(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(value = "/TravelInsurances/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTravelInsurance(@PathVariable Long id) {
        return null;
    }

}
