package za.co.xeon.external.sap.hibersap.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

/**
 * Created by Derick on 3/20/2016.
 */
@BapiStructure
public class Huheader {
    /**
     * Client name and number
     * */
    @Parameter("CLIENT")
    private java.lang.String client;

    /**
     * "Internal Handling Unit Number" */
    @Parameter("HU_ID")
    private java.lang.String huId;

    /**
     * "External Handling Unit Identification" */
    @Parameter("HU_EXID")
    private java.lang.String huExid;

    /**
     * "Type of External Handling Unit Identifier" */
    @Parameter("HU_EXID_TYPE")
    private java.lang.String huExidType;

    /**
     * "Shipping Point/Receiving Point" */
    @Parameter("SHIP_POINT")
    private java.lang.String shipPoint;

    /**
     * "Loading Point" */
    @Parameter("LOADING_POINT")
    private java.lang.String loadingPoint;

    /**
     * "Total Weight of Handling Unit" */
    @Parameter("TOTAL_WGHT")
    private java.math.BigDecimal totalWght;

    /**
     * "Loading Weight of Handling Unit" */
    @Parameter("LOAD_WGHT")
    private java.math.BigDecimal loadWght;

    /**
     * "Tare weight of handling unit" */
    @Parameter("TARE_WGHT")
    private java.math.BigDecimal tareWght;

    /**
     * "Unit of weight in ISO code" */
    @Parameter("UNIT_OF_WT_ISO")
    private java.lang.String unitOfWtIso;

    /**
     * "Weight Unit" */
    @Parameter("UNIT_OF_WT")
    private java.lang.String unitOfWt;

    /**
     * "Allowed Loading Weight of a Handling Unit" */
    @Parameter("ALLOWED_WGHT")
    private java.math.BigDecimal allowedWght;

    /**
     * "Unit of weight in ISO code" */
    @Parameter("MAX_UNIT_OF_WGHT_ISO")
    private java.lang.String maxUnitOfWghtIso;

    /**
     * "Weight Unit" */
    @Parameter("MAX_UNIT_OF_WGHT")
    private java.lang.String maxUnitOfWght;

    /**
     * "Total Volume of Handling Unit" */
    @Parameter("TOTAL_VOL")
    private java.math.BigDecimal totalVol;

    /**
     * "Loading Volume of Handling Unit" */
    @Parameter("LOAD_VOL")
    private java.math.BigDecimal loadVol;

    /**
     * "Tare volume of handling unit" */
    @Parameter("TARE_VOL")
    private java.math.BigDecimal tareVol;

    /**
     * "Volume unit in ISO code" */
    @Parameter("VOLUMEUNIT_ISO")
    private java.lang.String volumeunitIso;

    /**
     * "Volume unit" */
    @Parameter("VOLUMEUNIT")
    private java.lang.String volumeunit;

    /**
     * "Allowed Loading Volume for Handling Unit" */
    @Parameter("ALLOWED_VOL")
    private java.math.BigDecimal allowedVol;

    /**
     * "Volume unit in ISO code" */
    @Parameter("MAX_VOL_UNIT_ISO")
    private java.lang.String maxVolUnitIso;

    /**
     * "Volume unit" */
    @Parameter("MAX_VOL_UNIT")
    private java.lang.String maxVolUnit;

    /**
     * "Number of Similar Packaging Materials" */
    @Parameter("NO_SIMILAR_PACK_MAT")
    private java.lang.Integer noSimilarPackMat;

    /**
     * "Name of Person who Created the Object" */
    @Parameter("CREATED_BY")
    private java.lang.String createdBy;

    /**
     * "Date on which the record was created" */
    @Parameter("CREATED_DATE")
    private java.util.Date createdDate;

    /**
     * "Entry time" */
    @Parameter("CREATED_TIME")
    private java.util.Date createdTime;

    /**
     * "Name of person who changed object" */
    @Parameter("CHANGED_BY")
    private java.lang.String changedBy;

    /**
     * "Date of Last Change" */
    @Parameter("CHANGED_DATE")
    private java.util.Date changedDate;

    /**
     * "Time last change was made" */
    @Parameter("CHANGED_TIME")
    private java.util.Date changedTime;

