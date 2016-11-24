package travelsafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travelsafe.model.Item;

/**
 * Created by Dorian on 11/22/2016.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}


