package za.co.xeon.domain.enumeration;

/**
 * The CustomerType enumeration.
 */
public enum UnitOfMeasure implements SapCode {
	BAG("BAG"), BOX("BOX"), BOTTLE("BT"), CANISTER("CAN"), CARTON("CAR"), CUBIC_METER("CBM"), CRATE("CRT"), CASE("CV"), DEGREE("DEG"), DRUM("DR"), EACH("EA"), PACK("PAC"), PALLET("PAL");

	private String sapCode;

	UnitOfMeasure(String sapCode) {
		this.sapCode = sapCode;
	}

	public String getSapCode() {
		return sapCode;
	}
}
