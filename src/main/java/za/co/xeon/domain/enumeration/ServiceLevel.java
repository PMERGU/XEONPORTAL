package za.co.xeon.domain.enumeration;

/**
 * The ServiceLevel enumeration.
 */
public enum ServiceLevel  implements SapCode{
    ECONOMY("Economy","YSL1"), EXPRESS_AM("Express AM","YSL2"), EXPRESS_PM("Express PM","YSL2"),
    DEDICATED_EXPRESS("Dedicated","YSL3"), DEDICATED_ECONOMY("",""), CUSTOMER_COLLECTIONS("Customer Collections","YSL4"),
    CROSS_DOCK("Cross Dock","YSL5"), STO_INBOUND("STO L/H Inbound","YSL6");

    private String value;
    private String sapCode;

    ServiceLevel(String value, String sapCode) {
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

    public static ServiceLevel getEnum(String value) {
        for(ServiceLevel v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