    /**
     * "Sort field" */
    @Parameter("SORT_FLD")
    private java.lang.String sortFld;

    /**
     * "Handling Unit Group 1                     (Freely Definable)" */
    @Parameter("HU_GRP1")
    private java.lang.String huGrp1;

    /**
     * "Handling Unit Group 2                     (Freely Definable)" */
    @Parameter("HU_GRP2")
    private java.lang.String huGrp2;

    /**
     * "Handling Unit Group 3                     (Freely Definable)" */
    @Parameter("HU_GRP3")
    private java.lang.String huGrp3;

    /**
     * "Handling Unit Group 4                     (Freely Definable)" */
    @Parameter("HU_GRP4")
    private java.lang.String huGrp4;

    /**
     * "Handling Unit Group 5                     (Freely Definable)" */
    @Parameter("HU_GRP5")
    private java.lang.String huGrp5;

    /**
     * "Packaging Materials" */
    @Parameter("PACK_MAT")
    private java.lang.String packMat;

    /**
     * "Length" */
    @Parameter("LENGHT")
    private java.math.BigDecimal lenght;

    /**
     * "Width" */
    @Parameter("WIDTH")
    private java.math.BigDecimal width;

    /**
     * "Height" */
    @Parameter("HEIGHT")
    private java.math.BigDecimal height;

    /**
     * "Unit for length/breadth/height in ISO code" */
    @Parameter("UNIT_DIM_ISO")
    private java.lang.String unitDimIso;

    /**
     * "Unit of dimension for length/width/height" */
    @Parameter("UNIT_DIM")
    private java.lang.String unitDim;

    /**
     * "Status (at this time without functionality)" */
    @Parameter("STATUS_OBSOLET")
    private java.lang.String statusObsolet;

    /**
     * "Excess Weight Tolerance for Handling unit" */
    @Parameter("WGHT_TOL_HU")
    private java.math.BigDecimal wghtTolHu;

    /**
     * "Excess Volume Tolerance of the Handling Unit" */
    @Parameter("VOL_TOL_HU")
    private java.math.BigDecimal volTolHu;

    /**
     * "Base unit of measure in ISO code" */
    @Parameter("BASE_UOM_ISO")
    private java.lang.String baseUomIso;

    /**
     * "Base Unit of Measure" */
    @Parameter("BASE_UOM")
    private java.lang.String baseUom;

    /**
     * "Description of Handling Unit Content" */
    @Parameter("CONTENT")
    private java.lang.String content;

    /**
     * "Packaging Material Type" */
    @Parameter("PACK_MAT_TYPE")
    private java.lang.String packMatType;

    /**
     * "Material Group: Packaging Materials" */
    @Parameter("MAT_GRP_SM")
    private java.lang.String matGrpSm;

    /**
     * "Plant" */
    @Parameter("PLANT")
    private java.lang.String plant;

    /**
     * "Sales document item category" */
    @Parameter("ITEM_CATEG")
    private java.lang.String itemCateg;

    /**
     * "Sales Organization" */
    @Parameter("SALESORG")
    private java.lang.String salesorg;

    /**
     * "Reference distrib.channel for cust.and material masters" */
    @Parameter("DC_CUSTOM_MAT")
    private java.lang.String dcCustomMat;

    /**
     * "Lgth of loading platform in lgth of LdPlat measurement units" */
    @Parameter("LGTH_LOAD")
    private java.math.BigDecimal lgthLoad;

    /**
     * "Load unit of measure in ISO code" */
    @Parameter("LGTH_LOAD_UNIT_ISO")
    private java.lang.String lgthLoadUnitIso;

    /**
     * "Unit of measure to measure the length of loading platform" */
    @Parameter("LGTH_LOAD_UNIT")
    private java.lang.String lgthLoadUnit;

    /**
     * "Travel Time" */
    @Parameter("TRAVEL_TIME")
    private java.math.BigDecimal travelTime;

    /**
     * "Travel time unit in ISO code" */
    @Parameter("TRAVEL_TIME_UNIT_ISO")
    private java.lang.String travelTimeUnitIso;

    /**
     * "Unit of travel time" */
    @Parameter("TRAVEL_TIME_UNIT")
    private java.lang.String travelTimeUnit;

