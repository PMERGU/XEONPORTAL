package za.co.xeon.external.sap.hibersap;

import java.lang.String;
import java.util.List;
import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Export;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.ParameterType;
import za.co.xeon.external.sap.hibersap.dto.GtCustOrders;
import za.co.xeon.external.sap.hibersap.dto.GtReturn;
import za.co.xeon.external.sap.hibersap.dto.ImDateR;

@Bapi("Z_GET_CUST_ORDERS")
public class CustOrdersRFC {
    /**
     * "Customer Number 1"
     * */
    @Import
    @Parameter("IM_CUSTOMER")
    private final String imCustomer;

    /**
     * "Range Table for Data Element SYDATUM"
     * */
    @Import
    @Parameter(
        value = "IM_DATE_R",
        type = ParameterType.TABLE_STRUCTURE
    )
    private final List
        <ImDateR> imDateR;

    /**
     * "POD status on header level"
     * */
    @Import
    @Parameter("IM_STATUS_POD")
    private final String imStatusPod;

    /**
     * "Return Parameter"
     * */
    @Parameter(
        value = "GT_RETURN",
        type = ParameterType.STRUCTURE
    )
    @Export
    private GtReturn gtReturn;

    /**
     * @param "imCustomer" - "Customer Number 1"@param "imStatusPod" - "POD status on header level"
     * */
    public CustOrdersRFC(String imCustomer, List
        <ImDateR> imDateR, String imStatusPod) {
        this.imCustomer = imCustomer;
        this.imDateR = imDateR;
        this.imStatusPod = imStatusPod;
    }

    public List
        <GtCustOrders> getGtCustOrders() {
        return gtCustOrders;
    }

    /**
     * "Customer Orders"
     * */
    @Export
    @Parameter(
        value = "GT_CUST_ORDERS",
        type = ParameterType.TABLE_STRUCTURE
    )
    private List<GtCustOrders> gtCustOrders;

    public GtReturn getGtReturn() {
        return gtReturn;
    }
}
