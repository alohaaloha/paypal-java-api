package travelsafe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dorian on 11/22/2016.
 */
@Entity
@Table(name = "type_of_risk")
public class TypeOfRisk {

    @Id
    @GeneratedValue
    @Column(name = "type_of_risk_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "optional")
    private Boolean optional;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "items")
    private List<Item> items = new ArrayList<>();

    public TypeOfRisk(){

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

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
