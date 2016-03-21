package za.co.xeon.external.sap.hibersap;

import java.lang.String;
import java.util.List;
import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.Export;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.ParameterType;
import org.hibersap.bapi.BapiRet2;

@Bapi("Z_GET_CUSTOMER_ORDERS_BY_DATE")
public class CustomerOrdersByDateRFC {
    /**
     * "Customer Number 1" */
    @Import
    @Parameter("IM_CUSTOMER")
    private final String imCustomer;

    /**
     * "Range Table for Data Element SYDATUM" */
    @Import
    @Parameter("IM_DATE_R")
    private final List<ImDateR> imDateR;

    /**
     * "POD status on header level" */
    @Import
    @Parameter("IM_STATUS_POD")
    private final String imStatusPod;

    /**
     * "Trace tool table type" */
    @Export
    @Parameter("EV_RESULT")
    private List<EvResult> evResult;

    /**
     * "Return Parameter"@return "EvReturn" - "Return Parameter" */
    @Export
    @Parameter(
        value = "EV_RETURN",
        type = ParameterType.STRUCTURE
    )
    private BapiRet2 evReturnType;

    /**
     * @param "imCustomer" - "Customer Number 1"@param "imStatusPod" - "POD status on header level" */
    public CustomerOrdersByDateRFC(String imCustomer, List<ImDateR> imDateR, String imStatusPod) {
        this.imCustomer = imCustomer;
        this.imDateR = imDateR;
        this.imStatusPod = imStatusPod;
    }

    public List<EvResult> getEvResult() {
        return evResult;
    }

    public BapiRet2 getEvReturn() {
        return evReturnType;
    }

}
