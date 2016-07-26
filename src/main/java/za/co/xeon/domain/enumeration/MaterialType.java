package za.co.xeon.domain.enumeration;

/**
 * The CustomerType enumeration.
 */
public enum MaterialType  implements SapCode{
    BAGS("Bags", "C-BAGS"), BOX("Box", "C-BOX"), CRATE("Crate", "C-CRATE"), ENVELOPE("Envelope", "C-ENV"),
    PALLET("Pallet", "C-PAL"), PIECES("Pieces", "C-PCS"), STAND("Stand", "C-STAND"), CONTAINER("Container", "C-CONT"),
    DRUM("Drum", "C-DRUM"), ITEM("Item", "C-ITEM"), PACKAGE("Package", "C-PACK"), PIECE("Piece", "C-PIECE");

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
