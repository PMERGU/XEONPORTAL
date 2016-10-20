package za.co.xeon.domain.enumeration;

/**
 * The DeliveryType enumeration.
 */
public enum ZoneTypes implements SapCode {

	ZONE1("Zone 1"), ZONE2("Zone 2"), ZONE3("ZONE 3"), ZONE4("ZONE 4"), ZONE5("ZONE 5");

	private String sapCode;

	ZoneTypes(String sapCode) {
		this.sapCode = sapCode;
	}

	public String getSapCode() {
		return sapCode;
	}
}
