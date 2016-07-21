package za.co.xeon.domain.enumeration;

/**
 * Created by Derick on 7/17/2016.
 */
public enum AttachmentCategories {
    CARTAGE("Cartage"), INVOICE("Invoice"), POD("POD"), TREM("TREM");

    private String value;

    AttachmentCategories(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static AttachmentCategories getEnum(String value) {
        for(AttachmentCategories v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
