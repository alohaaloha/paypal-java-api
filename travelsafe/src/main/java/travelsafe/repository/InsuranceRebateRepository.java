package travelsafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import travelsafe.model.InsuranceRebate;

import java.util.List;

/**
 * Created by Dorian on 11/25/2016.
 */
@Repository
public interface InsuranceRebateRepository extends JpaRepository<InsuranceRebate, Long> {

    @Query("select ir from InsuranceRebate ir")
    public List<InsuranceRebate> getActual();

}
