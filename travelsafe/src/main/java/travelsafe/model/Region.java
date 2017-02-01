package travelsafe.model;

import javax.persistence.*;

/**
 * Created by Dorian on 1/4/2017.
 */
@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue
    @Column(name = "region_id")
    private Long id;

    @Column(name = "ser_translation")
    private String ser_translation;

    @Column(name = "en_translation")
    private String en_translation;

    public Region(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSer_translation() {
        return ser_translation;
    }

    public void setSer_translation(String ser_translation) {
        this.ser_translation = ser_translation;
    }

    public String getEn_translation() {
        return en_translation;
    }

    public void setEn_translation(String en_translation) {
        this.en_translation = en_translation;
    }

    @Override
    public String toString() {
        String retValue = "Region { " +
                "\n\tserTranslation : " + getSer_translation() +
                "\n\tenTranslation : " + getEn_translation() +
                "\n}";
        return retValue;
    }
}
