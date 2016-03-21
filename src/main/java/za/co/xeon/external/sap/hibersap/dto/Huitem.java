package za.co.xeon.external.sap.hibersap.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

/**
 * Created by Derick on 3/20/2016.
 */
@BapiStructure
public class Huitem {
    /**
     * "Client" */
    @Parameter("CLIENT")
    private java.lang.String client;

    /**
     * "Handling Unit Item" */
    @Parameter("HU_ITEM_NUMBER")
    private java.lang.String huItemNumber;

    /**
     * "Type of Handling-unit Item Content" */
    @Parameter("HU_ITEM_TYPE")
    private java.lang.String huItemType;

    /**
     * "Document number of the packed item" */
    @Parameter("OBJECT_DOC")
    private java.lang.String objectDoc;

    /**
     * "Item number" */
    @Parameter("OBJ_ITEM_NUMBER")
    private java.lang.String objItemNumber;

    /**
     * "Base Quantity Packed in the Handling Unit Item" */
    @Parameter("PACK_QTY")
    private java.math.BigDecimal packQty;

    /**
     * "Internal field/ Do not use     / LGMNG in float / MUM" */
    @Parameter("PACK_QTY_FLOAT")
    private java.lang.Double packQtyFloat;

    /**
     * "Base unit of measure of packed quantity (VEMNG) ISO" */
    @Parameter("BASE_UNIT_QTY_ISO")
    private java.lang.String baseUnitQtyIso;

    /**
     * "Base Unit of Measure of the Quantity to be Packed (VEMNG)" */
    @Parameter("BASE_UNIT_QTY")
    private java.lang.String baseUnitQty;

    /**
     * "Alternative unit of measure for stock unit ISO" */
    @Parameter("ALT_UNIT_QTY_ISO")
    private java.lang.String altUnitQtyIso;

    /**
     * "External Handling Unit Identification" */
    @Parameter("LOWER_LEVEL_EXID")
    private java.lang.String lowerLevelExid;

    /**
     * "Alternative unit of measure for stock unit of measure" */
    @Parameter("ALT_UNIT_QTY")
    private java.lang.String altUnitQty;

    /**
     * "Number of Auxiliary Packaging Materials in the HU Item" */
    @Parameter("NUMBER_PACK_MAT")
    private java.lang.Integer numberPackMat;

    /**
     * "Indicator: Supplementary item" */
    @Parameter("FLAG_SUPLMT_ITEM")
    private java.lang.String flagSuplmtItem;

    /**
     * "Material Number" */
    @Parameter("MATERIAL")
    private java.lang.String material;

    /**
     * "Batch Number" */
    @Parameter("BATCH")
    private java.lang.String batch;

    /**
     * "Plant" */
    @Parameter("PLANT")
    private java.lang.String plant;

    /**
     * "Storage Location" */
    @Parameter("STGE_LOC")
    private java.lang.String stgeLoc;

    /**
     * "Configuration (internal object number)" */
    @Parameter("INT_OBJ_NO")
    private java.lang.String intObjNo;

    /**
     * "Stock Category in the Warehouse Management System" */
    @Parameter("STOCK_CAT")
    private java.lang.String stockCat;

    /**
     * "Special Stock Indicator" */
    @Parameter("SPEC_STOCK")
    private java.lang.String specStock;

    /**
     * "Special Stock Number" */
    @Parameter("SP_STCK_NO")
    private java.lang.String spStckNo;

    /**
     * "Inspection Lot Number" */
    @Parameter("INSPLOT")
    private java.lang.String insplot;

    /**
     * "Number of serial numbers" */
    @Parameter("NO_OF_SERIAL_NUMBERS")
    private java.lang.Integer noOfSerialNumbers;

    /**
     * "Serial Number Profile" */
    @Parameter("SERNO_PROF")
    private java.lang.String sernoProf;

    /**
     * "Sales document item category" */
    @Parameter("ITEM_CATEG")
    private java.lang.String itemCateg;

    /**
     * "Material number of partner (customer / supplier)" */
    @Parameter("MATERIAL_PARTNER")
    private java.lang.String materialPartner;

