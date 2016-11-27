package travelsafe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelsafe.model.InsuranceRebate;
import travelsafe.repository.InsuranceRebateRepository;
import travelsafe.service.GenericService;

import java.util.List;

/**
 * Created by Dorian on 11/25/2016.
 */
@Service
@Transactional
public class InsuranceRebateService implements GenericService<InsuranceRebate> {

    @Autowired
    private InsuranceRebateRepository insuranceRebateRepository;

    @Override
    public InsuranceRebate findOne(Long id) {
        return insuranceRebateRepository.findOne(id);
    }

    @Override
    public List<InsuranceRebate> findAll() {
        return insuranceRebateRepository.findAll();
    }

    @Override
    public InsuranceRebate save(InsuranceRebate insuranceRebate) {
        return insuranceRebateRepository.save(insuranceRebate);
    }

    @Override
    public void remove(Long id) throws IllegalArgumentException {

    }

}
