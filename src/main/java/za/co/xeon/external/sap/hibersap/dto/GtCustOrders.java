package za.co.xeon.external.sap.hibersap.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

/**
 * Created by derick on 2016/09/02.
 */
@BapiStructure
public class GtCustOrders {
    /**
     * "Customer Number 1" */
    @Parameter("SKUNNR")
    private java.lang.String skunnr;

    /**
     * "Sales and Distribution Document Number" */
    @Parameter("VBELN")
    private java.lang.String vbeln;

    /**
     * "Sales and Distribution Document Number" */
    @Parameter("DBELN")
    private java.lang.String dbeln;

    /**
     * "Customer purchase order number" */
    @Parameter("BSTKD")
    private java.lang.String bstkd;

    public GtCustOrders(java.lang.String skunnr, java.lang.String vbeln, java.lang.String dbeln, java.lang.String bstkd) {
        this.skunnr = skunnr;
        this.vbeln = vbeln;
        this.dbeln = dbeln;
        this.bstkd = bstkd;
    }

    public GtCustOrders() {
    }

    /**
     * @return "Skunnr" - "Customer Number 1" */
    public java.lang.String getSkunnr() {
        return skunnr;
    }

    /**
     * @return "Vbeln" - "Sales and Distribution Document Number" */
    public java.lang.String getVbeln() {
        return vbeln;
    }

    /**
     * @return "Dbeln" - "Sales and Distribution Document Number" */
    public java.lang.String getDbeln() {
        return dbeln;
    }

    /**
     * @return "Bstkd" - "Customer purchase order number" */
    public java.lang.String getBstkd() {
        return bstkd;
    }
}
