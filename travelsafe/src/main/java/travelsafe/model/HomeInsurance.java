package travelsafe.model;

import javax.persistence.*;

/**
 * Created by Dorian on 11/23/2016.
 */
@Entity
@Table(name = "home_insurance")
public class HomeInsurance {

    @Id
    @GeneratedValue
    @Column(name = "hi_id")
    private Long id;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "surface_area")
    private Double surfaceArea;

    @Column(name = "age")
    private Long age;

    @Column(name = "estimated_value")
    private Double estimatedValue;

    @Column(name = "insurance_description")
    private String insuranceDescription;

    @Column(name = "address")
    private String address;

    @Column(name = "owners_name")
    private String ownersName;

    @Column(name = "owners_surname")
    private String ownersSurname;

    @Column(name = "owners_pin")
    private String ownersPIN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_insurance")
    private TravelInsurance travelInsurance;

    public HomeInsurance(){}

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

    public Double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(Double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Double getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(Double estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public String getInsuranceDescription() {
        return insuranceDescription;
    }

    public void setInsuranceDescription(String insuranceDescription) {
        this.insuranceDescription = insuranceDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
                "\n\taddress : " + getAddress() +
                "\n\towner : " + getOwnersName() + " " + getOwnersSurname() +
                "\n}";
        return retValue;
    }
}
