package travelsafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import travelsafe.model.InsuranceRebate;

import java.sql.Date;
import java.util.List;

/**
 * Created by Dorian on 11/25/2016.
 */
@Repository
public interface InsuranceRebateRepository extends JpaRepository<InsuranceRebate, Long> {

    @Query("select ir from InsuranceRebate ir where (ir.from <= ?1 or ir.from is null) and (?1 <= ir.to or ir.to is null)")
    public List<InsuranceRebate> getActual(Date date);

}
