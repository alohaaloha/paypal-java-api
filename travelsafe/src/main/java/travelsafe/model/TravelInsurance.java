package travelsafe.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dorian on 11/22/2016.
 */
@Entity
@Table(name = "travel_insurance")
public class TravelInsurance {

    @Id
    @GeneratedValue
    @Column(name = "ti_id")
    private Long id;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "number_of_people")
    private Long numberOfPeople;

    @Column(name = "maxAmount")
    private Double maxAmount;

    @Column(name = "totalPrice")
    private Double totalPrice;

    @Column(name="paypalPaymentId")
    private String paypalPaymentId;

    @OneToMany(mappedBy = "travelInsurance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HomeInsurance> homeInsurances = new ArrayList<>();

    @OneToMany(mappedBy = "travelInsurance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CarInsurance> carInsurances = new ArrayList<>();

    @OneToMany(mappedBy = "travelInsurance", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ParticipantInInsurance> participantInInsurances = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ir_id")
    private InsuranceRebate insuranceRebate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_id")
    private Price price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "region_id")
    private Item region;

    public TravelInsurance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Long numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<HomeInsurance> getHomeInsurances() {
        return homeInsurances;
    }

    public void setHomeInsurances(List<HomeInsurance> homeInsurances) {
        this.homeInsurances = homeInsurances;
    }

    public List<CarInsurance> getCarInsurances() {
        return carInsurances;
    }

    public void setCarInsurances(List<CarInsurance> carInsurances) {
        this.carInsurances = carInsurances;
    }

    public List<ParticipantInInsurance> getParticipantInInsurances() {
        return participantInInsurances;
    }

    public void setParticipantInInsurances(List<ParticipantInInsurance> participantInInsurances) {
        this.participantInInsurances = participantInInsurances;
    }

    public InsuranceRebate getInsuranceRebate() {
        return insuranceRebate;
    }

    public void setInsuranceRebate(InsuranceRebate insuranceRebate) {
        this.insuranceRebate = insuranceRebate;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Item getRegion() {
        return region;
    }

    public void setRegion(Item region) {
        this.region = region;
    }

    public String getPaypalPaymentId() {
        return paypalPaymentId;
    }

    public void setPaypalPaymentId(String paypalPaymentId) {
        this.paypalPaymentId = paypalPaymentId;
    }

    @Override
    public String toString() {
        String retValue = "TravelInsurance { " +
                "\n\tduration : " + getDuration() +
                "\n\tnumberOfPeople : " + getNumberOfPeople() +
                "\n\tmaxAmount : " + getMaxAmount() +
                "\n\thomeInsurances : " + homeInsurances.toString() +
                "\n\tcarInsurances : " + carInsurances.toString() +
                "\n\tregion : " + getRegion() +
                "\n\tprice : " + getPrice() +
                "\n}";
        return retValue;
    }
}
