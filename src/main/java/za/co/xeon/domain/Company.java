package za.co.xeon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import za.co.xeon.domain.enumeration.CompanyType;
import za.co.xeon.domain.enumeration.TransportParty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Company.
 */
@Entity
@Table(name = "company")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "sap_id", nullable = false)
    private String sapId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CompanyType type;

    @Lob
    @Column(name = "background")
    private byte[] background;

    @Column(name = "background_content_type")
    private String backgroundContentType;

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<User> employees = new HashSet<>();

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Party> locations = new HashSet<>();

    public Company() {
    }

    public Company(Long id) {
        this.id = id;
    }

    public Company(Long id, String name, String sapId, CompanyType type) {
        this.id = id;
        this.name = name;
        this.sapId = sapId;
        this.type = type;
    }

    public CompanyType getType() {
        return type;
    }

    public void setType(CompanyType type) {
        this.type = type;
    }

    public Set<Party> getLocations() {
        return locations;
    }

    public void setLocations(Set<Party> locations) {
        this.locations = locations;
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

    public String getSapId() {
        return sapId;
    }

    public void setSapId(String sapId) {
        this.sapId = sapId;
    }

    public byte[] getBackground() {
        return background;
    }

    public void setBackground(byte[] background) {
        this.background = background;
    }

    public String getBackgroundContentType() {
        return backgroundContentType;
    }

    public void setBackgroundContentType(String backgroundContentType) {
        this.backgroundContentType = backgroundContentType;
    }

    public Set<User> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<User> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Company company = (Company) o;
        if(company.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Company{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", sapId='" + sapId + "'" +
            ", background='" + background + "'" +
            ", backgroundContentType='" + backgroundContentType + "'" +
            '}';
    }
}
