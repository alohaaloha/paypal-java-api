package travelsafe.model;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dorian on 11/22/2016.
 */
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "person_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pin", unique = true)
    private String pin;

    @Column(name = "passportNumber", unique = true)
    private String passportNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "typeOfRisks")
    private List<TypeOfRisk> typeOfRisks = new ArrayList<>();

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ParticipantInInsurance> participantInInsurances = new ArrayList<>();

    public Person(){

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<TypeOfRisk> getTypeOfRisks() {
        return typeOfRisks;
    }

    public void setTypeOfRisks(List<TypeOfRisk> typeOfRisks) {
        this.typeOfRisks = typeOfRisks;
    }

    public List<ParticipantInInsurance> getParticipantInInsurances() {
        return participantInInsurances;
    }

    public void setParticipantInInsurances(List<ParticipantInInsurance> participantInInsurances) {
        this.participantInInsurances = participantInInsurances;
    }
}
