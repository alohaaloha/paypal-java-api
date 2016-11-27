package travelsafe.model;

import javax.persistence.*;

/**
 * Created by Dorian on 11/23/2016.
 */
@Entity
@Table(name = "car_package")
public class CarPackage {

    @Id
    @GeneratedValue
    @Column(name = "cp_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OfferType offer;

    public CarPackage() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OfferType getOffer() {
        return offer;
    }

    public void setOffer(OfferType offer) {
        this.offer = offer;
    }

}
