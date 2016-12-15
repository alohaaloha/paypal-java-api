package travelsafe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelsafe.model.CarInsurance;
import travelsafe.repository.CarInsuranceRepository;
import travelsafe.service.GenericService;

import java.util.List;

/**
 * Created by Dorian on 11/25/2016.
 */
@Service
@Transactional
public class CarInsuranceService implements GenericService<CarInsurance> {

    @Autowired
    private CarInsuranceRepository carInsuranceRepository;

    @Override
    public List<CarInsurance> getAll() {
        return carInsuranceRepository.findAll();
    }

    @Override
    public CarInsurance save(CarInsurance entity) {
        return null;
    }

}
