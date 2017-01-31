package travelsafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import travelsafe.model.Region;

import java.util.List;

/**
 * Created by Dorian on 1/4/2017.
 */
@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    @Query("select r.en_translation from Region r")
    public List<String> getRegionsInEnglish();

    @Query("select r.ser_translation from Region r")
    public List<String> getRegionsInSerbian();

}
