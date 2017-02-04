package travelsafe.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "name_en", nullable = false)
    private String name_en;

    @Column(name = "name_srb")
    private String name_srb;

    @Column(name = "coef")
    private Double coef;

    @Column(name = "date_from")
    @Type(type = "date")
    private Date from;

    @Column(name = "date_to")
    @Type(type = "date")
    private Date to;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_of_risk")
    private TypeOfRisk typeOfRisk;

    public Item(){}

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

    public Double getCoef() {
        return coef;
    }

    public void setCoef(Double coef) {
        this.coef = coef;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getName_srb() {
        return name_srb;
    }

    public void setName_srb(String name_srb) {
        this.name_srb = name_srb;
    }

    public TypeOfRisk getTypeOfRisk() {
        return typeOfRisk;
    }

    public void setTypeOfRisk(TypeOfRisk typeOfRisk) {
        this.typeOfRisk = typeOfRisk;
    }
}