    /**
     * "Distance Travelled" */
    @Parameter("DISTANCE")
    private java.math.BigDecimal distance;

    /**
     * "Travel time unit in ISO code" */
    @Parameter("UNIT_OF_DIST_ISO")
    private java.lang.String unitOfDistIso;

    /**
     * "Unit of distance" */
    @Parameter("UNIT_OF_DIST")
    private java.lang.String unitOfDist;

    /**
     * "Storage Location" */
    @Parameter("STGE_LOC")
    private java.lang.String stgeLoc;

    /**
     * "Weight and Volume Fixed" */
    @Parameter("WGHT_VOL_FIX")
    private java.lang.String wghtVolFix;

    /**
     * "Packaging Material Category" */
    @Parameter("PACK_MAT_CAT")
    private java.lang.String packMatCat;

    /**
     * "Handling Unit's 2nd External Identification" */
    @Parameter("EXT_ID_HU_2")
    private java.lang.String extIdHu2;

    /**
     * "Country of means of transport ISO code" */
    @Parameter("CNTRY_SHP_MAT_ISO")
    private java.lang.String cntryShpMatIso;

    /**
     * "Country providing means of transport" */
    @Parameter("CNTRY_SHP_MAT")
    private java.lang.String cntryShpMat;

    /**
     * "Driver's nationality ISO code" */
    @Parameter("NATIONALITY_DRIVER_ISO")
    private java.lang.String nationalityDriverIso;

    /**
     * "Driver's Nationality" */
    @Parameter("NATIONALITY_DRIVER")
    private java.lang.String nationalityDriver;

    /**
     * "Driver name" */
    @Parameter("NAME_DRIVER")
    private java.lang.String nameDriver;

    /**
     * "Alternate Driver's Name" */
    @Parameter("NAME_CO_DRIVER")
    private java.lang.String nameCoDriver;

    /**
     * "Partner's (Customer's or Vendor's) Packaging Material" */
    @Parameter("PACK_MAT_CUSTOMER")
    private java.lang.String packMatCustomer;

    /**
     * "Packing Object" */
    @Parameter("PACK_MAT_OBJECT")
    private java.lang.String packMatObject;

    /**
     * "Key for Object to Which the Handling Unit is Assigned" */
    @Parameter("PACK_MAT_OBJ_KEY")
    private java.lang.String packMatObjKey;

    /**
     * "Worldwide unique key for VEKP-VENUM" */
    @Parameter("HANDLE")
    private java.lang.String handle;

    /**
     * "WM status for container material" */
    @Parameter("CONTAINER_STAT")
    private java.lang.String containerStat;

    /**
     * "Warehouse Number / Warehouse Complex" */
    @Parameter("WAREHOUSE_NUMBER")
    private java.lang.String warehouseNumber;

    /**
     * "Packaging Material is Closed Packaging" */
    @Parameter("CLOSED_BOX")
    private java.lang.String closedBox;

    /**
     * "Packing Level is Relevant for Dangerous Goods" */
    @Parameter("FLAG_PACKG_LV_DANG_GOODS")
    private java.lang.String flagPackgLvDangGoods;

    /**
     * "Indicator: Packing level is relevant for printing" */
    @Parameter("FLAG_PACKG_LV_PRINT")
    private java.lang.String flagPackgLvPrint;

    /**
     * "Higher-Level Handling Unit" */
    @Parameter("HIGHER_LEVEL_HU")
    private java.lang.String higherLevelHu;

    /**
     * "Packing instruction" */
    @Parameter("PACKG_INSTRUCT")
    private java.lang.String packgInstruct;

    /**
     * "Local packing status for a single HU (without sub HUs)" */
    @Parameter("L_PACKG_STATUS_HU")
    private java.lang.String lPackgStatusHu;

    /**
     * "Indicator: do not print external shipping label" */
    @Parameter("FLAG_NO_EXT_LABLE")
    private java.lang.String flagNoExtLable;

    /**
     * "Allowed load" */
    @Parameter("PERMISS_WORKLOAD")
    private java.math.BigDecimal permissWorkload;

    /**
     * "Content is Located at an HU-managed Storage Location" */
    @Parameter("HU_STOR_LOC")
    private java.lang.String huStorLoc;

