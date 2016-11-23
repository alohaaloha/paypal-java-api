package travelsafe.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dorian on 11/22/2016.
 */
@Entity
@Table(name = "item")
public class Item{

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "coef")
    private Double coef;

    public Item(){

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
