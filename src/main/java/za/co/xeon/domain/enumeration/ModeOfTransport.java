package za.co.xeon.domain.enumeration;

/**
 * The ModeOfTransport enumeration.
 */
public enum ModeOfTransport implements SapCode{
    AIR("06"), ROAD("RD"), ROAD_FCL("FR"), SEA("03"), SEA_FCL("FS"),
    ALL_MODES_OF_TRANSPORT_CH("XH"), ALL_MODES_OF_TRANSPORT_FTL("FT");

    private String sapCode;

    ModeOfTransport(String sapCode) {
        this.sapCode = sapCode;
    }

    public String getSapCode() {
        return sapCode;
    }
}
