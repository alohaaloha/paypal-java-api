package travelsafe.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelsafe.model.InsuranceRebate;

import java.util.List;

/**
 * Created by Drazen on 25.11.2016..
 */
@RestController
@RequestMapping("/InsuranceRebate")
public class InsuranceRebateController {

    @RequestMapping(value = "/InsuranceRebates",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InsuranceRebate> createInsuranceRebate(@RequestBody InsuranceRebate insuranceRebate) {
        return null;
    }

    @RequestMapping(value = "/InsuranceRebates",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InsuranceRebate> updateInsuranceRebate(@RequestBody InsuranceRebate insuranceRebate) {
        return null;
    }

    @RequestMapping(value = "/InsuranceRebates",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InsuranceRebate> getAllInsuranceRebates() {
        return null;
    }

    @RequestMapping(value = "/InsuranceRebates/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InsuranceRebate> getInsuranceRebate(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(value = "/InsuranceRebates/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteInsuranceRebate(@PathVariable Long id) {
        return null;
    }

}
