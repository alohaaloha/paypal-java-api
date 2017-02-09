package travelsafe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelsafe.model.TypeOfRisk;
import travelsafe.service.impl.TypeOfRiskService;

import java.util.List;

/**
 * Created by Drazen on 25.11.2016..
 */
@RestController
@RequestMapping("/api")
public class TypeOfRiskController {

    private static final Logger LOG = LoggerFactory.getLogger(TypeOfRiskController.class);

    @Autowired
    private TypeOfRiskService typeOfRiskService;

    @RequestMapping(value = "/TypeOfRisks/{optional}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TypeOfRisk>> getByOptional(@PathVariable Long optional) {

        boolean option = false;
        if(optional == 0)
            option = false;
        else if(optional == 1)
            option = true;
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<TypeOfRisk> typeOfRisks = typeOfRiskService.getByOptional(option);

        if(typeOfRisks == null)
            return new ResponseEntity<List<TypeOfRisk>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<TypeOfRisk>>(typeOfRisks,HttpStatus.OK);
    }

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
    public ResponseEntity<List<TypeOfRisk>> getAllTypeOfRisks() {

        List<TypeOfRisk> typeOfRisks = typeOfRiskService.getAll();

        if(typeOfRisks == null)
            return new ResponseEntity<List<TypeOfRisk>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<TypeOfRisk>>(typeOfRisks,HttpStatus.OK);
    }

    @RequestMapping(value = "/TypeOfRisks/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTypeOfRisk(@PathVariable Long id) {
        return null;
    }

}