    /**
     * "Description of Packaging Material" */
    @Parameter("PACK_MAT_NAME")
    private java.lang.String packMatName;

    public Huheader(java.lang.String client, java.lang.String huId, java.lang.String huExid, java.lang.String huExidType, java.lang.String shipPoint, java.lang.String loadingPoint, java.math.BigDecimal totalWght, java.math.BigDecimal loadWght, java.math.BigDecimal tareWght, java.lang.String unitOfWtIso, java.lang.String unitOfWt, java.math.BigDecimal allowedWght, java.lang.String maxUnitOfWghtIso, java.lang.String maxUnitOfWght, java.math.BigDecimal totalVol, java.math.BigDecimal loadVol, java.math.BigDecimal tareVol, java.lang.String volumeunitIso, java.lang.String volumeunit, java.math.BigDecimal allowedVol, java.lang.String maxVolUnitIso, java.lang.String maxVolUnit, java.lang.Integer noSimilarPackMat, java.lang.String createdBy, java.util.Date createdDate, java.util.Date createdTime, java.lang.String changedBy, java.util.Date changedDate, java.util.Date changedTime, java.lang.String sortFld, java.lang.String huGrp1, java.lang.String huGrp2, java.lang.String huGrp3, java.lang.String huGrp4, java.lang.String huGrp5, java.lang.String packMat, java.math.BigDecimal lenght, java.math.BigDecimal width, java.math.BigDecimal height, java.lang.String unitDimIso, java.lang.String unitDim, java.lang.String statusObsolet, java.math.BigDecimal wghtTolHu, java.math.BigDecimal volTolHu, java.lang.String baseUomIso, java.lang.String baseUom, java.lang.String content, java.lang.String packMatType, java.lang.String matGrpSm, java.lang.String plant, java.lang.String itemCateg, java.lang.String salesorg, java.lang.String dcCustomMat, java.math.BigDecimal lgthLoad, java.lang.String lgthLoadUnitIso, java.lang.String lgthLoadUnit, java.math.BigDecimal travelTime, java.lang.String travelTimeUnitIso, java.lang.String travelTimeUnit, java.math.BigDecimal distance, java.lang.String unitOfDistIso, java.lang.String unitOfDist, java.lang.String stgeLoc, java.lang.String wghtVolFix, java.lang.String packMatCat, java.lang.String extIdHu2, java.lang.String cntryShpMatIso, java.lang.String cntryShpMat, java.lang.String nationalityDriverIso, java.lang.String nationalityDriver, java.lang.String nameDriver, java.lang.String nameCoDriver, java.lang.String packMatCustomer, java.lang.String packMatObject, java.lang.String packMatObjKey, java.lang.String handle, java.lang.String containerStat, java.lang.String warehouseNumber, java.lang.String closedBox, java.lang.String flagPackgLvDangGoods, java.lang.String flagPackgLvPrint, java.lang.String higherLevelHu, java.lang.String packgInstruct, java.lang.String lPackgStatusHu, java.lang.String flagNoExtLable, java.math.BigDecimal permissWorkload, java.lang.String huStorLoc, java.lang.String packMatName) {
        this.client = client;
        this.huId = huId;
        this.huExid = huExid;
        this.huExidType = huExidType;
        this.shipPoint = shipPoint;
        this.loadingPoint = loadingPoint;
        this.totalWght = totalWght;
        this.loadWght = loadWght;
        this.tareWght = tareWght;
        this.unitOfWtIso = unitOfWtIso;
        this.unitOfWt = unitOfWt;
        this.allowedWght = allowedWght;
        this.maxUnitOfWghtIso = maxUnitOfWghtIso;
        this.maxUnitOfWght = maxUnitOfWght;
        this.totalVol = totalVol;
        this.loadVol = loadVol;
        this.tareVol = tareVol;
        this.volumeunitIso = volumeunitIso;
        this.volumeunit = volumeunit;
        this.allowedVol = allowedVol;
        this.maxVolUnitIso = maxVolUnitIso;
        this.maxVolUnit = maxVolUnit;
        this.noSimilarPackMat = noSimilarPackMat;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.changedBy = changedBy;
        this.changedDate = changedDate;
        this.changedTime = changedTime;
        this.sortFld = sortFld;
        this.huGrp1 = huGrp1;
        this.huGrp2 = huGrp2;
        this.huGrp3 = huGrp3;
        this.huGrp4 = huGrp4;
        this.huGrp5 = huGrp5;
        this.packMat = packMat;
        this.lenght = lenght;
        this.width = width;
        this.height = height;
        this.unitDimIso = unitDimIso;
        this.unitDim = unitDim;
        this.statusObsolet = statusObsolet;
        this.wghtTolHu = wghtTolHu;
        this.volTolHu = volTolHu;
        this.baseUomIso = baseUomIso;
        this.baseUom = baseUom;
        this.content = content;
        this.packMatType = packMatType;
        this.matGrpSm = matGrpSm;
        this.plant = plant;
        this.itemCateg = itemCateg;
        this.salesorg = salesorg;
        this.dcCustomMat = dcCustomMat;
        this.lgthLoad = lgthLoad;
        this.lgthLoadUnitIso = lgthLoadUnitIso;
        this.lgthLoadUnit = lgthLoadUnit;
        this.travelTime = travelTime;
        this.travelTimeUnitIso = travelTimeUnitIso;
        this.travelTimeUnit = travelTimeUnit;
        this.distance = distance;
        this.unitOfDistIso = unitOfDistIso;
        this.unitOfDist = unitOfDist;
        this.stgeLoc = stgeLoc;
        this.wghtVolFix = wghtVolFix;
        this.packMatCat = packMatCat;
        this.extIdHu2 = extIdHu2;
        this.cntryShpMatIso = cntryShpMatIso;
        this.cntryShpMat = cntryShpMat;
        this.nationalityDriverIso = nationalityDriverIso;
        this.nationalityDriver = nationalityDriver;
        this.nameDriver = nameDriver;
        this.nameCoDriver = nameCoDriver;
        this.packMatCustomer = packMatCustomer;
        this.packMatObject = packMatObject;
        this.packMatObjKey = packMatObjKey;
        this.handle = handle;
        this.containerStat = containerStat;
        this.warehouseNumber = warehouseNumber;
        this.closedBox = closedBox;
        this.flagPackgLvDangGoods = flagPackgLvDangGoods;
        this.flagPackgLvPrint = flagPackgLvPrint;
        this.higherLevelHu = higherLevelHu;
        this.packgInstruct = packgInstruct;
        this.lPackgStatusHu = lPackgStatusHu;
        this.flagNoExtLable = flagNoExtLable;
        this.permissWorkload = permissWorkload;
        this.huStorLoc = huStorLoc;
        this.packMatName = packMatName;
    }

