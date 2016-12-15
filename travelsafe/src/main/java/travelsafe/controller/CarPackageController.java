package travelsafe.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelsafe.model.CarPackage;

import java.util.List;

/**
 * Created by Drazen on 25.11.2016..
 */
@RestController
@RequestMapping("/api")
public class CarPackageController {

    @RequestMapping(value = "/CarPackages",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarPackage> createCarPackage(@RequestBody CarPackage carPackage) {
        return null;
    }

    @RequestMapping(value = "/CarPackages",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarPackage> updateCarPackage(@RequestBody CarPackage carPackage) {
        return null;
    }

    @RequestMapping(value = "/CarPackages",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CarPackage> getAllCarPackages() {
        return null;
    }

    @RequestMapping(value = "/CarPackages/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarPackage> getCarPackage(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(value = "/CarPackages/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCarPackage(@PathVariable Long id) {
        return null;
    }

}
