package travelsafe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelsafe.model.TypeOfRisk;
import travelsafe.repository.TypeOfRiskRepository;
import travelsafe.service.GenericService;

import java.util.List;

/**
 * Created by Dorian on 11/25/2016.
 */
@Service
@Transactional
public class TypeOfRiskService implements GenericService<TypeOfRisk> {

    @Autowired
    private TypeOfRiskRepository typeOfRiskRepository;

    @Override
    public List<TypeOfRisk> getAll() {
        return typeOfRiskRepository.findAll();
    }

    @Override
    public TypeOfRisk save(TypeOfRisk entity) {
        return null;
    }
}
