package travelsafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travelsafe.model.ParticipantInInsurance;

/**
 * Created by Dorian on 11/25/2016.
 */
@Repository
public interface ParticipantInInsuranceRepository extends JpaRepository<ParticipantInInsurance,Long> {
}
