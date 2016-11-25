package travelsafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travelsafe.model.CarInsurance;

/**
 * Created by Dorian on 11/25/2016.
 */
@Repository
public interface CarInsuranceRepository extends JpaRepository<CarInsurance, Long> {
}
