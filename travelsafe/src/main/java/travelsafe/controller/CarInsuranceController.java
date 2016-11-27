package travelsafe.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelsafe.model.CarInsurance;

import java.util.List;

/**
 * Created by Drazen on 25.11.2016..
 */
@RestController
@RequestMapping("/api")
public class CarInsuranceController {

    @RequestMapping(value = "/CarInsurances",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarInsurance> createCarInsurance(@RequestBody CarInsurance carInsurance) {
        return null;
    }

    @RequestMapping(value = "/CarInsurances",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarInsurance> updateCarInsurance(@RequestBody CarInsurance carInsurance) {
       return null;
    }

    @RequestMapping(value = "/CarInsurances",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CarInsurance> getAllCarInsurances() {
        return null;
    }

    @RequestMapping(value = "/CarInsurances/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarInsurance> getCarInsurance(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(value = "/CarInsurances/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCarInsurance(@PathVariable Long id) {
        return null;
    }

}
