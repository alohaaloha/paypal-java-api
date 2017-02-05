package travelsafe.model.dto;

/**
 * Created by Dorian on 2/4/2017.
 */
public class ItemDTO {

    private Long id;
    private String name;
    private Double coef;

    public ItemDTO(){

    }

    public ItemDTO(Long id, String name, Double coef){
        this.id = id;
        this.name = name;
        this.coef = coef;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCoef() {
        return coef;
    }

    public void setCoef(Double coef) {
        this.coef = coef;
    }
}