    /**
     * "Packaging Material Type" */
    @Parameter("PACK_MAT_TYPE")
    private java.lang.String packMatType;

    /**
     * "Description of Packaging Material" */
    @Parameter("PACK_MAT_NAME")
    private java.lang.String packMatName;

    /**
     * "Shelf Life Expiration Date" */
    @Parameter("EXPIRYDATE")
    private java.util.Date expirydate;

    /**
     * "Date of Goods Receipt" */
    @Parameter("GR_DATE")
    private java.util.Date grDate;

    /**
     * "External Handling Unit Identification" */
    @Parameter("HU_EXID")
    private java.lang.String huExid;

    public Huitem(java.lang.String client, java.lang.String huItemNumber, java.lang.String huItemType, java.lang.String objectDoc, java.lang.String objItemNumber, java.math.BigDecimal packQty, java.lang.Double packQtyFloat, java.lang.String baseUnitQtyIso, java.lang.String baseUnitQty, java.lang.String altUnitQtyIso, java.lang.String lowerLevelExid, java.lang.String altUnitQty, java.lang.Integer numberPackMat, java.lang.String flagSuplmtItem, java.lang.String material, java.lang.String batch, java.lang.String plant, java.lang.String stgeLoc, java.lang.String intObjNo, java.lang.String stockCat, java.lang.String specStock, java.lang.String spStckNo, java.lang.String insplot, java.lang.Integer noOfSerialNumbers, java.lang.String sernoProf, java.lang.String itemCateg, java.lang.String materialPartner, java.lang.String packMatType, java.lang.String packMatName, java.util.Date expirydate, java.util.Date grDate, java.lang.String huExid) {
        this.client = client;
        this.huItemNumber = huItemNumber;
        this.huItemType = huItemType;
        this.objectDoc = objectDoc;
        this.objItemNumber = objItemNumber;
        this.packQty = packQty;
        this.packQtyFloat = packQtyFloat;
        this.baseUnitQtyIso = baseUnitQtyIso;
        this.baseUnitQty = baseUnitQty;
        this.altUnitQtyIso = altUnitQtyIso;
        this.lowerLevelExid = lowerLevelExid;
        this.altUnitQty = altUnitQty;
        this.numberPackMat = numberPackMat;
        this.flagSuplmtItem = flagSuplmtItem;
        this.material = material;
        this.batch = batch;
        this.plant = plant;
        this.stgeLoc = stgeLoc;
        this.intObjNo = intObjNo;
        this.stockCat = stockCat;
        this.specStock = specStock;
        this.spStckNo = spStckNo;
        this.insplot = insplot;
        this.noOfSerialNumbers = noOfSerialNumbers;
        this.sernoProf = sernoProf;
        this.itemCateg = itemCateg;
        this.materialPartner = materialPartner;
        this.packMatType = packMatType;
        this.packMatName = packMatName;
        this.expirydate = expirydate;
        this.grDate = grDate;
        this.huExid = huExid;
    }

    public Huitem() {
    }

    /**
     * @return "Client" - "Client" */
    public java.lang.String getClient() {
        return client;
    }

    /**
     * @return "HuItemNumber" - "Handling Unit Item" */
    public java.lang.String getHuItemNumber() {
        return huItemNumber;
    }

    /**
     * @return "HuItemType" - "Type of Handling-unit Item Content" */
    public java.lang.String getHuItemType() {
        return huItemType;
    }

    /**
     * @return "ObjectDoc" - "Document number of the packed item" */
    public java.lang.String getObjectDoc() {
        return objectDoc;
    }

    /**
     * @return "ObjItemNumber" - "Item number" */
    public java.lang.String getObjItemNumber() {
        return objItemNumber;
    }

    /**
     * @return "PackQty" - "Base Quantity Packed in the Handling Unit Item" */
    public java.math.BigDecimal getPackQty() {
        return packQty;
    }

    /**
     * @return "PackQtyFloat" - "Internal field/ Do not use     / LGMNG in float / MUM" */
    public java.lang.Double getPackQtyFloat() {
        return packQtyFloat;
    }

