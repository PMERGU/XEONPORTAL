package za.co.xeon.domain.enumeration;

/**
 * The ServiceLevel enumeration.
 */
public enum ServiceLevel  implements SapCode{
    ECONOMY_PUS("Economy Plus","YS10"), ECONOMY("Economy","YSL1"), DEDICATED("Dedicated","YSL3"), OVERNIGHT_EXPRESS("Overnight express","YSL9");

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