    public Huheader() {
    }

    /**
     * Client name and handling number
     * @return client
     * */
    public java.lang.String getClient() {
        return client;
    }

    /**
     * @return "HuId" - "Internal Handling Unit Number" */
    public java.lang.String getHuId() {
        return huId;
    }

    /**
     * @return "HuExid" - "External Handling Unit Identification" */
    public java.lang.String getHuExid() {
        return huExid;
    }

    /**
     * @return "HuExidType" - "Type of External Handling Unit Identifier" */
    public java.lang.String getHuExidType() {
        return huExidType;
    }

    /**
     * @return "ShipPoint" - "Shipping Point/Receiving Point" */
    public java.lang.String getShipPoint() {
        return shipPoint;
    }

    /**
     * @return "LoadingPoint" - "Loading Point" */
    public java.lang.String getLoadingPoint() {
        return loadingPoint;
    }

    /**
     * @return "TotalWght" - "Total Weight of Handling Unit" */
    public java.math.BigDecimal getTotalWght() {
        return totalWght;
    }

    /**
     * @return "LoadWght" - "Loading Weight of Handling Unit" */
    public java.math.BigDecimal getLoadWght() {
        return loadWght;
    }

    /**
     * @return "TareWght" - "Tare weight of handling unit" */
    public java.math.BigDecimal getTareWght() {
        return tareWght;
    }

    /**
     * @return "UnitOfWtIso" - "Unit of weight in ISO code" */
    public java.lang.String getUnitOfWtIso() {
        return unitOfWtIso;
    }

