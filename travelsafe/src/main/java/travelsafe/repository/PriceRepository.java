package travelsafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import travelsafe.model.Price;

import java.util.List;

/**
 * Created by Dorian on 11/25/2016.
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("select p from Price p")
    public List<Price> getActual();

}
