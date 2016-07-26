package za.co.xeon.external.sap.hibersap;

import java.lang.String;
import java.util.List;
import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.Export;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.ParameterType;
import za.co.xeon.external.sap.hibersap.dto.GtCustOrdersDetail;
import za.co.xeon.external.sap.hibersap.dto.GtReturn;
import za.co.xeon.external.sap.hibersap.dto.ImDateR;

@Bapi("Z_GET_CUST_ORDERS_DETAIL")
public class CustOrdersDetailRFC {
    /**
     * "Range Table for Data Element SYDATUM" */
    @Import
    @Parameter(
        value = "IM_DATE_R",
        type = ParameterType.TABLE_STRUCTURE
    )
    private final List
        <ImDateR> imDateR;

    /**
     * "Sales and Distribution Document Number" */
    @Import
    @Parameter("IM_VBELN")
    private final String imVbeln;

    /**
     * "Customer Orders" */
    @Export
    @Parameter(
        value = "GT_CUST_ORDERS_DETAIL",
        type = ParameterType.TABLE_STRUCTURE
    )
    private List
        <GtCustOrdersDetail> gtCustOrdersDetail;

    /**
     * "Return Parameter" */
    @Parameter(
        value = "GT_RETURN",
        type = ParameterType.STRUCTURE
    )
    @Export
    private GtReturn gtReturn;

    /**
     * @param "imVbeln" - "Sales and Distribution Document Number" */
    public CustOrdersDetailRFC(List<ImDateR> imDateR, String imVbeln) {
        this.imDateR = imDateR;
        this.imVbeln = imVbeln;
    }

    public List
        <GtCustOrdersDetail> getGtCustOrdersDetail() {
        return gtCustOrdersDetail;
    }

    public GtReturn getGtReturn() {
        return gtReturn;
    }

}
