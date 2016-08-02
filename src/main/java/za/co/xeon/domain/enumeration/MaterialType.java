package za.co.xeon.domain.enumeration;

/**
 * The CustomerType enumeration.
 */
public enum MaterialType  implements SapCode{
    BAGS("Bags", "COURIER-BAGS"), BOX("Box", "COURIER-BOX"), CRATE("Crate", "COURIER-CRATE"), ENVELOPE("Envelope", "COURIER-ENV"),
    PALLET("Pallet", "COURIER-PAL"), PIECES("Pieces", "COURIER-PCS"), STAND("Stand", "COURIER-STAND"), CONTAINER("Container", "COURIER-CONT"),
    DRUM("Drum", "COURIER-DRUM"), ITEM("Item", "COURIER-ITEM"), PACKAGE("Package", "COURIER-PACK"), PIECE("Piece", "COURIER-PIECE");

    private String value;
    private String sapCode;

    MaterialType(String value, String sapCode) {
        this.value = value;
        this.sapCode = sapCode;
    }

    public String getValue() {
        return value;
    }

    public String getSapCode() {
        return sapCode;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static MaterialType getEnum(String value) {
        for (MaterialType v : values())
            if (v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