    /**
     * @return "UnitOfWt" - "Weight Unit" */
    public java.lang.String getUnitOfWt() {
        return unitOfWt;
    }

    /**
     * @return "AllowedWght" - "Allowed Loading Weight of a Handling Unit" */
    public java.math.BigDecimal getAllowedWght() {
        return allowedWght;
    }

    /**
     * @return "MaxUnitOfWghtIso" - "Unit of weight in ISO code" */
    public java.lang.String getMaxUnitOfWghtIso() {
        return maxUnitOfWghtIso;
    }

    /**
     * @return "MaxUnitOfWght" - "Weight Unit" */
    public java.lang.String getMaxUnitOfWght() {
        return maxUnitOfWght;
    }

    /**
     * @return "TotalVol" - "Total Volume of Handling Unit" */
    public java.math.BigDecimal getTotalVol() {
        return totalVol;
    }

    /**
     * @return "LoadVol" - "Loading Volume of Handling Unit" */
    public java.math.BigDecimal getLoadVol() {
        return loadVol;
    }

    /**
     * @return "TareVol" - "Tare volume of handling unit" */
    public java.math.BigDecimal getTareVol() {
        return tareVol;
    }

    /**
     * @return "VolumeunitIso" - "Volume unit in ISO code" */
    public java.lang.String getVolumeunitIso() {
        return volumeunitIso;
    }

    /**
     * @return "Volumeunit" - "Volume unit" */
    public java.lang.String getVolumeunit() {
        return volumeunit;
    }

    /**
     * @return "AllowedVol" - "Allowed Loading Volume for Handling Unit" */
    public java.math.BigDecimal getAllowedVol() {
        return allowedVol;
    }

    /**
     * @return "MaxVolUnitIso" - "Volume unit in ISO code" */
    public java.lang.String getMaxVolUnitIso() {
        return maxVolUnitIso;
    }

    /**
     * @return "MaxVolUnit" - "Volume unit" */
    public java.lang.String getMaxVolUnit() {
        return maxVolUnit;
    }

    /**
     * @return "NoSimilarPackMat" - "Number of Similar Packaging Materials" */
    public java.lang.Integer getNoSimilarPackMat() {
        return noSimilarPackMat;
    }

    /**
     * @return "CreatedBy" - "Name of Person who Created the Object" */
    public java.lang.String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return createdDate - "Date on which the record was created" */
    public java.util.Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @return "CreatedTime" - "Entry time" */
    public java.util.Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @return "ChangedBy" - "Name of person who changed object" */
    public java.lang.String getChangedBy() {
        return changedBy;
    }

    /**
     * @return "ChangedDate" - "Date of Last Change" */
    public java.util.Date getChangedDate() {
        return changedDate;
    }

    /**
     * @return "ChangedTime" - "Time last change was made" */
    public java.util.Date getChangedTime() {
        return changedTime;
    }

    /**
     * @return "SortFld" - "Sort field" */
    public java.lang.String getSortFld() {
        return sortFld;
    }

    /**
     * @return "HuGrp1" - "Handling Unit Group 1                     (Freely Definable)" */
    public java.lang.String getHuGrp1() {
        return huGrp1;
    }

    /**
     * @return "HuGrp2" - "Handling Unit Group 2                     (Freely Definable)" */
    public java.lang.String getHuGrp2() {
        return huGrp2;
    }

    /**
     * @return "HuGrp3" - "Handling Unit Group 3                     (Freely Definable)" */
    public java.lang.String getHuGrp3() {
        return huGrp3;
    }

    /**
     * @return "HuGrp4" - "Handling Unit Group 4                     (Freely Definable)" */
    public java.lang.String getHuGrp4() {
        return huGrp4;
    }

    /**
     * @return "HuGrp5" - "Handling Unit Group 5                     (Freely Definable)" */
    public java.lang.String getHuGrp5() {
        return huGrp5;
    }

    /**
     * @return "PackMat" - "Packaging Materials" */
    public java.lang.String getPackMat() {
        return packMat;
    }

    /**
     * @return "Lenght" - "Length" */
    public java.math.BigDecimal getLenght() {
        return lenght;
    }

