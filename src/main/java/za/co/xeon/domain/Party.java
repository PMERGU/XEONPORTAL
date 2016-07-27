package za.co.xeon.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Party.
 */
@Entity
@Table(name = "party")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Party implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "street_name")
    private String streetName;

    @ManyToOne
    @JoinColumn(name = "postal_code_id")
    private PostalArea area;

    @Column(name = "reference")
    private String reference;

    @NotNull
    @Column(name = "sap_id", nullable = false)
    private String sapId;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public String getSapId() {
        return sapId;
    }

    public void setSapId(String sapId) {
        this.sapId = sapId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public PostalArea getArea() {
        return area;
    }

    public void setArea(PostalArea area) {
        this.area = area;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Party party = (Party) o;
        if(party.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, party.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Party{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", houseNumber='" + houseNumber + "'" +
            ", streetName='" + streetName + "'" +
            ", reference='" + reference + "'" +
            '}';
    }
}
