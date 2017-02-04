package travelsafe.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dorian on 11/22/2016.
 */
@Entity
@Table(name = "participant_in_insurance")
public class ParticipantInInsurance {

    @Id
    @GeneratedValue
    @Column(name = "pii_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pin")
    private String pin;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "passportNumber")
    private String passportNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "carrier")
    private Boolean carrier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_insurance")
    private TravelInsurance travelInsurance;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="participants_items", joinColumns=@JoinColumn(name="pii_id"), inverseJoinColumns=@JoinColumn(name="item_id"))
    private List<Item> items = new ArrayList<>();

/*
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "typeOfRisks")
    private List<TypeOfRisk> typeOfRisks = new ArrayList<>();
    */

    public ParticipantInInsurance(){}

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getCarrier() {
        return carrier;
    }

    public void setCarrier(Boolean carrier) {
        this.carrier = carrier;
    }

    public TravelInsurance getTravelInsurance() {
        return travelInsurance;
    }

    public void setTravelInsurance(TravelInsurance travelInsurance) {
        this.travelInsurance = travelInsurance;
    }
/*
    public List<TypeOfRisk> getTypeOfRisks() {
        //return typeOfRisks;
        return null;
    }

    public void setTypeOfRisks(List<TypeOfRisk> typeOfRisks) {
        //this.typeOfRisks = typeOfRisks;
    }*/

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