    /**
     * @return "Width" - "Width" */
    public java.math.BigDecimal getWidth() {
        return width;
    }

    /**
     * @return "Height" - "Height" */
    public java.math.BigDecimal getHeight() {
        return height;
    }

    /**
     * @return "UnitDimIso" - "Unit for length/breadth/height in ISO code" */
    public java.lang.String getUnitDimIso() {
        return unitDimIso;
    }

    /**
     * @return "UnitDim" - "Unit of dimension for length/width/height" */
    public java.lang.String getUnitDim() {
        return unitDim;
    }

    /**
     * @return "StatusObsolet" - "Status (at this time without functionality)" */
    public java.lang.String getStatusObsolet() {
        return statusObsolet;
    }

    /**
     * @return "WghtTolHu" - "Excess Weight Tolerance for Handling unit" */
    public java.math.BigDecimal getWghtTolHu() {
        return wghtTolHu;
    }

    /**
     * @return "VolTolHu" - "Excess Volume Tolerance of the Handling Unit" */
    public java.math.BigDecimal getVolTolHu() {
        return volTolHu;
    }

    /**
     * @return "BaseUomIso" - "Base unit of measure in ISO code" */
    public java.lang.String getBaseUomIso() {
        return baseUomIso;
    }

    /**
     * @return "BaseUom" - "Base Unit of Measure" */
    public java.lang.String getBaseUom() {
        return baseUom;
    }

    /**
     * @return "Content" - "Description of Handling Unit Content" */
    public java.lang.String getContent() {
        return content;
    }

    /**
     * @return "PackMatType" - "Packaging Material Type" */
    public java.lang.String getPackMatType() {
        return packMatType;
    }

    /**
     * @return "MatGrpSm" - "Material Group: Packaging Materials" */
    public java.lang.String getMatGrpSm() {
        return matGrpSm;
    }

    /**
     * @return "Plant" - "Plant" */
    public java.lang.String getPlant() {
        return plant;
    }

    /**
     * @return "ItemCateg" - "Sales document item category" */
    public java.lang.String getItemCateg() {
        return itemCateg;
    }

    /**
     * @return "Salesorg" - "Sales Organization" */
    public java.lang.String getSalesorg() {
        return salesorg;
    }

    /**
     * @return "DcCustomMat" - "Reference distrib.channel for cust.and material masters" */
    public java.lang.String getDcCustomMat() {
        return dcCustomMat;
    }

    /**
     * @return "LgthLoad" - "Lgth of loading platform in lgth of LdPlat measurement units" */
    public java.math.BigDecimal getLgthLoad() {
        return lgthLoad;
    }

    /**
     * @return "LgthLoadUnitIso" - "Load unit of measure in ISO code" */
    public java.lang.String getLgthLoadUnitIso() {
        return lgthLoadUnitIso;
    }

    /**
     * @return "LgthLoadUnit" - "Unit of measure to measure the length of loading platform" */
    public java.lang.String getLgthLoadUnit() {
        return lgthLoadUnit;
    }

    /**
     * @return "TravelTime" - "Travel Time" */
    public java.math.BigDecimal getTravelTime() {
        return travelTime;
    }

    /**
     * @return "TravelTimeUnitIso" - "Travel time unit in ISO code" */
    public java.lang.String getTravelTimeUnitIso() {
        return travelTimeUnitIso;
    }

    /**
     * @return "TravelTimeUnit" - "Unit of travel time" */
    public java.lang.String getTravelTimeUnit() {
        return travelTimeUnit;
    }

    /**
     * @return "Distance" - "Distance Travelled" */
    public java.math.BigDecimal getDistance() {
        return distance;
    }

    /**
     * @return "UnitOfDistIso" - "Travel time unit in ISO code" */
    public java.lang.String getUnitOfDistIso() {
        return unitOfDistIso;
    }

    /**
     * @return "UnitOfDist" - "Unit of distance" */
    public java.lang.String getUnitOfDist() {
        return unitOfDist;
    }

    /**
     * @return "StgeLoc" - "Storage Location" */
    public java.lang.String getStgeLoc() {
        return stgeLoc;
    }

