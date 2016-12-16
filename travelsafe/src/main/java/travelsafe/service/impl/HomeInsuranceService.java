package travelsafe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelsafe.model.HomeInsurance;
import travelsafe.repository.HomeInsuranceRepository;
import travelsafe.service.GenericService;

import java.util.List;

/**
 * Created by Dorian on 11/25/2016.
 */
@Service
@Transactional
public class HomeInsuranceService implements GenericService<HomeInsurance> {

    @Autowired
    private HomeInsuranceRepository homeInsuranceRepository;

    @Override
    public List<HomeInsurance> getAll() {
        return homeInsuranceRepository.findAll();
    }

    @Override
    public HomeInsurance save(HomeInsurance entity) {
        return null;
    }

}
