package za.co.xeon.external.sap.hibersap;

import java.util.List;

import org.hibersap.annotations.*;
import org.hibersap.bapi.BapiRet2;
import za.co.xeon.external.sap.hibersap.dto.ImHuitem;

@Bapi("Z_SET_HANDLING_UNITS")
public class UpdateHandlingUnitsRFC {
    /**
     * "table type for handling unit" */
    @Import
    @Parameter(value="IM_HUITEM", type = ParameterType.TABLE_STRUCTURE)
    private final List<ImHuitem> imHuitem;

    /**
     * "Return parameter table"@return "Return" - "Return parameter table" */
    @Table
    @Parameter("RETURN")
    private List<BapiRet2> returnType;

    public UpdateHandlingUnitsRFC(List<ImHuitem> imHuitem) {
        this.imHuitem = imHuitem;
    }

    public List<BapiRet2> getReturn() {
        return returnType;
    }

}
