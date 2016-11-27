package travelsafe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelsafe.model.ParticipantInInsurance;
import travelsafe.repository.ParticipantInInsuranceRepository;
import travelsafe.service.GenericService;

import java.util.List;

/**
 * Created by Dorian on 11/25/2016.
 */
@Service
@Transactional
public class ParticipantInInsuranceService implements GenericService<ParticipantInInsurance> {

    @Autowired
    private ParticipantInInsuranceRepository participantInInsuranceRepository;

    @Override
    public ParticipantInInsurance findOne(Long id) {
        return participantInInsuranceRepository.findOne(id);
    }

    @Override
    public List<ParticipantInInsurance> findAll() {
        return participantInInsuranceRepository.findAll();
    }

    @Override
    public ParticipantInInsurance save(ParticipantInInsurance participantInInsurance) {
        return participantInInsuranceRepository.save(participantInInsurance);
    }

    @Override
    public void remove(Long id) throws IllegalArgumentException {

    }

}
