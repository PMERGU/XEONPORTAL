package za.co.xeon.external.sap.hibersap;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

import java.math.BigDecimal;
import java.util.Date;

@BapiStructure
public class Order {

    @Parameter("SKUNNR")
    private String skuNumber;

    @Parameter("SNAME1")
    private String name;

    @Parameter("BSTKD")
    private String purchaseOrderNumber;

    @Parameter("IHREZ")
    private String customerReference;

    @Parameter("VBELN")
    private String salesNumber;

    @Parameter("BEZEI")
    private String controllingArea;

    @Parameter("PARVW")
    private String partnerFunction;

    @Parameter("AUDAT")
    private Date entryDate;

    @Parameter("ERZET")
    private Date entryTime;

    @Parameter("ERNAM")
    private String capturedBy;

    @Parameter("KWMENG")
    private String quantity;

    @Parameter("TKNUM")
    private String shipmentNumber;

    @Parameter("DATBG")
    private Date shipmentDate;

    @Parameter("UATBG")
    private String shipmentTime;

    @Parameter("DATEN")
    private Date deliveryDate; ;

    @Parameter("UATEN")
    private Date deliveryTime ;

    @Parameter("DBELN")
    private String documenNumber;

    @Parameter("KOQUK")
    private String pickupStatus;

    @Parameter("LVSTK")
    private String warehouseStatus;

    @Parameter("WBSTK")
    private String goodsMovementStatus;

    @Parameter("TRSTA")
    private String transportPlanningStatus;

    @Parameter("PDSTK")
    private String podStatus;

    @Parameter("FKSTK")
    private String billingStatus;

    @Parameter("LFIMG")
    private String quantityDelivered;

    @Parameter("BELNR")
    private String documenNumber2;

    @Parameter("WRBTR")
    private BigDecimal documentCurrency;

    @Parameter("PKUNNR")
    private String pkunnr;

    @Parameter("PNAME1")
    private String pname1;

    @Parameter("BKUNNR")
    private String bkunnr;

    @Parameter("BNAME1")
    private String bname1;

    public String getSkuNumber() {
        return skuNumber;
    }

    public String getName() {
        return name;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public String getControllingArea() {
        return controllingArea;
    }

    public String getPartnerFunction() {
        return partnerFunction;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public String getCapturedBy() {
        return capturedBy;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public String getShipmentTime() {
        return shipmentTime;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public String getDocumenNumber() {
        return documenNumber;
    }

    public String getPickupStatus() {
        return pickupStatus;
    }

    public String getWarehouseStatus() {
        return warehouseStatus;
    }

    public String getGoodsMovementStatus() {
        return goodsMovementStatus;
    }

    public String getTransportPlanningStatus() {
        return transportPlanningStatus;
    }

    public String getPodStatus() {
        return podStatus;
    }

    public String getBillingStatus() {
        return billingStatus;
    }

    public String getQuantityDelivered() {
        return quantityDelivered;
    }

    public String getDocumenNumber2() {
        return documenNumber2;
    }

    public BigDecimal getDocumentCurrency() {
        return documentCurrency;
    }

    public String getPkunnr() {
        return pkunnr;
    }

    public String getPname1() {
        return pname1;
    }

    public String getBkunnr() {
        return bkunnr;
    }

    public String getBname1() {
        return bname1;
    }
}
