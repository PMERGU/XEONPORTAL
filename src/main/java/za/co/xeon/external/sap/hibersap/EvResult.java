package za.co.xeon.external.sap.hibersap;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

/**
 * Created by Derick on 3/21/2016.
 */
@BapiStructure
public class EvResult {
    /**
     * "Customer Number 1"
     */
    @Parameter("SKUNNR")
    private String skunnr;

    /**
     * "Name"
     */
    @Parameter("SNAME1")
    private String sname1;

    /**
     * "Customer purchase order number"
     */
    @Parameter("BSTKD")
    private String bstkd;

    /**
     * "Customer's or vendor's internal reference"
     */
    @Parameter("IHREZ")
    private String ihrez;

    /**
     * "Sales and Distribution Document Number"
     */
    @Parameter("VBELN")
    private String vbeln;

    /**
     * "Name of the controlling area"
     */
    @Parameter("BEZEI")
    private String bezei;

    /**
     * "Partner function"
     */
    @Parameter("PARVW")
    private String parvw;

    /**
     * "Document date (date received/sent)"
     */
    @Parameter("AUDAT")
    private java.util.Date audat;

    /**
     * "Entry time"
     */
    @Parameter("ERZET")
    private java.util.Date erzet;

    /**
     * "Name of Person who Created the Object"
     */
    @Parameter("ERNAM")
    private String ernam;

    /**
     * "Cumulative order quantity in sales units"
     */
    @Parameter("KWMENG")
    private java.math.BigDecimal kwmeng;

    /**
     * "Shipment Number"
     */
    @Parameter("TKNUM")
    private String tknum;

    /**
     * "Current date for start of shipment"
     */
    @Parameter("DATBG")
    private java.util.Date datbg;

    /**
     * "Actual transport start time"
     */
    @Parameter("UATBG")
    private java.util.Date uatbg;

    /**
     * "Actual Date for End of Shipment"
     */
    @Parameter("DATEN")
    private java.util.Date daten;

    /**
     * "Actual shipment end time"
     */
    @Parameter("UATEN")
    private java.util.Date uaten;

    /**
     * "Sales and Distribution Document Number"
     */
    @Parameter("DBELN")
    private String dbeln;

    /**
     * "Status of pick confirmation"
     */
    @Parameter("KOQUK")
    private String koquk;

    /**
     * "Overall status of warehouse management activities"
     */
    @Parameter("LVSTK")
    private String lvstk;

    /**
     * "Total goods movement status"
     */
    @Parameter("WBSTK")
    private String wbstk;

    /**
     * "Transportation planning status"
     */
    @Parameter("TRSTA")
    private String trsta;

    /**
     * "POD status on header level"
     */
    @Parameter("PDSTK")
    private String pdstk;

    /**
     * "Billing status"
     */
    @Parameter("FKSTK")
    private String fkstk;

    /**
     * "Actual quantity delivered (in sales units)"
     */
    @Parameter("LFIMG")
    private java.math.BigDecimal lfimg;

    /**
     * "Sales and Distribution Document Number"
     */
    @Parameter("BELNR")
    private String belnr;

    /**
     * "Amount in document currency"
     */
    @Parameter("WRBTR")
    private java.math.BigDecimal wrbtr;

    /**
     * "Customer Number 1"
     */
    @Parameter("PKUNNR")
    private String pkunnr;

    /**
     * "Name"
     */
    @Parameter("PNAME1")
    private String pname1;

    /**
     * "Customer Number 1"
     */
    @Parameter("BKUNNR")
    private String bkunnr;

    /**
     * "Name"
     */
    @Parameter("BNAME1")
    private String bname1;

    public EvResult(String skunnr, String sname1, String bstkd, String ihrez, String vbeln, String bezei, String parvw, java.util.Date audat, java.util.Date erzet, String ernam, java.math.BigDecimal kwmeng, String tknum, java.util.Date datbg, java.util.Date uatbg, java.util.Date daten, java.util.Date uaten, String dbeln, String koquk, String lvstk, String wbstk, String trsta, String pdstk, String fkstk, java.math.BigDecimal lfimg, String belnr, java.math.BigDecimal wrbtr, String pkunnr, String pname1, String bkunnr, String bname1) {
        this.skunnr = skunnr;
        this.sname1 = sname1;
        this.bstkd = bstkd;
        this.ihrez = ihrez;
        this.vbeln = vbeln;
        this.bezei = bezei;
        this.parvw = parvw;
        this.audat = audat;
        this.erzet = erzet;
        this.ernam = ernam;
        this.kwmeng = kwmeng;
        this.tknum = tknum;
        this.datbg = datbg;
        this.uatbg = uatbg;
        this.daten = daten;
        this.uaten = uaten;
        this.dbeln = dbeln;
        this.koquk = koquk;
        this.lvstk = lvstk;
        this.wbstk = wbstk;
        this.trsta = trsta;
        this.pdstk = pdstk;
        this.fkstk = fkstk;
        this.lfimg = lfimg;
        this.belnr = belnr;
        this.wrbtr = wrbtr;
        this.pkunnr = pkunnr;
        this.pname1 = pname1;
        this.bkunnr = bkunnr;
        this.bname1 = bname1;
    }

