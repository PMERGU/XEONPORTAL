package za.co.xeon.domain.enumeration;

/**
 * The DeliveryType enumeration.
 */
public enum DeliveryType implements SapCode{
    RETAIL("Retail","Y2"), STANDARD("Standard","Y1"), HOME_DROPBOX("Home Dropbox","Y3"),
    HOME_CONNECTION("Home Connection","Y4"), DSV("DSV Price Group", "Y5");

    private String value;
    private String sapCode;

    DeliveryType(String value, String sapCode) {
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

    public static DeliveryType getEnum(String value) {
        for(DeliveryType v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
