package za.co.xeon.external.sap.hibersap;

import java.util.List;

import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.ParameterType;
import org.hibersap.annotations.Table;
import org.hibersap.bapi.BapiRet2;

import za.co.xeon.external.sap.hibersap.dto.ImHuupdate;

@Bapi("Z_SET_HANDLING_UNITS_UPDATE")
public class ReceivedHandlingUnitsRFC {
    /**
     * "Mobile Handing Unit Field Update" */
    @Import
    @Parameter(
        value = "IM_HUUPDATE",
        type = ParameterType.TABLE_STRUCTURE
    )
    private final List<ImHuupdate> imHuupdate;

    /**
     * "Return Parameter"@return "Return" - "Return Parameter" */
    @Table
    @Parameter("RETURN")
    private List<BapiRet2> returnType;

    public ReceivedHandlingUnitsRFC(List<ImHuupdate> imHuupdate) {
        this.imHuupdate = imHuupdate;
    }

    public List<BapiRet2> getReturn() {
        return returnType;
    }

}
