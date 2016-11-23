package travelsafe.model;

import javax.persistence.*;

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

    @Column(name = "carrier")
    private Boolean carrier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_insurance")
    private TravelInsurance travelInsurance;

    public ParticipantInInsurance(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCarrier() {
        return carrier;
    }

    public void setCarrier(Boolean carrier) {
        this.carrier = carrier;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public TravelInsurance getTravelInsurance() {
        return travelInsurance;
    }

    public void setTravelInsurance(TravelInsurance travelInsurance) {
        this.travelInsurance = travelInsurance;
    }
}
