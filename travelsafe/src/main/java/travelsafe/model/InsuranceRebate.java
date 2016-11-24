package travelsafe.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Dorian on 11/22/2016.
 */
@Entity
@Table(name = "insurance_rebate")
public class InsuranceRebate {

    @Id
    @GeneratedValue
    @Column(name = "ir_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_from")
    @Type(type = "date")
    private Date from;

    @Column(name = "date_to")
    @Type(type = "date")
    private Date to;

    @Column(name = "amount")
    private Double amount;

    public InsuranceRebate(){}

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
}
