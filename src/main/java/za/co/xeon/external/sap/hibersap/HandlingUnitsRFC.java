package za.co.xeon.external.sap.hibersap;

import java.lang.String;
import java.util.List;
import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.Table;
import org.hibersap.bapi.BapiRet2;

@Bapi("Z_GET_HANDLING_UNITS")
public class HandlingUnitsRFC {
    /**
     * "Sales and Distribution Document Number" */
    @Import
    @Parameter("IM_DEL_VBELN")
    private final String imDelVbeln;

    /**
     * "Handling Unit - Header Table" */
    @Parameter("HUHEADER")
    @Table
    private List<Huheader> huheader;

    /**
     * "Structure for Handling Unit Item" */
    @Parameter("HUITEM")
    @Table
    private List<Huitem> huitem;

    /**
     * "External identification of a HU" */
    @Parameter("HUNUMBERS")
    @Table
    private List<Hunumbers> hunumbers;

    /**
     * "Return Parameter"@return "Return" - "Return Parameter" */
    @Table
    @Parameter("RETURN")
    private List<BapiRet2> returnType;

    /**
     * @param "imDelVbeln" - "Sales and Distribution Document Number" */
    public HandlingUnitsRFC(String imDelVbeln) {
        this.imDelVbeln = imDelVbeln;
    }

    public List<Huheader> getHuheader() {
        return huheader;
    }

    public List<Huitem> getHuitem() {
        return huitem;
    }

    public List<Hunumbers> getHunumbers() {
        return hunumbers;
    }

    public List<BapiRet2> getReturn() {
        return returnType;
    }

    @BapiStructure
    public class Huheader {
        /**
         * "Client" */
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

        /**
         * @return "Client" - "Client" */
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
         * @return "CreatedDate" - "Date on which the record was created" */
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

    @BapiStructure
    public class Hunumbers {
        /**
         * "External Handling Unit Identification" */
        @Parameter("HU_EXID")
        private java.lang.String huExid;

        /**
         * @return "HuExid" - "External Handling Unit Identification" */
        public java.lang.String getHuExid() {
            return huExid;
        }
    }
}
