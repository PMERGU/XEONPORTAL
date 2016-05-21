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
    private java.lang.String exidv;

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
    private java.lang.String meabm;

    /**
     * "The first status for tracking"
     */
    @Parameter("VEGR1")
    private java.lang.String vegr1;

    /**
     * "The second status for tracking"
     */
    @Parameter("VEGR2")
    private java.lang.String vegr2;

    /**
     * "The step number in the tracking process"
     */
    @Parameter("VEGR3")
    private java.lang.String vegr3;

    /**
     * "Handling Unit's 2nd External Identification"
     */
    @Parameter("EXT_ID_HU_2")
    private java.lang.String extIdHu2;

    /**
     * "Total Weight of Handling Unit"
     */
    @Parameter("BRGEW")
    private java.math.BigDecimal brgew;

    /**
     * "Weight Unit"
     */
    @Parameter("GEWEI")
    private java.lang.String gewei;

    public ImHuupdate(java.lang.String exidv, java.math.BigDecimal laeng, java.math.BigDecimal breit, java.math.BigDecimal hoehe, java.lang.String meabm, java.lang.String vegr1, java.lang.String vegr2, java.lang.String vegr3, java.lang.String extIdHu2, java.math.BigDecimal brgew, java.lang.String gewei) {
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

    public ImHuupdate() {
    }

    /**
     * @return "Exidv" - "External Handling Unit Identification"
     */
    public java.lang.String getExidv() {
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
    public java.lang.String getMeabm() {
        return meabm;
    }

    /**
     * @return "Vegr1" - "The first status for tracking"
     */
    public java.lang.String getVegr1() {
        return vegr1;
    }

    /**
     * @return "Vegr2" - "The second status for tracking"
     */
    public java.lang.String getVegr2() {
        return vegr2;
    }

    /**
     * @return "Vegr3" - "The step number in the tracking process"
     */
    public java.lang.String getVegr3() {
        return vegr3;
    }

    /**
     * @return "ExtIdHu2" - "Handling Unit's 2nd External Identification"
     */
    public java.lang.String getExtIdHu2() {
        return extIdHu2;
    }

    public void setExidv(String exidv) {
        this.exidv = exidv;
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
    public java.lang.String getGewei() {
        return gewei;
    }
}
