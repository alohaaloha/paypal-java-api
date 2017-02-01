package travelsafe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelsafe.model.TypeOfRisk;

import java.util.List;

/**
 * Created by Drazen on 25.11.2016..
 */
@RestController
@RequestMapping("/api")
public class TypeOfRiskController {

    private static final Logger LOG = LoggerFactory.getLogger(TypeOfRiskController.class);

    @RequestMapping(value = "/TypeOfRisks",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeOfRisk> createTypeOfRisk(@RequestBody TypeOfRisk typeOfRisk) {
        return null;
    }

    @RequestMapping(value = "/TypeOfRisks",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeOfRisk> updateTypeOfRisk(@RequestBody TypeOfRisk typeOfRisk) {
        return null;
    }

    @RequestMapping(value = "/TypeOfRisks",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TypeOfRisk> getAllTypeOfRisks() {
        return null;
    }

    @RequestMapping(value = "/TypeOfRisks/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeOfRisk> getTypeOfRisk(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(value = "/TypeOfRisks/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTypeOfRisk(@PathVariable Long id) {
        return null;
    }

}
