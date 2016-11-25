package travelsafe.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelsafe.model.ParticipantInInsurance;

import java.util.List;

/**
 * Created by Drazen on 25.11.2016..
 */
@RestController
@RequestMapping("/ParticipantInInsurance")
public class ParticipantInInsuranceController {

    @RequestMapping(value = "/ParticipantInInsurances",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParticipantInInsurance> createParticipantInInsurance(@RequestBody ParticipantInInsurance participantInInsurance) {
        return null;
    }

    @RequestMapping(value = "/ParticipantInInsurances",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParticipantInInsurance> updateParticipantInInsurance(@RequestBody ParticipantInInsurance participantInInsurance) {
        return null;
    }

    @RequestMapping(value = "/ParticipantInInsurances",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ParticipantInInsurance> getAllParticipantInInsurances() {
        return null;
    }

    @RequestMapping(value = "/ParticipantInInsurances/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParticipantInInsurance> getParticipantInInsurance(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(value = "/ParticipantInInsurances/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteParticipantInInsurance(@PathVariable Long id) {
        return null;
    }

}