    public EvResult() {
    }

    /**
     * @return "Skunnr" - "Customer Number 1"
     */
    public String getSkunnr() {
        return skunnr;
    }

    /**
     * @return "Sname1" - "Name"
     */
    public String getSname1() {
        return sname1;
    }

    /**
     * @return "Bstkd" - "Customer purchase order number"
     */
    public String getBstkd() {
        return bstkd;
    }

    /**
     * @return "Ihrez" - "Customer's or vendor's internal reference"
     */
    public String getIhrez() {
        return ihrez;
    }

    /**
     * @return "Vbeln" - "Sales and Distribution Document Number"
     */
    public String getVbeln() {
        return vbeln;
    }

    /**
     * @return "Bezei" - "Name of the controlling area"
     */
    public String getBezei() {
        return bezei;
    }

    /**
     * @return "Parvw" - "Partner function"
     */
    public String getParvw() {
        return parvw;
    }

    /**
     * @return "Audat" - "Document date (date received/sent)"
     */
    public java.util.Date getAudat() {
        return audat;
    }

    /**
     * @return "Erzet" - "Entry time"
     */
    public java.util.Date getErzet() {
        return erzet;
    }

    /**
     * @return "Ernam" - "Name of Person who Created the Object"
     */
    public String getErnam() {
        return ernam;
    }

    /**
     * @return "Kwmeng" - "Cumulative order quantity in sales units"
     */
    public java.math.BigDecimal getKwmeng() {
        return kwmeng;
    }

    /**
     * @return "Tknum" - "Shipment Number"
     */
    public String getTknum() {
        return tknum;
    }

    /**
     * @return "Datbg" - "Current date for start of shipment"
     */
    public java.util.Date getDatbg() {
        return datbg;
    }

    /**
     * @return "Uatbg" - "Actual transport start time"
     */
    public java.util.Date getUatbg() {
        return uatbg;
    }

    /**
     * @return "Daten" - "Actual Date for End of Shipment"
     */
    public java.util.Date getDaten() {
        return daten;
    }

    /**
     * @return "Uaten" - "Actual shipment end time"
     */
    public java.util.Date getUaten() {
        return uaten;
    }

    /**
     * @return "Dbeln" - "Sales and Distribution Document Number"
     */
    public String getDbeln() {
        return dbeln;
    }

    /**
     * @return "Koquk" - "Status of pick confirmation"
     */
    public String getKoquk() {
        return koquk;
    }

    /**
     * @return "Lvstk" - "Overall status of warehouse management activities"
     */
    public String getLvstk() {
        return lvstk;
    }

    /**
     * @return "Wbstk" - "Total goods movement status"
     */
    public String getWbstk() {
        return wbstk;
    }

    /**
     * @return "Trsta" - "Transportation planning status"
     */
    public String getTrsta() {
        return trsta;
    }

    /**
     * @return "Pdstk" - "POD status on header level"
     */
    public String getPdstk() {
        return pdstk;
    }

    /**
     * @return "Fkstk" - "Billing status"
     */
    public String getFkstk() {
        return fkstk;
    }

    /**
     * @return "Lfimg" - "Actual quantity delivered (in sales units)"
     */
    public java.math.BigDecimal getLfimg() {
        return lfimg;
    }

    /**
     * @return "Belnr" - "Sales and Distribution Document Number"
     */
    public String getBelnr() {
        return belnr;
    }

    /**
     * @return "Wrbtr" - "Amount in document currency"
     */
    public java.math.BigDecimal getWrbtr() {
        return wrbtr;
    }

    /**
     * @return "Pkunnr" - "Customer Number 1"
     */
    public String getPkunnr() {
        return pkunnr;
    }

    /**
     * @return "Pname1" - "Name"
     */
    public String getPname1() {
        return pname1;
    }

    /**
     * @return "Bkunnr" - "Customer Number 1"
     */
    public String getBkunnr() {
        return bkunnr;
    }

    /**
     * @return "Bname1" - "Name"
     */
    public String getBname1() {
        return bname1;
    }
}
