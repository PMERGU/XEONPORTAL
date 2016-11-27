package za.co.xeon.domain.enumeration;

/**
 * The CustomerType enumeration.
 */
public enum MaterialType implements SapCode {
	BAGS("COURIER-BAGS"), BOX("COURIER-BOX"), CRATE("COURIER-CRATE"), ENVELOPE("COURIER-ENV"), PALLET("COURIER-PAL"), PIECES("COURIER-PCS"), STAND("COURIER-STAND"), CONTAINER("COURIER-CONT"), DRUM("COURIER-DRUM"), ITEM("COURIER-ITEM"), PACKAGE("COURIER-PKG"), PIECE("COURIER-PIECE"), DEDICATED("DEDICATED");

	private String sapCode;

	MaterialType(String sapCode) {
		this.sapCode = sapCode;
	}

	public String getSapCode() {
		return sapCode;
	}
}
