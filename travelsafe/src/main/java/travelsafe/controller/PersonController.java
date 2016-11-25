package travelsafe.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Drazen on 25.11.2016..
 */
@RestController
@RequestMapping("/Person")
public class PersonController {

    @RequestMapping(value = "/Persons",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return null;
    }

    @RequestMapping(value = "/Persons",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        return null;
    }

    @RequestMapping(value = "/Persons",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getAllPersons() {
        return null;
    }

    @RequestMapping(value = "/Persons/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(value = "/Persons/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        return null;
    }

}
