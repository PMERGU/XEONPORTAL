package za.co.xeon.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import za.co.xeon.domain.Company;
import za.co.xeon.domain.Party;
import za.co.xeon.domain.User;
import za.co.xeon.domain.enumeration.CompanyType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A Company.
 */
public class CompanyDto implements Serializable {

    private Long id;

    private String name;

    private String sapId;

    private CompanyType type;

    private byte[] background;

    private String backgroundContentType;

    public CompanyDto() {
    }

    public CompanyDto(Long id, String name, String sapId, CompanyType type, byte[] background, String backgroundContentType) {
        this.id = id;
        this.name = name;
        this.sapId = sapId;
        this.type = type;
        this.background = background;
        this.backgroundContentType = backgroundContentType;
    }

    public static CompanyDto map(Company company){
        return new CompanyDto(company.getId(), company.getName(), company.getSapId(), company.getType(), company.getBackground(), company.getBackgroundContentType());
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

    public CompanyType getType() {
        return type;
    }

    public void setType(CompanyType type) {
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyDto that = (CompanyDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sapId != null ? !sapId.equals(that.sapId) : that.sapId != null) return false;
        if (type != that.type) return false;
        if (!Arrays.equals(background, that.background)) return false;
        return backgroundContentType != null ? backgroundContentType.equals(that.backgroundContentType) : that.backgroundContentType == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sapId != null ? sapId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(background);
        result = 31 * result + (backgroundContentType != null ? backgroundContentType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CompanyDto{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", sapId='" + sapId + '\'' +
            ", type=" + type +
            ", background=" + Arrays.toString(background) +
            ", backgroundContentType='" + backgroundContentType + '\'' +
            '}';
    }
}
