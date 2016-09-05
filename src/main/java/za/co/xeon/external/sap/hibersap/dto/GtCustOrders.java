package za.co.xeon.external.sap.hibersap.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

/**
 * Created by derick on 2016/09/02.
 */
@BapiStructure
public class GtCustOrders {
    /**
     * "Customer Number 1"
     */
    @Parameter("SKUNNR")
    private String skunnr;

    /**
     * "Sales and Distribution Document Number"
     */
    @Parameter("VBELN")
    private String vbeln;

    /**
     * "Sales and Distribution Document Number"
     */
    @Parameter("DBELN")
    private String dbeln;

    public GtCustOrders(String skunnr, String vbeln, String dbeln) {
        this.skunnr = skunnr;
        this.vbeln = vbeln;
        this.dbeln = dbeln;
    }

    public GtCustOrders() {
    }

    /**
     * @return "Skunnr" - "Customer Number 1"
     */
    public String getSkunnr() {
        return skunnr;
    }

    /**
     * @return "Vbeln" - "Sales and Distribution Document Number"
     */
    public String getVbeln() {
        return vbeln;
    }

    /**
     * @return "Dbeln" - "Sales and Distribution Document Number"
     */
    public String getDbeln() {
        return dbeln;
    }
}
