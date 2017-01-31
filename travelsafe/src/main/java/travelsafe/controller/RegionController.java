package travelsafe.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.net.www.http.HttpClient;
import travelsafe.service.impl.RegionService;

import java.util.List;

/**
 * Created by Dorian on 1/4/2017.
 */

@RestController
@RequestMapping(value = "api/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @RequestMapping(value = "/{lang}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getRegions(@PathVariable String lang) {
        List<String> regions = regionService.getRegionsByLang(lang);
        if(regions == null)
            return new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<List<String>>(regions, HttpStatus.OK);
    }

}
