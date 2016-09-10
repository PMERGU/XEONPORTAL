package za.co.xeon.domain.enumeration;

/**
 * The ModeOfTransport enumeration.
 */
public enum ServiceType implements SapCode{
    INBOUND("ZIN1"), OUTBOUND("ZOUT"), RETURNS("ZIN1"), STOCK_TRANSFER_OUTBOUND("ZOUT"), STOCK_TRANSFER_INBOUND("ZIN1"),
    LCL("YCO"), FCL("YCO"), COURIER("YCO"), PORT_HUB_INBOUND("ZCR 2"), PORT_HUB_OUTBOUND("ZCR 3"), INLAND_HUB_INBOUND("ZCR 4"),
    INLAND_HUB_OUTBOUND("ZCR 5"), CUSTOMER_TO_PORT("ZCR 6"), STANDARD_CROSS_HAUL("YCO");

    private String sapCode;

    ServiceType(String sapCode) {
        this.sapCode = sapCode;
    }

    public String getSapCode() {
        return sapCode;
    }

}

