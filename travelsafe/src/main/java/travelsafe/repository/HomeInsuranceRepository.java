package travelsafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travelsafe.model.HomeInsurance;

/**
 * Created by Dorian on 11/25/2016.
 */
@Repository
public interface HomeInsuranceRepository extends JpaRepository<HomeInsurance, Long> {
}
