package za.co.xeon.domain.enumeration;

/**
 * Created by Derick on 8/13/2016.
 */
public enum     PartyType {
    SOLD_TO_PARTY("Sold to party"), OTHER("Other");

    private String value;

    PartyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static PartyType getEnum(String value) {
        for(PartyType v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
