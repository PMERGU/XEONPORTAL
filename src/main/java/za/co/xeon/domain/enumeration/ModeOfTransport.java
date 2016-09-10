package za.co.xeon.domain.enumeration;

/**
 * The ModeOfTransport enumeration.
 */
public enum ModeOfTransport implements SapCode{
    AIR_DELIVERIES("06"), AIR_TRANSFERS("05"), ROAD("RD"), SEA("03");

    private String sapCode;

    ModeOfTransport(String sapCode) {
        this.sapCode = sapCode;
    }

    public String getSapCode() {
        return sapCode;
    }

}
