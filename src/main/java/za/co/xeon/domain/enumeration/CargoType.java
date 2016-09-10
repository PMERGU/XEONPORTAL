package za.co.xeon.domain.enumeration;

/**
 * The CustomerType enumeration.
 */
public enum CargoType implements SapCode{
    EXPLOSIVES("01"), GASES("02"), FLAMMABLE_LIQUIDS("03"),
    FLAMMABLE_SOLIDS("04"), OXIDIZING("05"), TOXIC_INFECTIOUS("06"),
    RADIOACTIVE("07"), CORROSIVES("08"), DANGEROUS_GOODS("09");

    private String sapCode;

    CargoType(String sapCode) {
        this.sapCode = sapCode;
    }

    public String getSapCode() {
        return sapCode;
    }

}
