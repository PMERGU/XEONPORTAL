package za.co.xeon.domain.enumeration;

/**
 * The ModeOfTransport enumeration.
 */
public enum ServiceType  implements SapCode{
    COURIER("Courier","YCO"), INBOUND("Inbound","ZIN1"), OUTBOUND("Outbound","ZOUT");

    private String value;
    private String sapCode;

    ServiceType(String value, String sapCode) {
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

    public static ServiceType getEnum(String value) {
        for(ServiceType v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}

