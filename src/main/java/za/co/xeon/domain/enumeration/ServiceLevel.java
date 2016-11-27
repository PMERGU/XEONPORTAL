package za.co.xeon.domain.enumeration;

/**
 * The ServiceLevel enumeration.
 */
public enum ServiceLevel implements SapCode {
	EXPRESS("YSL2"), ECONOMY_PLUS("YSL10"), ECONOMY("YSL1"), DEDICATED("YSL3"), OVERNIGHT_EXPRESS("YSL9"), STANDARD_CROSS_DOCK("YSL5"), STANDARD_CROSS_HAUL("YSL5"), CUSTOMER_DROP_OFF("YSL4"), CUSTOMER_COLLECTION("YSL4"), STOCK_TRANSFER("YSL6"), STANDARD_FTL("YSL1");

	private String sapCode;

	ServiceLevel(String sapCode) {
		this.sapCode = sapCode;
	}

	public String getSapCode() {
		return sapCode;
	}

}
