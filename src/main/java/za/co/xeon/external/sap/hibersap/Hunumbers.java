package za.co.xeon.external.sap.hibersap;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

/**
 * Created by Derick on 3/20/2016.
 */
@BapiStructure
public class Hunumbers {
    /**
     * "External Handling Unit Identification" */
    @Parameter("HU_EXID")
    private java.lang.String huExid;

    public Hunumbers(java.lang.String huExid) {
        this.huExid = huExid;
    }

    public Hunumbers() {
    }

    /**
     * @return "HuExid" - "External Handling Unit Identification" */
    public java.lang.String getHuExid() {
        return huExid;
    }

}
