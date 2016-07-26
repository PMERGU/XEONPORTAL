package za.co.xeon.domain.enumeration;

/**
 * The ModeOfTransport enumeration.
 */
public enum ModeOfTransport  implements SapCode{
    AIR_DELIVERIES("Air Deliveries","06"), AIR_TRANSFERS("Air Transfers","05"), ROAD("Road","RD"), SEA("Sea","03");

    private String value;
    private String sapCode;

    ModeOfTransport(String value, String sapCode) {
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

    public static ModeOfTransport getEnum(String value) {
        for(ModeOfTransport v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }

}
