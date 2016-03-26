package za.co.xeon.external.sap.hibersap.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

/**
 * Created by Derick on 3/26/2016.
 */
@BapiStructure
public class ImHuitem {
    /**
     * "Sales and Distribution Document Number"
     */
    @Parameter("DBELN")
    private String dbeln;

    /**
     * "External Handling Unit Identification"
     */
    @Parameter("HU_EXID")
    private String huExid;

    /**
     * "Date"
     */
    @Parameter("DATUM")
    private java.util.Date datum;

    /**
     * "Time"
     */
    @Parameter("UZEIT")
    private java.util.Date uzeit;

    public ImHuitem(String dbeln, String huExid, java.util.Date datum, java.util.Date uzeit) {
        this.dbeln = dbeln;
        this.huExid = huExid;
        this.datum = datum;
        this.uzeit = uzeit;
    }

    public ImHuitem() {
    }

    /**
     * @return "Dbeln" - "Sales and Distribution Document Number"
     */
    public String getDbeln() {
        return dbeln;
    }

    /**
     * @return "HuExid" - "External Handling Unit Identification"
     */
    public String getHuExid() {
        return huExid;
    }

    /**
     * @return "Datum" - "Date"
     */
    public java.util.Date getDatum() {
        return datum;
    }

    /**
     * @return "Uzeit" - "Time"
     */
    public java.util.Date getUzeit() {
        return uzeit;
    }
}
