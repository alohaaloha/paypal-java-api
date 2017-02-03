package travelsafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import travelsafe.model.Price;

import java.sql.Date;
import java.util.List;

/**
 * Created by Dorian on 11/25/2016.
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("select p from Price p where (p.from <= ?1 or p.from is null) and (?1 <= p.to or p.to is null)")
    public List<Price> getActual(Date date);

}
