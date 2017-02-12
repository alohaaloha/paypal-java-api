package travelsafe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelsafe.model.Item;
import travelsafe.service.impl.ItemService;
import travelsafe.service.impl.RegionService;

import java.util.List;

/**
 * Created by Dorian on 1/4/2017.
 */

@RestController
@RequestMapping(value = "/api")
public class RegionController {

    private static final Logger LOG = LoggerFactory.getLogger(RegionController.class);

    @Autowired
    private RegionService regionService;

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/regions/{lang}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getRegions(@PathVariable String lang) {
        LOG.debug("Getting regions by {} language", lang);
        List<String> regions = regionService.getRegionsByLang(lang);
        if(regions == null)
            return new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<List<String>>(regions, HttpStatus.OK);
    }

    /**
     * GET  /regions/search -> get regions with search filtered by region name in given language
     */
    @RequestMapping(value = "/regions/search",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getUserBySearchFilter(@RequestParam String searchCriteria,
                                                            @RequestParam String language) {
        LOG.debug("REST request to get Regions filtered by searchCriteria: " + searchCriteria);
        List<Item> regions = regionService.getRegionsBySearchCriteria(searchCriteria, language);
        return new ResponseEntity<List<Item>>(regions, HttpStatus.OK);
    }
}
