package travelsafe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travelsafe.model.Region;
import travelsafe.repository.RegionRepository;
import travelsafe.service.GenericService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dorian on 1/4/2017.
 */
@Service
public class RegionService implements GenericService<Region> {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public List<Region> getAll() {
        return null;
    }

    @Override
    public Region save(Region entity) {
        return null;
    }

    public List<String> getRegionsByLang(String lang){
        if(lang.equals("en"))
            return regionRepository.getRegionsInEnglish();
        else if(lang.equals("ser"))
            return regionRepository.getRegionsInSerbian();
        else
            return null;
    }
}
