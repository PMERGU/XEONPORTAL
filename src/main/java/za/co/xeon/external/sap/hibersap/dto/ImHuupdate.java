package za.co.xeon.external.sap.hibersap.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

/**
 * Created by Derick on 5/1/2016.
 */
@BapiStructure
public class ImHuupdate {
    /**
     * "External Handling Unit Identification"
     */
    @Parameter("EXIDV")
    private String exidv;

    /**
     * "Length"
     */
    @Parameter("LAENG")
    private java.math.BigDecimal laeng;

    /**
     * "Width"
     */
    @Parameter("BREIT")
    private java.math.BigDecimal breit;

    /**
     * "Height"
     */
    @Parameter("HOEHE")
    private java.math.BigDecimal hoehe;

    /**
     * "Unit of dimension for length/width/height"
     */
    @Parameter("MEABM")
    private String meabm;

    /**
     * "The first status for tracking"
     */
    @Parameter("VEGR1")
    private String vegr1;

    /**
     * "The second status for tracking"
     */
    @Parameter("VEGR2")
    private String vegr2;

    /**
     * "The step number in the tracking process" */
    @Parameter("VEGR3")
    private java.lang.String vegr3;

    /**
     * "Handling Unit's 2nd External Identification"
     */
    @Parameter("EXT_ID_HU_2")
    private String extIdHu2;

    /**
     * "Total Weight of Handling Unit"
     */
    @Parameter("BRGEW")
    private java.math.BigDecimal brgew;

    /**
     * "Weight Unit"
     */
    @Parameter("GEWEI")
    private String gewei;

    public ImHuupdate(String exidv, java.math.BigDecimal laeng, java.math.BigDecimal breit, java.math.BigDecimal hoehe, String meabm, String vegr1, String vegr2, String vegr3, String extIdHu2, java.math.BigDecimal brgew, String gewei) {
        this.exidv = exidv;
        this.laeng = laeng;
        this.breit = breit;
        this.hoehe = hoehe;
        this.meabm = meabm;
        this.vegr1 = vegr1;
        this.vegr2 = vegr2;
        this.vegr3 = vegr3;
        this.extIdHu2 = extIdHu2;
        this.brgew = brgew;
        this.gewei = gewei;
    }

    public ImHuupdate(String exidv, String extIdHu2) {
        this.exidv = exidv;
        this.extIdHu2 = extIdHu2;
    }

    public ImHuupdate() {
    }

    /**
     * @return "Exidv" - "External Handling Unit Identification"
     */
    public String getExidv() {
        return exidv;
    }

    /**
     * @return "Laeng" - "Length"
     */
    public java.math.BigDecimal getLaeng() {
        return laeng;
    }

    /**
     * @return "Breit" - "Width"
     */
    public java.math.BigDecimal getBreit() {
        return breit;
    }

    /**
     * @return "Hoehe" - "Height"
     */
    public java.math.BigDecimal getHoehe() {
        return hoehe;
    }

    /**
     * @return "Meabm" - "Unit of dimension for length/width/height"
     */
    public String getMeabm() {
        return meabm;
    }

    /**
     * @return "Vegr1" - "The first status for tracking"
     */
    public String getVegr1() {
        return vegr1;
    }

    /**
     * @return "Vegr2" - "The second status for tracking"
     */
    public String getVegr2() {
        return vegr2;
    }

    /**
     * @return "Vegr3" - "The second status for tracking"
     */
    public String getVegr3() {
        return vegr3;
    }

    /**
     * @return "ExtIdHu2" - "Handling Unit's 2nd External Identification"
     */
    public String getExtIdHu2() {
        return extIdHu2;
    }

    /**
     * @return "Brgew" - "Total Weight of Handling Unit"
     */
    public java.math.BigDecimal getBrgew() {
        return brgew;
    }

    /**
     * @return "Gewei" - "Weight Unit"
     */
    public String getGewei() {
        return gewei;
    }

    public void setExtIdHu2(String extIdHu2) {
        this.extIdHu2 = extIdHu2;
    }
}
