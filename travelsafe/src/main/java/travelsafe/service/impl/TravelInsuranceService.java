package travelsafe.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelsafe.model.TravelInsurance;
import travelsafe.repository.TravelInsuranceRepository;
import travelsafe.service.GenericService;

import java.util.List;

/**
 * Created by Dorian on 11/25/2016.
 */
@Service
@Transactional
public class TravelInsuranceService implements GenericService<TravelInsurance> {

    @Autowired
    private TravelInsuranceRepository travelInsuranceRepository;

    @Override
    public List<TravelInsurance> getAll() {
        return travelInsuranceRepository.findAll();
    }

    @Override
    public TravelInsurance save(TravelInsurance entity) {
         return travelInsuranceRepository.save(entity);
    }

}
