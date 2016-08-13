package za.co.xeon.domain.enumeration;

/**
 * Created by Derick on 8/13/2016.
 */
public enum CommentCategory {
    ORDER("Order");

    private String value;

    CommentCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static CommentCategory getEnum(String value) {
        for(CommentCategory v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
