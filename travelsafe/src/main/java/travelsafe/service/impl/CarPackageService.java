package travelsafe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelsafe.model.CarPackage;
import travelsafe.repository.CarPackageRepository;
import travelsafe.service.GenericService;

import java.util.List;

/**
 * Created by Dorian on 11/25/2016.
 */
@Service
@Transactional
public class CarPackageService implements GenericService<CarPackage> {

    @Autowired
    private CarPackageRepository carPackageRepository;

    @Override
    public List<CarPackage> getAll() {
        return carPackageRepository.findAll();
    }

    @Override
    public CarPackage save(CarPackage entity) {
        return null;
    }
}
