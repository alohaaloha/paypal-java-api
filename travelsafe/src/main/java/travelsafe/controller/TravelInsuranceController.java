package travelsafe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelsafe.model.TravelInsurance;
import travelsafe.repository.TravelInsuranceRepository;
import travelsafe.service.impl.PriceCalculatorService;

import java.util.List;

/**
 * Created by Drazen on 25.11.2016..
 */
@RestController
@RequestMapping("/api")
public class TravelInsuranceController {

    private static final Logger LOG = LoggerFactory.getLogger(TravelInsuranceController.class);

    @Autowired
    TravelInsuranceRepository travelInsuranceRepository;

    @Autowired
    PriceCalculatorService priceCalculatorService;

    @RequestMapping(value = "/TravelInsurances",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTravelInsurance(@RequestBody TravelInsurance travelInsurance) {
        System.out.println(" USAO U REST POST METOD ZA TRAVEL INSURANCE");
        System.out.println(travelInsurance);
        return ResponseEntity.ok().build();
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
    public ResponseEntity getTravelInsurance(@PathVariable Long id) {
        TravelInsurance travelInsurance = travelInsuranceRepository.findOne(id);
        return new ResponseEntity<>(travelInsurance, HttpStatus.OK);
    }

    @RequestMapping(value = "/TravelInsurances/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTravelInsurance(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(value = "/TravelInsurances/price",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getPriceForTravelInsurance(@RequestBody TravelInsurance travelInsurance) {
        try {
            return new ResponseEntity<Double>(priceCalculatorService.calculatePrice(travelInsurance), HttpStatus.OK);
        } catch (Exception e) {
            LOG.debug("Exception occured when calculating price.");
            return new ResponseEntity<Double>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