    /**
     * @return "BaseUnitQtyIso" - "Base unit of measure of packed quantity (VEMNG) ISO" */
    public java.lang.String getBaseUnitQtyIso() {
        return baseUnitQtyIso;
    }

    /**
     * @return "BaseUnitQty" - "Base Unit of Measure of the Quantity to be Packed (VEMNG)" */
    public java.lang.String getBaseUnitQty() {
        return baseUnitQty;
    }

    /**
     * @return "AltUnitQtyIso" - "Alternative unit of measure for stock unit ISO" */
    public java.lang.String getAltUnitQtyIso() {
        return altUnitQtyIso;
    }

    /**
     * @return "LowerLevelExid" - "External Handling Unit Identification" */
    public java.lang.String getLowerLevelExid() {
        return lowerLevelExid;
    }

    /**
     * @return "AltUnitQty" - "Alternative unit of measure for stock unit of measure" */
    public java.lang.String getAltUnitQty() {
        return altUnitQty;
    }

    /**
     * @return "NumberPackMat" - "Number of Auxiliary Packaging Materials in the HU Item" */
    public java.lang.Integer getNumberPackMat() {
        return numberPackMat;
    }

    /**
     * @return "FlagSuplmtItem" - "Indicator: Supplementary item" */
    public java.lang.String getFlagSuplmtItem() {
        return flagSuplmtItem;
    }

    /**
     * @return "Material" - "Material Number" */
    public java.lang.String getMaterial() {
        return material;
    }

    /**
     * @return "Batch" - "Batch Number" */
    public java.lang.String getBatch() {
        return batch;
    }

    /**
     * @return "Plant" - "Plant" */
    public java.lang.String getPlant() {
        return plant;
    }

    /**
     * @return "StgeLoc" - "Storage Location" */
    public java.lang.String getStgeLoc() {
        return stgeLoc;
    }

    /**
     * @return "IntObjNo" - "Configuration (internal object number)" */
    public java.lang.String getIntObjNo() {
        return intObjNo;
    }

    /**
     * @return "StockCat" - "Stock Category in the Warehouse Management System" */
    public java.lang.String getStockCat() {
        return stockCat;
    }

    /**
     * @return "SpecStock" - "Special Stock Indicator" */
    public java.lang.String getSpecStock() {
        return specStock;
    }

    /**
     * @return "SpStckNo" - "Special Stock Number" */
    public java.lang.String getSpStckNo() {
        return spStckNo;
    }

    /**
     * @return "Insplot" - "Inspection Lot Number" */
    public java.lang.String getInsplot() {
        return insplot;
    }

    /**
     * @return "NoOfSerialNumbers" - "Number of serial numbers" */
    public java.lang.Integer getNoOfSerialNumbers() {
        return noOfSerialNumbers;
    }

    /**
     * @return "SernoProf" - "Serial Number Profile" */
    public java.lang.String getSernoProf() {
        return sernoProf;
    }

    /**
     * @return "ItemCateg" - "Sales document item category" */
    public java.lang.String getItemCateg() {
        return itemCateg;
    }

    /**
     * @return "MaterialPartner" - "Material number of partner (customer / supplier)" */
    public java.lang.String getMaterialPartner() {
        return materialPartner;
    }

    /**
     * @return "PackMatType" - "Packaging Material Type" */
    public java.lang.String getPackMatType() {
        return packMatType;
    }

    /**
     * @return "PackMatName" - "Description of Packaging Material" */
    public java.lang.String getPackMatName() {
        return packMatName;
    }

    /**
     * @return "Expirydate" - "Shelf Life Expiration Date" */
    public java.util.Date getExpirydate() {
        return expirydate;
    }

    /**
     * @return "GrDate" - "Date of Goods Receipt" */
    public java.util.Date getGrDate() {
        return grDate;
    }

    /**
     * @return "HuExid" - "External Handling Unit Identification" */
    public java.lang.String getHuExid() {
        return huExid;
    }
}
