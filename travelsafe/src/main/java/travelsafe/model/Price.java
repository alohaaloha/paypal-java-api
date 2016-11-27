package travelsafe.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Dorian on 11/22/2016.
 */
@Entity
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue
    @Column(name = "price_id")
    private Long id;

    @Column(name = "date_from")
    @Type(type = "date")
    private Date from;

    @Column(name = "date_to")
    @Type(type = "date")
    private Date to;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "valute")
    private String valute;

    public Price(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getValute() {
        return valute;
    }

    public void setValute(String valute) {
        this.valute = valute;
    }

}
