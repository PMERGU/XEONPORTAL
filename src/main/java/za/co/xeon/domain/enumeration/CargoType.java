package za.co.xeon.domain.enumeration;

/**
 * The CustomerType enumeration.
 */
public enum CargoType  implements SapCode{
    EXPLOSIVES("Explosives", "01"), GASES("Gases", "02"), FLAMMABLE_LIQUIDS("Flammable Liquids", "03"),
    FLAMMABLE_SOLIDS("Flammable Solids", "04"), OXIDIZING("Oxidizing", "05"), TOXIC_INFECTIOUS("Toxic Infectious", "06"),
    RADIOACTIVE("Radioactive", "07"), CORROSIVES("Corrosives", "08"), DANGEROUS_GOODS("Dangerous Goods", "09");

    private String value;
    private String sapCode;

    CargoType(String value, String sapCode) {
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

    public static CargoType getEnum(String value) {
        for (CargoType v : values())
            if (v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