    /**
     * @return "WghtVolFix" - "Weight and Volume Fixed" */
    public java.lang.String getWghtVolFix() {
        return wghtVolFix;
    }

    /**
     * @return "PackMatCat" - "Packaging Material Category" */
    public java.lang.String getPackMatCat() {
        return packMatCat;
    }

    /**
     * @return "ExtIdHu2" - "Handling Unit's 2nd External Identification" */
    public java.lang.String getExtIdHu2() {
        return extIdHu2;
    }

    /**
     * @return "CntryShpMatIso" - "Country of means of transport ISO code" */
    public java.lang.String getCntryShpMatIso() {
        return cntryShpMatIso;
    }

    /**
     * @return "CntryShpMat" - "Country providing means of transport" */
    public java.lang.String getCntryShpMat() {
        return cntryShpMat;
    }

    /**
     * @return "NationalityDriverIso" - "Driver's nationality ISO code" */
    public java.lang.String getNationalityDriverIso() {
        return nationalityDriverIso;
    }

    /**
     * @return "NationalityDriver" - "Driver's Nationality" */
    public java.lang.String getNationalityDriver() {
        return nationalityDriver;
    }

    /**
     * @return "NameDriver" - "Driver name" */
    public java.lang.String getNameDriver() {
        return nameDriver;
    }

    /**
     * @return "NameCoDriver" - "Alternate Driver's Name" */
    public java.lang.String getNameCoDriver() {
        return nameCoDriver;
    }

    /**
     * @return "PackMatCustomer" - "Partner's (Customer's or Vendor's) Packaging Material" */
    public java.lang.String getPackMatCustomer() {
        return packMatCustomer;
    }

    /**
     * @return "PackMatObject" - "Packing Object" */
    public java.lang.String getPackMatObject() {
        return packMatObject;
    }

    /**
     * @return "PackMatObjKey" - "Key for Object to Which the Handling Unit is Assigned" */
    public java.lang.String getPackMatObjKey() {
        return packMatObjKey;
    }

    /**
     * @return "Handle" - "Worldwide unique key for VEKP-VENUM" */
    public java.lang.String getHandle() {
        return handle;
    }

    /**
     * @return "ContainerStat" - "WM status for container material" */
    public java.lang.String getContainerStat() {
        return containerStat;
    }

    /**
     * @return "WarehouseNumber" - "Warehouse Number / Warehouse Complex" */
    public java.lang.String getWarehouseNumber() {
        return warehouseNumber;
    }

    /**
     * @return "ClosedBox" - "Packaging Material is Closed Packaging" */
    public java.lang.String getClosedBox() {
        return closedBox;
    }

    /**
     * @return "FlagPackgLvDangGoods" - "Packing Level is Relevant for Dangerous Goods" */
    public java.lang.String getFlagPackgLvDangGoods() {
        return flagPackgLvDangGoods;
    }

    /**
     * @return "FlagPackgLvPrint" - "Indicator: Packing level is relevant for printing" */
    public java.lang.String getFlagPackgLvPrint() {
        return flagPackgLvPrint;
    }

    /**
     * @return "HigherLevelHu" - "Higher-Level Handling Unit" */
    public java.lang.String getHigherLevelHu() {
        return higherLevelHu;
    }

    /**
     * @return "PackgInstruct" - "Packing instruction" */
    public java.lang.String getPackgInstruct() {
        return packgInstruct;
    }

    /**
     * @return "LPackgStatusHu" - "Local packing status for a single HU (without sub HUs)" */
    public java.lang.String getLPackgStatusHu() {
        return lPackgStatusHu;
    }

    /**
     * @return "FlagNoExtLable" - "Indicator: do not print external shipping label" */
    public java.lang.String getFlagNoExtLable() {
        return flagNoExtLable;
    }

    /**
     * @return "PermissWorkload" - "Allowed load" */
    public java.math.BigDecimal getPermissWorkload() {
        return permissWorkload;
    }

    /**
     * @return "HuStorLoc" - "Content is Located at an HU-managed Storage Location" */
    public java.lang.String getHuStorLoc() {
        return huStorLoc;
    }

    /**
     * @return "PackMatName" - "Description of Packaging Material" */
    public java.lang.String getPackMatName() {
        return packMatName;
    }
}
