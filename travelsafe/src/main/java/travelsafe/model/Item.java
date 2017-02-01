package travelsafe.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;

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

    @Column(name = "date_from")
    @Type(type = "date")
    private Date from;

    @Column(name = "date_to")
    @Type(type = "date")
    private Date to;

    public Item(){}

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

}
