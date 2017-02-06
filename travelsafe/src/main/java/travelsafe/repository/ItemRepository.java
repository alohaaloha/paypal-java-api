package travelsafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import travelsafe.model.Item;
import travelsafe.model.dto.ItemDTO;

import java.sql.Date;
import java.util.List;

/**
 * Created by Dorian on 11/22/2016.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where (i.from <= ?1 or i.from is null) and (?1 <= i.to or i.to is null)")
    public List<Item> getActual(Date date);

    @Query("select new travelsafe.model.dto.ItemDTO(i.id, i.name_en, i.coef) from Item i where i.typeOfRisk.name_en=?1")
    public List<ItemDTO> getItemByTypeOfRiskEN(String name_en);

    @Query("select new travelsafe.model.dto.ItemDTO(i.id, i.name_srb, i.coef) from Item i where i.typeOfRisk.name_srb=?1")
    public List<ItemDTO> getItemByTypeOfRiskSRB(String name);

    @Query("select new travelsafe.model.dto.ItemDTO(i.id, i.name_en, i.coef) from Item i where i.typeOfRisk.optional=?1")
    public List<ItemDTO> getItemByOptionalEN(Boolean optional);

    @Query("select new travelsafe.model.dto.ItemDTO(i.id, i.name_srb, i.coef) from Item i where i.typeOfRisk.optional=?1")
    public List<ItemDTO> getItemByOptionalSRB(Boolean optional);

    @Query("select new travelsafe.model.dto.ItemDTO(i.id, i.name_en, i.coef) from Item i " +
            "where i.typeOfRisk.name_en = ?1 " +
            "and (i.from <= ?2 or i.from is null)" +
            "and (?2 <= i.to or i.to is null)")
    public List<ItemDTO> getActualItemsByTypeOfRiskEN(String name_en, Date date);

    @Query("select new travelsafe.model.dto.ItemDTO(i.id, i.name_srb, i.coef) from Item i " +
            "where i.typeOfRisk.name_srb = ?1 " +
            "and (i.from <= ?2 or i.from is null)" +
            "and (?2 <= i.to or i.to is null)")
    public List<ItemDTO> getActualItemsByTypeOfRiskSRB(String name, Date date);

    @Query("select new travelsafe.model.dto.ItemDTO(i.id, i.name_en, i.coef) from Item i " +
            "where i.typeOfRisk.optional = ?1 " +
            "and (i.from <= ?2 or i.from is null)" +
            "and (?2 <= i.to or i.to is null)")
    public List<ItemDTO> getActualItemsByOptionalEN(Boolean optional, Date date);

    @Query("select new travelsafe.model.dto.ItemDTO(i.id, i.name_srb, i.coef) from Item i " +
            "where i.typeOfRisk.optional = ?1 " +
            "and (i.from <= ?2 or i.from is null)" +
            "and (?2 <= i.to or i.to is null)")
    public List<ItemDTO> getActualItemsByOptionalSRB(Boolean optional, Date date);

    /*
    @Query("select new travelsafe.model.ItemDTO(i.id, i.name_en) from Item i where i.typeOfRisk.name_en=?1")
    public List<ItemDTO> test(String name_en);
    */

}
