package za.co.xeon.web.rest.dto;

import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.external.sap.hibersap.dto.GtCustOrdersDetail;

import java.util.List;

/**
 * Created by Derick on 9/10/2016.
 */
public class OrderDetails{
    private PurchaseOrder purchaseOrder;
    private List<GtCustOrdersDetail> sapOrders;

    public OrderDetails(PurchaseOrder purchaseOrder, List<GtCustOrdersDetail> sapOrders) {
        this.purchaseOrder = purchaseOrder;
        this.sapOrders = sapOrders;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public List<GtCustOrdersDetail> getSapOrders() {
        return sapOrders;
    }

    public void setSapOrders(List<GtCustOrdersDetail> sapOrders) {
        this.sapOrders = sapOrders;
    }
}
