package travelsafe.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dorian on 11/22/2016.
 */
@Entity
@Table(name = "type_of_risk")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@type_of_risk_id")
public class TypeOfRisk {

    @Id
    @GeneratedValue
    @Column(name = "type_of_risk_id")
    private Long id;

    @Column(name = "name_en")
    private String name_en;

    @Column(name = "name_srb")
    private String name_srb;

    //da li ide na front ili ne
    @Column(name = "optional")
    private Boolean optional;

    @Column(name = "two_or_more")
    @Null
    private Boolean twoOrMore;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "typeOfRisk")
    //@JoinColumn(name = "items")
    private List<Item> items = new ArrayList<>();

    public TypeOfRisk(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name) {
        this.name_en = name;
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

    public String getName_srb() {
        return name_srb;
    }

    public void setName_srb(String name_srb) {
        this.name_srb = name_srb;
    }

    public Boolean getTwoOrMore() {
        return twoOrMore;
    }

    public void setTwoOrMore(Boolean twoOrMore) {
        this.twoOrMore = twoOrMore;
    }
}
