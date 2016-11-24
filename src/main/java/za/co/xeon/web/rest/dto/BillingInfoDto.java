package za.co.xeon.web.rest.dto;

public class BillingInfoDto {

	private String locationString;

	private String source;

	private String sourceZone;

	private String destination;

	private String destinationZone;

	private String rate;

	private String minRate;

	private String ecoMin;
	private String ecoMax;

	private Double weight;

	private Double calculatedBill;

	private Double volume;

	private String serviceType;
	private String serviceLevel;
	private boolean surcharge;
	private String modeOfTrans;

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

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getMinRate() {
		return minRate;
	}

	public void setMinRate(String minRate) {
		this.minRate = minRate;
	}

	public String getEcoMin() {
		return ecoMin;
	}

	public void setEcoMin(String ecoMin) {
		this.ecoMin = ecoMin;
	}

	public String getEcoMax() {
		return ecoMax;
	}

	public void setEcoMax(String ecoMax) {
		this.ecoMax = ecoMax;
	}

	@Override
	public String toString() {
		return " BillingInfo{ source='" + source + '\'' + ", sourceZone= ' " + sourceZone + '\'' + ", destination=" + destination + '\'' + ", destinationZone=" + destinationZone + '\'' + ", rate=" + rate + '\'' + ", calculatedBill= " + calculatedBill + '\'' + ", volume=" + volume + '\'' + ", weight=" + weight + '\'' + ", minRate=" + minRate + '\'' + ", ecoMin=" + ecoMin + '\'' + ", ecoMax=" + ecoMax + '\'' + ", serviceType=" + serviceType + '\'' + ", serviceLevel=" + serviceLevel + '\'' + ", surcharge=" + surcharge + '\'' + ", modeOfTrans=" + modeOfTrans + '\'' + '}';
	}

	public String toStringFull() {
		return " BillingInfo{ source='" + source + '\'' + ", sourceZone= ' " + sourceZone + '\'' + ", destination=" + destination + '\'' + ", destinationZone=" + destinationZone + '\'' + ", rate=" + rate + '\'' + ", calculatedBill= " + calculatedBill + '\'' + ", volume=" + volume + '\'' + ", weight=" + weight + '\'' + ", minRate=" + minRate + '\'' + ", ecoMin=" + ecoMin + '\'' + ", ecoMax=" + ecoMax + '\'' + ", serviceType=" + serviceType + '\'' + ", serviceLevel=" + serviceLevel + '\'' + ", surcharge=" + surcharge + '\'' + ", modeOfTrans=" + modeOfTrans + '\'' + '}';
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getCalculatedBill() {
		return calculatedBill;
	}

	public void setCalculatedBill(Double calculatedBill) {
		this.calculatedBill = calculatedBill;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public boolean isSurcharge() {
		return surcharge;
	}

	public void setSurcharge(boolean surcharge) {
		this.surcharge = surcharge;
	}

	public String getModeOfTrans() {
		return modeOfTrans;
	}

	public void setModeOfTrans(String modeOfTrans) {
		this.modeOfTrans = modeOfTrans;
	}

}
