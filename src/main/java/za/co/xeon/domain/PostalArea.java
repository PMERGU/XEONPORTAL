package za.co.xeon.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Party.
 */
@Entity
@Table(name = "postal_code")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PostalArea implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7641681223926942077L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "suburb")
    private String suburb;

    @Column(name = "postal_code")
    private Integer postalCode;

    @Column(name = "city")
    private String city;

    @Column(name = "trafficZone")
    private String trafficZone;

    @Column(name = "hub")
    private String hub;

    @Column(name = "plant")
    private String plant;

    @Column(name = "province")
    private String province;

    @Column(name = "country")
    private String country;

    public PostalArea() {
    }

    public PostalArea(String suburb, Integer postalCode, String city, String trafficZone, String hub, String plant, String province, String country) {
        this.suburb = suburb;
        this.postalCode = postalCode;
        this.city = city;
        this.trafficZone = trafficZone;
        this.hub = hub;
        this.plant = plant;
        this.province = province;
        this.country = country;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getTrafficZone() {
        return trafficZone;
    }

    public void setTrafficZone(String trafficZone) {
        this.trafficZone = trafficZone;
    }

    public String getHub() {
        return hub;
    }

    public void setHub(String hub) {
        this.hub = hub;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostalArea that = (PostalArea) o;

        if (suburb != null ? !suburb.equals(that.suburb) : that.suburb != null) return false;
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        return country != null ? country.equals(that.country) : that.country == null;

    }

    @Override
    public int hashCode() {
        int result = suburb != null ? suburb.hashCode() : 0;
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostalArea{" +
            "id=" + id +
            ", suburb='" + suburb + '\'' +
            ", postalCode=" + postalCode +
            ", city='" + city + '\'' +
            ", province='" + province + '\'' +
            ", country='" + country + '\'' +
            '}';
    }

    public String toStringShort() {
        return "PostalArea{" +
            "postalCode=" + postalCode +
            ", hub='" + hub + '\'' +
            ", plant='" + plant + '\'' +
            '}';
    }
}

