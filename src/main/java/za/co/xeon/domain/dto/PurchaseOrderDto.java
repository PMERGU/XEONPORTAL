package za.co.xeon.domain.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Derick on 3/25/2016.
 */
public class PurchaseOrderDto implements Serializable {
    private List<SalesOrderDto> salesOrders;

    public PurchaseOrderDto() {
    }

    public PurchaseOrderDto(List<SalesOrderDto> salesOrders) {
        this.salesOrders = salesOrders;
    }

    public List<SalesOrderDto> getSalesOrders() {
        return salesOrders;
    }

    public void setSalesOrders(List<SalesOrderDto> salesOrders) {
        this.salesOrders = salesOrders;
    }
}
