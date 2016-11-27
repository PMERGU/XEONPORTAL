package za.co.xeon.domain.enumeration;

/**
 * Created by Derick on 9/11/2016.
 */
public enum ShippingType {
	HUB_TO_HUB_LINE("I4"), DOOR_TO_DOOR_LINE("I9"), HUB_TO_DOOR_LINE("N2"), DOOR_TO_HUB_LINE("N1"), DOOR_TO_DOOR_LOCAL("O7"), HUB_TO_DOOR_LOCAL("O3"), DOOR_TO_HUB_LOCAL("O2"), STANDARD_SHIPPING("L1");

	private String sapCode;

	ShippingType(String sapCode) {
		this.sapCode = sapCode;
	}

	public String getSapCode() {
		return sapCode;
	}
}
