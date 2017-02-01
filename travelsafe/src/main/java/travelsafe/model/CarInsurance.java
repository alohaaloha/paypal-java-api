package travelsafe.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dorian on 11/23/2016.
 */
@Entity
@Table(name = "car_insurance")
public class CarInsurance {

    @Id
    @GeneratedValue
    @Column(name = "ci_id")
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "type")
    private String type;

    @Column(name = "year_of_production")
    private Long yearOfProduction;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "chassis_number")
    private String chassisNumber;

    @Column(name = "owners_name")
    private String ownersName;

    @Column(name = "owners_surname")
    private String ownersSurname;

    @Column(name = "owners_pin")
    private String ownersPIN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_insurance")
    private TravelInsurance travelInsurance;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "packages_in_car_insurances",
                joinColumns = {@JoinColumn(name = "ci_id")},
                inverseJoinColumns = {@JoinColumn(name = "cp_id")})
    private List<CarPackage> carPackages = new ArrayList<>();

    public CarInsurance() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Long yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getOwnersName() {
        return ownersName;
    }

    public void setOwnersName(String ownersName) {
        this.ownersName = ownersName;
    }

    public String getOwnersSurname() {
        return ownersSurname;
    }

    public void setOwnersSurname(String ownersSurname) {
        this.ownersSurname = ownersSurname;
    }

    public String getOwnersPIN() {
        return ownersPIN;
    }

    public void setOwnersPIN(String ownersPIN) {
        this.ownersPIN = ownersPIN;
    }

    public TravelInsurance getTravelInsurance() {
        return travelInsurance;
    }

    public void setTravelInsurance(TravelInsurance travelInsurance) {
        this.travelInsurance = travelInsurance;
    }

    @Override
    public String toString() {
        String retValue = "HomeInsurance { " +
                "\n\tbrand : " + getBrand() +
                "\n\towner : " + getOwnersName() + " " + getOwnersSurname() +
                "\n}";
        return retValue;
    }
}
