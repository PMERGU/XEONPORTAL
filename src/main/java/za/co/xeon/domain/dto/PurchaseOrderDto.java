package za.co.xeon.domain.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Derick on 3/25/2016.
 */
public class PurchaseOrderDto implements Serializable {
    Map<String, SalesOrderDto> salesOrders = new HashMap<>();

    public PurchaseOrderDto() {
    }

    public PurchaseOrderDto(Map<String, SalesOrderDto> salesOrders) {
        this.salesOrders = salesOrders;
    }

    public Map<String, SalesOrderDto> getSalesOrders() {
        return salesOrders;
    }

    public void setSalesOrders(Map<String, SalesOrderDto> salesOrders) {
        this.salesOrders = salesOrders;
    }

    public static PurchaseOrderDto newPurchaseOrder(String salesOrder, SalesOrderDto salesOrderDto){
        Map<String, SalesOrderDto> salesOrders = new HashMap<>();
        salesOrders.put(salesOrder, salesOrderDto);
        return new PurchaseOrderDto(salesOrders);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseOrderDto that = (PurchaseOrderDto) o;

        return salesOrders != null ? salesOrders.equals(that.salesOrders) : that.salesOrders == null;

    }

    @Override
    public int hashCode() {
        return salesOrders != null ? salesOrders.hashCode() : 0;
    }
}
