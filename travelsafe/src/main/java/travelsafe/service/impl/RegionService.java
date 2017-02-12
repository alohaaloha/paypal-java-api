package travelsafe.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travelsafe.model.Item;
import travelsafe.model.Region;
import travelsafe.repository.RegionRepository;
import travelsafe.service.GenericService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dorian on 1/4/2017.
 */
@Service
public class RegionService implements GenericService<Region> {

    private static final Logger LOG = LoggerFactory.getLogger(RegionService.class);

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ItemService itemService;

    @Override
    public List<Region> getAll() {
        return null;
    }

    @Override
    public Region save(Region entity) {
        return null;
    }

    public List<String> getRegionsByLang(String lang){
        LOG.debug("Get region by {} language", lang);
        if(lang.equals("en"))
            return regionRepository.getRegionsInEnglish();
        else if(lang.equals("ser"))
            return regionRepository.getRegionsInSerbian();
        else
            return null;
    }

    // TODO: Move this method to ItemService when Region table is removed from database
    public List<Item> getRegionsBySearchCriteria(String searchCriteria, String language) {
        LOG.debug("Fetching regions filtered by search critera " + searchCriteria + "in language " + language);

        List<Item> allRegionsNamesForLanguage = itemService.getActualItemsByTypeOfRiskCode("region_ti", Date.valueOf(LocalDate.now()));
        List<Item> regionsMatchingCriteria = new ArrayList<>();

        allRegionsNamesForLanguage.forEach((regionItem) -> {
            if (language.equals("sr") && regionItem.getName_srb().toLowerCase().contains(searchCriteria.toLowerCase())) {
                regionsMatchingCriteria.add(regionItem);
            } else if (language.equals("en") && regionItem.getName_en().toLowerCase().contains(searchCriteria.toLowerCase()))
                regionsMatchingCriteria.add(regionItem);
        });

        return regionsMatchingCriteria;
    }
}
