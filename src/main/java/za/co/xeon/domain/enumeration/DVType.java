package za.co.xeon.domain.enumeration;

/**
 * Created by Derick on 8/13/2016.
 */
public enum DVType {
    DIMENSIONS("Dimensions"),VOLUME("Volume");

    private String value;

    DVType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static DVType getEnum(String value) {
        for(DVType v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
