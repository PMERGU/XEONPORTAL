package za.co.xeon.domain.enumeration;

/**
 * The DeliveryType enumeration.
 */
public enum DeliveryType implements SapCode{
    RETAIL("Y2"), STANDARD("Y1"), HOME_DROPBOX("Y3"),
    HOME_CONNECTION("Y4"), DSV("Y5");

    private String sapCode;

    DeliveryType(String sapCode) {
        this.sapCode = sapCode;
    }

    public String getSapCode() {
        return sapCode;
    }
}
