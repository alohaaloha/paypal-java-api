package travelsafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import travelsafe.model.Item;

import java.sql.Date;
import java.util.List;

/**
 * Created by Dorian on 11/22/2016.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    //@Query("select i from Item i where i.from = ?1 or i.from is null")
    @Query("select i from Item i where i.from >= ?1 or i.from is null")
    public List<Item> getActual(Date date); //where ((?1 >= i.from) or (i.from is null)) and ((?1 <= i.to) or (i.from is null))

}
