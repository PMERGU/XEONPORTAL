package za.co.xeon.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * A BillingInfo.
 */
@Entity
@Table(name = "billing_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class BillingInfo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "location_string")
	private String locationString;

	@NotNull
	@Column(name = "source", nullable = false)
	private String source;

	@NotNull
	@Column(name = "source_zone", nullable = false)
	private String sourceZone;

	@NotNull
	@Column(name = "destination", nullable = false)
	private String destination;

	@NotNull
	@Column(name = "destination_zone", nullable = false)
	private String destinationZone;

	@NotNull
	@Column(name = "rate", nullable = false)
	private Double rate;

	@NotNull
	@Column(name = "min_rate", nullable = false)
	private Double minRate;
	
	
	@NotNull
	@Column(name = "ex_rate", nullable = false)
	private Double exRate;

	@NotNull
	@Column(name = "ex_min_rate", nullable = false)
	private Double exMinRate;

	@NotNull
	@Column(name = "eco_min", nullable = false)
	private Double ecoMin;
	
	@NotNull
	@Column(name = "eco_max", nullable = false)
	private Double ecoMax;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocationString() {
		return locationString;
	}

	public void setLocationString(String locationString) {
		this.locationString = locationString;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceZone() {
		return sourceZone;
	}

	public void setSourceZone(String sourceZone) {
		this.sourceZone = sourceZone;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDestinationZone() {
		return destinationZone;
	}

	public void setDestinationZone(String destinationZone) {
		this.destinationZone = destinationZone;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getMinRate() {
		return minRate;
	}

	public void setMinRate(Double minRate) {
		this.minRate = minRate;
	}

	public Double getEcoMin() {
		return ecoMin;
	}

	public void setEcoMin(Double ecoMin) {
		this.ecoMin = ecoMin;
	}

	public Double getEcoMax() {
		return ecoMax;
	}

	public void setEcoMax(Double ecoMax) {
		this.ecoMax = ecoMax;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BillingInfo purchaseOrder = (BillingInfo) o;
		if (purchaseOrder.id == null || id == null) {
			return false;
		}
		return Objects.equals(id, purchaseOrder.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return " BillingInfo{ " + "id=" + id + ", source=" + source + ", sourceZone= ' " + sourceZone + '\''
				+ ", destination=" + destination + '\'' + ", destinationZone=" + destinationZone + '\'' + ", rate="
				+ rate + '\'' + ", minRate=" + minRate + '\'' + ", exRate="
						+ exRate + '\'' + ", exMinRate=" + exMinRate + '\'' +  ", ecoMin=" + ecoMin + '\'' + ", ecoMax=" + ecoMax
				+ '\'' + '}';
	}

	public String toStringFull() {
		return " BillingInfo{ " + "id=" + id + ", source=" + source + ", sourceZone= ' " + sourceZone + '\''
				+ ", destination=" + destination + '\'' + ", destinationZone=" + destinationZone + '\'' + ", rate="
				+ rate + '\'' + ", minRate=" + minRate + '\'' + ", exRate="
						+ exRate + '\'' + ", exMinRate=" + exMinRate + '\'' +  ", ecoMin=" + ecoMin + '\'' + ", ecoMax=" + ecoMax
				+ '\'' + '}';
	}

	public Double getExRate() {
		return exRate;
	}

	public void setExRate(Double exRate) {
		this.exRate = exRate;
	}

	public Double getExMinRate() {
		return exMinRate;
	}

	public void setExMinRate(Double exMinRate) {
		this.exMinRate = exMinRate;
	}

}
