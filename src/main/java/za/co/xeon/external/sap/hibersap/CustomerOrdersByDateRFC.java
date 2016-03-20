package za.co.xeon.external.sap.hibersap;

import org.hibersap.annotations.*;

import java.util.List;

/**
 * Created by Derick on 3/14/2016.
 */
@Bapi("Z_GET_CUSTOMER_ORDERS_BY_DATE")
public class CustomerOrdersByDateRFC {
    @Import
    @Parameter("IM_CUSTOMER")
    private final String customerNo;

    @Import
    @Parameter("IM_STATUS_POD")
    private final String podStatus;

    @Export
    @Parameter(value="EV_RETURN", type = ParameterType.STRUCTURE)
    private BapiRet2 returnData;

    @Table
    @Parameter("EV_RESULT")
    private List<Order> orderList;

    public CustomerOrdersByDateRFC(String customerNo, String podStatus) {
        this.customerNo = customerNo;
        this.podStatus = podStatus;
    }

    public BapiRet2 getReturnData() {
        return returnData;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
