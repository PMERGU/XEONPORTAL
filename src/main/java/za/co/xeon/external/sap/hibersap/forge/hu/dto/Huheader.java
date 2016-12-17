package za.co.xeon.external.sap.hibersap.forge.hu.dto;

import org.hibersap.annotations.BapiStructure;
import java.math.BigDecimal;
import org.hibersap.annotations.Parameter;
import java.util.Date;
import java.lang.Override;

@BapiStructure
public class Huheader {

	@Parameter("TRAVEL_TIME")
	BigDecimal _travelTime;
	@Parameter("HU_GRP4")
	String _huGrp4;
	@Parameter("WAREHOUSE_NUMBER")
	String _warehouseNumber;
	@Parameter("PERMISS_WORKLOAD")
	BigDecimal _permissWorkload;
	@Parameter("PACK_MAT_OBJ_KEY")
	String _packMatObjKey;
	@Parameter("NAME_DRIVER")
	String _nameDriver;
	@Parameter("SORT_FLD")
	String _sortFld;
	@Parameter("MAX_VOL_UNIT_ISO")
	String _maxVolUnitIso;
	@Parameter("DC_CUSTOM_MAT")
	String _dcCustomMat;
	@Parameter("BASE_UOM")
	String _baseUom;
	@Parameter("FLAG_PACKG_LV_PRINT")
	String _flagPackgLvPrint;
	@Parameter("CONTENT")
	String _content;
	@Parameter("DISTANCE")
	BigDecimal _distance;
	@Parameter("UNIT_OF_WT")
	String _unitOfWt;
	@Parameter("LENGHT")
	BigDecimal _lenght;
	@Parameter("STATUS_OBSOLET")
	String _statusObsolet;
	@Parameter("CLIENT")
	String _client;
	@Parameter("TOTAL_VOL")
	BigDecimal _totalVol;
	@Parameter("TARE_VOL")
	BigDecimal _tareVol;
	@Parameter("WGHT_TOL_HU")
	BigDecimal _wghtTolHu;
	@Parameter("HU_GRP3")
	String _huGrp3;
	@Parameter("CREATED_TIME")
	Date _createdTime;
	@Parameter("PACKG_INSTRUCT")
	String _packgInstruct;
	@Parameter("PACK_MAT_NAME")
	String _packMatName;
	@Parameter("MAX_UNIT_OF_WGHT_ISO")
	String _maxUnitOfWghtIso;
	@Parameter("CLOSED_BOX")
	String _closedBox;
	@Parameter("CHANGED_BY")
	String _changedBy;
	@Parameter("MAT_GRP_SM")
	String _matGrpSm;
	@Parameter("VOL_TOL_HU")
	BigDecimal _volTolHu;
	@Parameter("WIDTH")
	BigDecimal _width;
	@Parameter("LGTH_LOAD_UNIT_ISO")
	String _lgthLoadUnitIso;
	@Parameter("FLAG_NO_EXT_LABLE")
	String _flagNoExtLable;
	@Parameter("TARE_WGHT")
	BigDecimal _tareWght;
	@Parameter("UNIT_OF_DIST")
	String _unitOfDist;
	@Parameter("WGHT_VOL_FIX")
	String _wghtVolFix;
	@Parameter("PACK_MAT_TYPE")
	String _packMatType;
	@Parameter("UNIT_OF_DIST_ISO")
	String _unitOfDistIso;
	@Parameter("LOAD_VOL")
	BigDecimal _loadVol;
	@Parameter("LGTH_LOAD")
	BigDecimal _lgthLoad;
	@Parameter("PLANT")
	String _plant;
	@Parameter("PACK_MAT_OBJECT")
	String _packMatObject;
	@Parameter("STGE_LOC")
	String _stgeLoc;
	@Parameter("PACK_MAT")
	String _packMat;
	@Parameter("HU_GRP2")
	String _huGrp2;
	@Parameter("NO_SIMILAR_PACK_MAT")
	Integer _noSimilarPackMat;
	@Parameter("PACK_MAT_CUSTOMER")
	String _packMatCustomer;
	@Parameter("SHIP_POINT")
	String _shipPoint;
	@Parameter("HU_ID")
	String _huId;
	@Parameter("EXT_ID_HU_2")
	String _extIdHu2;
	@Parameter("LOAD_WGHT")
	BigDecimal _loadWght;
	@Parameter("HU_EXID")
	String _huExid;
	@Parameter("ALLOWED_VOL")
	BigDecimal _allowedVol;
	@Parameter("ITEM_CATEG")
	String _itemCateg;
	@Parameter("SALESORG")
	String _salesorg;
	@Parameter("MAX_UNIT_OF_WGHT")
	String _maxUnitOfWght;
	@Parameter("CHANGED_DATE")
	Date _changedDate;
	@Parameter("CHANGED_TIME")
	Date _changedTime;
	@Parameter("HEIGHT")
	BigDecimal _height;
	@Parameter("L_PACKG_STATUS_HU")
	String _lPackgStatusHu;
	@Parameter("UNIT_OF_WT_ISO")
	String _unitOfWtIso;
	@Parameter("HU_STOR_LOC")
	String _huStorLoc;
	@Parameter("HU_EXID_TYPE")
	String _huExidType;
	@Parameter("CREATED_BY")
	String _createdBy;
	@Parameter("UNIT_DIM")
	String _unitDim;
	@Parameter("LGTH_LOAD_UNIT")
	String _lgthLoadUnit;
	@Parameter("VOLUMEUNIT")
	String _volumeunit;
	@Parameter("ALLOWED_WGHT")
	BigDecimal _allowedWght;
	@Parameter("CONTAINER_STAT")
	String _containerStat;
	@Parameter("HIGHER_LEVEL_HU")
	String _higherLevelHu;
	@Parameter("CNTRY_SHP_MAT_ISO")
	String _cntryShpMatIso;
	@Parameter("HU_GRP1")
	String _huGrp1;
	@Parameter("HU_GRP5")
	String _huGrp5;
	@Parameter("CREATED_DATE")
	Date _createdDate;
	@Parameter("CNTRY_SHP_MAT")
	String _cntryShpMat;
	@Parameter("HANDLE")
	String _handle;
	@Parameter("BASE_UOM_ISO")
	String _baseUomIso;
	@Parameter("PACK_MAT_CAT")
	String _packMatCat;
	@Parameter("VOLUMEUNIT_ISO")
	String _volumeunitIso;
	@Parameter("NATIONALITY_DRIVER_ISO")
	String _nationalityDriverIso;
	@Parameter("NATIONALITY_DRIVER")
	String _nationalityDriver;
	@Parameter("UNIT_DIM_ISO")
	String _unitDimIso;
	@Parameter("TRAVEL_TIME_UNIT")
	String _travelTimeUnit;
	@Parameter("TOTAL_WGHT")
	BigDecimal _totalWght;
	@Parameter("TRAVEL_TIME_UNIT_ISO")
	String _travelTimeUnitIso;
	@Parameter("LOADING_POINT")
	String _loadingPoint;
	@Parameter("FLAG_PACKG_LV_DANG_GOODS")
	String _flagPackgLvDangGoods;
	@Parameter("NAME_CO_DRIVER")
	String _nameCoDriver;
	@Parameter("MAX_VOL_UNIT")
	String _maxVolUnit;

	public BigDecimal get_travelTime() {
		return this._travelTime;
	}

	public void set_travelTime(final BigDecimal _travelTime) {
		this._travelTime = _travelTime;
	}

	public String get_huGrp4() {
		return this._huGrp4;
	}

	public void set_huGrp4(final String _huGrp4) {
		this._huGrp4 = _huGrp4;
	}

	public String get_warehouseNumber() {
		return this._warehouseNumber;
	}

	public void set_warehouseNumber(final String _warehouseNumber) {
		this._warehouseNumber = _warehouseNumber;
	}

	public BigDecimal get_permissWorkload() {
		return this._permissWorkload;
	}

	public void set_permissWorkload(final BigDecimal _permissWorkload) {
		this._permissWorkload = _permissWorkload;
	}

	public String get_packMatObjKey() {
		return this._packMatObjKey;
	}

	public void set_packMatObjKey(final String _packMatObjKey) {
		this._packMatObjKey = _packMatObjKey;
	}

	public String get_nameDriver() {
		return this._nameDriver;
	}

	public void set_nameDriver(final String _nameDriver) {
		this._nameDriver = _nameDriver;
	}

	public String get_sortFld() {
		return this._sortFld;
	}

	public void set_sortFld(final String _sortFld) {
		this._sortFld = _sortFld;
	}

	public String get_maxVolUnitIso() {
		return this._maxVolUnitIso;
	}

	public void set_maxVolUnitIso(final String _maxVolUnitIso) {
		this._maxVolUnitIso = _maxVolUnitIso;
	}

	public String get_dcCustomMat() {
		return this._dcCustomMat;
	}

	public void set_dcCustomMat(final String _dcCustomMat) {
		this._dcCustomMat = _dcCustomMat;
	}

	public String get_baseUom() {
		return this._baseUom;
	}

	public void set_baseUom(final String _baseUom) {
		this._baseUom = _baseUom;
	}

	public String get_flagPackgLvPrint() {
		return this._flagPackgLvPrint;
	}

	public void set_flagPackgLvPrint(final String _flagPackgLvPrint) {
		this._flagPackgLvPrint = _flagPackgLvPrint;
	}

	public String get_content() {
		return this._content;
	}

	public void set_content(final String _content) {
		this._content = _content;
	}

	public BigDecimal get_distance() {
		return this._distance;
	}

	public void set_distance(final BigDecimal _distance) {
		this._distance = _distance;
	}

	public String get_unitOfWt() {
		return this._unitOfWt;
	}

	public void set_unitOfWt(final String _unitOfWt) {
		this._unitOfWt = _unitOfWt;
	}

	public BigDecimal get_lenght() {
		return this._lenght;
	}

	public void set_lenght(final BigDecimal _lenght) {
		this._lenght = _lenght;
	}

	public String get_statusObsolet() {
		return this._statusObsolet;
	}

	public void set_statusObsolet(final String _statusObsolet) {
		this._statusObsolet = _statusObsolet;
	}

	public String get_client() {
		return this._client;
	}

	public void set_client(final String _client) {
		this._client = _client;
	}

	public BigDecimal get_totalVol() {
		return this._totalVol;
	}

	public void set_totalVol(final BigDecimal _totalVol) {
		this._totalVol = _totalVol;
	}

	public BigDecimal get_tareVol() {
		return this._tareVol;
	}

	public void set_tareVol(final BigDecimal _tareVol) {
		this._tareVol = _tareVol;
	}

	public BigDecimal get_wghtTolHu() {
		return this._wghtTolHu;
	}

	public void set_wghtTolHu(final BigDecimal _wghtTolHu) {
		this._wghtTolHu = _wghtTolHu;
	}

	public String get_huGrp3() {
		return this._huGrp3;
	}

	public void set_huGrp3(final String _huGrp3) {
		this._huGrp3 = _huGrp3;
	}

	public Date get_createdTime() {
		return this._createdTime;
	}

	public void set_createdTime(final Date _createdTime) {
		this._createdTime = _createdTime;
	}

	public String get_packgInstruct() {
		return this._packgInstruct;
	}

	public void set_packgInstruct(final String _packgInstruct) {
		this._packgInstruct = _packgInstruct;
	}

	public String get_packMatName() {
		return this._packMatName;
	}

	public void set_packMatName(final String _packMatName) {
		this._packMatName = _packMatName;
	}

	public String get_maxUnitOfWghtIso() {
		return this._maxUnitOfWghtIso;
	}

	public void set_maxUnitOfWghtIso(final String _maxUnitOfWghtIso) {
		this._maxUnitOfWghtIso = _maxUnitOfWghtIso;
	}

	public String get_closedBox() {
		return this._closedBox;
	}

	public void set_closedBox(final String _closedBox) {
		this._closedBox = _closedBox;
	}

	public String get_changedBy() {
		return this._changedBy;
	}

	public void set_changedBy(final String _changedBy) {
		this._changedBy = _changedBy;
	}

	public String get_matGrpSm() {
		return this._matGrpSm;
	}

	public void set_matGrpSm(final String _matGrpSm) {
		this._matGrpSm = _matGrpSm;
	}

	public BigDecimal get_volTolHu() {
		return this._volTolHu;
	}

	public void set_volTolHu(final BigDecimal _volTolHu) {
		this._volTolHu = _volTolHu;
	}

	public BigDecimal get_width() {
		return this._width;
	}

	public void set_width(final BigDecimal _width) {
		this._width = _width;
	}

	public String get_lgthLoadUnitIso() {
		return this._lgthLoadUnitIso;
	}

	public void set_lgthLoadUnitIso(final String _lgthLoadUnitIso) {
		this._lgthLoadUnitIso = _lgthLoadUnitIso;
	}

	public String get_flagNoExtLable() {
		return this._flagNoExtLable;
	}

	public void set_flagNoExtLable(final String _flagNoExtLable) {
		this._flagNoExtLable = _flagNoExtLable;
	}

	public BigDecimal get_tareWght() {
		return this._tareWght;
	}

	public void set_tareWght(final BigDecimal _tareWght) {
		this._tareWght = _tareWght;
	}

	public String get_unitOfDist() {
		return this._unitOfDist;
	}

	public void set_unitOfDist(final String _unitOfDist) {
		this._unitOfDist = _unitOfDist;
	}

	public String get_wghtVolFix() {
		return this._wghtVolFix;
	}

	public void set_wghtVolFix(final String _wghtVolFix) {
		this._wghtVolFix = _wghtVolFix;
	}

	public String get_packMatType() {
		return this._packMatType;
	}

	public void set_packMatType(final String _packMatType) {
		this._packMatType = _packMatType;
	}

	public String get_unitOfDistIso() {
		return this._unitOfDistIso;
	}

	public void set_unitOfDistIso(final String _unitOfDistIso) {
		this._unitOfDistIso = _unitOfDistIso;
	}

	public BigDecimal get_loadVol() {
		return this._loadVol;
	}

	public void set_loadVol(final BigDecimal _loadVol) {
		this._loadVol = _loadVol;
	}

	public BigDecimal get_lgthLoad() {
		return this._lgthLoad;
	}

	public void set_lgthLoad(final BigDecimal _lgthLoad) {
		this._lgthLoad = _lgthLoad;
	}

	public String get_plant() {
		return this._plant;
	}

	public void set_plant(final String _plant) {
		this._plant = _plant;
	}

	public String get_packMatObject() {
		return this._packMatObject;
	}

	public void set_packMatObject(final String _packMatObject) {
		this._packMatObject = _packMatObject;
	}

	public String get_stgeLoc() {
		return this._stgeLoc;
	}

	public void set_stgeLoc(final String _stgeLoc) {
		this._stgeLoc = _stgeLoc;
	}

	public String get_packMat() {
		return this._packMat;
	}

	public void set_packMat(final String _packMat) {
		this._packMat = _packMat;
	}

	public String get_huGrp2() {
		return this._huGrp2;
	}

	public void set_huGrp2(final String _huGrp2) {
		this._huGrp2 = _huGrp2;
	}

	public Integer get_noSimilarPackMat() {
		return this._noSimilarPackMat;
	}

	public void set_noSimilarPackMat(final Integer _noSimilarPackMat) {
		this._noSimilarPackMat = _noSimilarPackMat;
	}

	public String get_packMatCustomer() {
		return this._packMatCustomer;
	}

	public void set_packMatCustomer(final String _packMatCustomer) {
		this._packMatCustomer = _packMatCustomer;
	}

	public String get_shipPoint() {
		return this._shipPoint;
	}

	public void set_shipPoint(final String _shipPoint) {
		this._shipPoint = _shipPoint;
	}

	public String get_huId() {
		return this._huId;
	}

	public void set_huId(final String _huId) {
		this._huId = _huId;
	}

	public String get_extIdHu2() {
		return this._extIdHu2;
	}

	public void set_extIdHu2(final String _extIdHu2) {
		this._extIdHu2 = _extIdHu2;
	}

	public BigDecimal get_loadWght() {
		return this._loadWght;
	}

	public void set_loadWght(final BigDecimal _loadWght) {
		this._loadWght = _loadWght;
	}

	public String get_huExid() {
		return this._huExid;
	}

	public void set_huExid(final String _huExid) {
		this._huExid = _huExid;
	}

	public BigDecimal get_allowedVol() {
		return this._allowedVol;
	}

	public void set_allowedVol(final BigDecimal _allowedVol) {
		this._allowedVol = _allowedVol;
	}

	public String get_itemCateg() {
		return this._itemCateg;
	}

	public void set_itemCateg(final String _itemCateg) {
		this._itemCateg = _itemCateg;
	}

	public String get_salesorg() {
		return this._salesorg;
	}

	public void set_salesorg(final String _salesorg) {
		this._salesorg = _salesorg;
	}

	public String get_maxUnitOfWght() {
		return this._maxUnitOfWght;
	}

	public void set_maxUnitOfWght(final String _maxUnitOfWght) {
		this._maxUnitOfWght = _maxUnitOfWght;
	}

	public Date get_changedDate() {
		return this._changedDate;
	}

	public void set_changedDate(final Date _changedDate) {
		this._changedDate = _changedDate;
	}

	public Date get_changedTime() {
		return this._changedTime;
	}

	public void set_changedTime(final Date _changedTime) {
		this._changedTime = _changedTime;
	}

	public BigDecimal get_height() {
		return this._height;
	}

	public void set_height(final BigDecimal _height) {
		this._height = _height;
	}

	public String get_lPackgStatusHu() {
		return this._lPackgStatusHu;
	}

	public void set_lPackgStatusHu(final String _lPackgStatusHu) {
		this._lPackgStatusHu = _lPackgStatusHu;
	}

	public String get_unitOfWtIso() {
		return this._unitOfWtIso;
	}

	public void set_unitOfWtIso(final String _unitOfWtIso) {
		this._unitOfWtIso = _unitOfWtIso;
	}

	public String get_huStorLoc() {
		return this._huStorLoc;
	}

	public void set_huStorLoc(final String _huStorLoc) {
		this._huStorLoc = _huStorLoc;
	}

	public String get_huExidType() {
		return this._huExidType;
	}

	public void set_huExidType(final String _huExidType) {
		this._huExidType = _huExidType;
	}

	public String get_createdBy() {
		return this._createdBy;
	}

	public void set_createdBy(final String _createdBy) {
		this._createdBy = _createdBy;
	}

	public String get_unitDim() {
		return this._unitDim;
	}

	public void set_unitDim(final String _unitDim) {
		this._unitDim = _unitDim;
	}

	public String get_lgthLoadUnit() {
		return this._lgthLoadUnit;
	}

	public void set_lgthLoadUnit(final String _lgthLoadUnit) {
		this._lgthLoadUnit = _lgthLoadUnit;
	}

	public String get_volumeunit() {
		return this._volumeunit;
	}

	public void set_volumeunit(final String _volumeunit) {
		this._volumeunit = _volumeunit;
	}

	public BigDecimal get_allowedWght() {
		return this._allowedWght;
	}

	public void set_allowedWght(final BigDecimal _allowedWght) {
		this._allowedWght = _allowedWght;
	}

	public String get_containerStat() {
		return this._containerStat;
	}

	public void set_containerStat(final String _containerStat) {
		this._containerStat = _containerStat;
	}

	public String get_higherLevelHu() {
		return this._higherLevelHu;
	}

	public void set_higherLevelHu(final String _higherLevelHu) {
		this._higherLevelHu = _higherLevelHu;
	}

	public String get_cntryShpMatIso() {
		return this._cntryShpMatIso;
	}

	public void set_cntryShpMatIso(final String _cntryShpMatIso) {
		this._cntryShpMatIso = _cntryShpMatIso;
	}

	public String get_huGrp1() {
		return this._huGrp1;
	}

	public void set_huGrp1(final String _huGrp1) {
		this._huGrp1 = _huGrp1;
	}

	public String get_huGrp5() {
		return this._huGrp5;
	}

	public void set_huGrp5(final String _huGrp5) {
		this._huGrp5 = _huGrp5;
	}

	public Date get_createdDate() {
		return this._createdDate;
	}

	public void set_createdDate(final Date _createdDate) {
		this._createdDate = _createdDate;
	}

	public String get_cntryShpMat() {
		return this._cntryShpMat;
	}

	public void set_cntryShpMat(final String _cntryShpMat) {
		this._cntryShpMat = _cntryShpMat;
	}

	public String get_handle() {
		return this._handle;
	}

	public void set_handle(final String _handle) {
		this._handle = _handle;
	}

	public String get_baseUomIso() {
		return this._baseUomIso;
	}

	public void set_baseUomIso(final String _baseUomIso) {
		this._baseUomIso = _baseUomIso;
	}

	public String get_packMatCat() {
		return this._packMatCat;
	}

	public void set_packMatCat(final String _packMatCat) {
		this._packMatCat = _packMatCat;
	}

	public String get_volumeunitIso() {
		return this._volumeunitIso;
	}

	public void set_volumeunitIso(final String _volumeunitIso) {
		this._volumeunitIso = _volumeunitIso;
	}

	public String get_nationalityDriverIso() {
		return this._nationalityDriverIso;
	}

	public void set_nationalityDriverIso(final String _nationalityDriverIso) {
		this._nationalityDriverIso = _nationalityDriverIso;
	}

	public String get_nationalityDriver() {
		return this._nationalityDriver;
	}

	public void set_nationalityDriver(final String _nationalityDriver) {
		this._nationalityDriver = _nationalityDriver;
	}

	public String get_unitDimIso() {
		return this._unitDimIso;
	}

	public void set_unitDimIso(final String _unitDimIso) {
		this._unitDimIso = _unitDimIso;
	}

	public String get_travelTimeUnit() {
		return this._travelTimeUnit;
	}

	public void set_travelTimeUnit(final String _travelTimeUnit) {
		this._travelTimeUnit = _travelTimeUnit;
	}

	public BigDecimal get_totalWght() {
		return this._totalWght;
	}

	public void set_totalWght(final BigDecimal _totalWght) {
		this._totalWght = _totalWght;
	}

	public String get_travelTimeUnitIso() {
		return this._travelTimeUnitIso;
	}

	public void set_travelTimeUnitIso(final String _travelTimeUnitIso) {
		this._travelTimeUnitIso = _travelTimeUnitIso;
	}

	public String get_loadingPoint() {
		return this._loadingPoint;
	}

	public void set_loadingPoint(final String _loadingPoint) {
		this._loadingPoint = _loadingPoint;
	}

	public String get_flagPackgLvDangGoods() {
		return this._flagPackgLvDangGoods;
	}

	public void set_flagPackgLvDangGoods(final String _flagPackgLvDangGoods) {
		this._flagPackgLvDangGoods = _flagPackgLvDangGoods;
	}

	public String get_nameCoDriver() {
		return this._nameCoDriver;
	}

	public void set_nameCoDriver(final String _nameCoDriver) {
		this._nameCoDriver = _nameCoDriver;
	}

	public String get_maxVolUnit() {
		return this._maxVolUnit;
	}

	public void set_maxVolUnit(final String _maxVolUnit) {
		this._maxVolUnit = _maxVolUnit;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (_travelTime != null)
			result += "_travelTime: " + _travelTime;
		if (_huGrp4 != null && !_huGrp4.trim().isEmpty())
			result += ", _huGrp4: " + _huGrp4;
		if (_warehouseNumber != null && !_warehouseNumber.trim().isEmpty())
			result += ", _warehouseNumber: " + _warehouseNumber;
		if (_permissWorkload != null)
			result += ", _permissWorkload: " + _permissWorkload;
		if (_packMatObjKey != null && !_packMatObjKey.trim().isEmpty())
			result += ", _packMatObjKey: " + _packMatObjKey;
		if (_nameDriver != null && !_nameDriver.trim().isEmpty())
			result += ", _nameDriver: " + _nameDriver;
		if (_sortFld != null && !_sortFld.trim().isEmpty())
			result += ", _sortFld: " + _sortFld;
		if (_maxVolUnitIso != null && !_maxVolUnitIso.trim().isEmpty())
			result += ", _maxVolUnitIso: " + _maxVolUnitIso;
		if (_dcCustomMat != null && !_dcCustomMat.trim().isEmpty())
			result += ", _dcCustomMat: " + _dcCustomMat;
		if (_baseUom != null && !_baseUom.trim().isEmpty())
			result += ", _baseUom: " + _baseUom;
		if (_flagPackgLvPrint != null && !_flagPackgLvPrint.trim().isEmpty())
			result += ", _flagPackgLvPrint: " + _flagPackgLvPrint;
		if (_content != null && !_content.trim().isEmpty())
			result += ", _content: " + _content;
		if (_distance != null)
			result += ", _distance: " + _distance;
		if (_unitOfWt != null && !_unitOfWt.trim().isEmpty())
			result += ", _unitOfWt: " + _unitOfWt;
		if (_lenght != null)
			result += ", _lenght: " + _lenght;
		if (_statusObsolet != null && !_statusObsolet.trim().isEmpty())
			result += ", _statusObsolet: " + _statusObsolet;
		if (_client != null && !_client.trim().isEmpty())
			result += ", _client: " + _client;
		if (_totalVol != null)
			result += ", _totalVol: " + _totalVol;
		if (_tareVol != null)
			result += ", _tareVol: " + _tareVol;
		if (_wghtTolHu != null)
			result += ", _wghtTolHu: " + _wghtTolHu;
		if (_huGrp3 != null && !_huGrp3.trim().isEmpty())
			result += ", _huGrp3: " + _huGrp3;
		if (_createdTime != null)
			result += ", _createdTime: " + _createdTime;
		if (_packgInstruct != null && !_packgInstruct.trim().isEmpty())
			result += ", _packgInstruct: " + _packgInstruct;
		if (_packMatName != null && !_packMatName.trim().isEmpty())
			result += ", _packMatName: " + _packMatName;
		if (_maxUnitOfWghtIso != null && !_maxUnitOfWghtIso.trim().isEmpty())
			result += ", _maxUnitOfWghtIso: " + _maxUnitOfWghtIso;
		if (_closedBox != null && !_closedBox.trim().isEmpty())
			result += ", _closedBox: " + _closedBox;
		if (_changedBy != null && !_changedBy.trim().isEmpty())
			result += ", _changedBy: " + _changedBy;
		if (_matGrpSm != null && !_matGrpSm.trim().isEmpty())
			result += ", _matGrpSm: " + _matGrpSm;
		if (_volTolHu != null)
			result += ", _volTolHu: " + _volTolHu;
		if (_width != null)
			result += ", _width: " + _width;
		if (_lgthLoadUnitIso != null && !_lgthLoadUnitIso.trim().isEmpty())
			result += ", _lgthLoadUnitIso: " + _lgthLoadUnitIso;
		if (_flagNoExtLable != null && !_flagNoExtLable.trim().isEmpty())
			result += ", _flagNoExtLable: " + _flagNoExtLable;
		if (_tareWght != null)
			result += ", _tareWght: " + _tareWght;
		if (_unitOfDist != null && !_unitOfDist.trim().isEmpty())
			result += ", _unitOfDist: " + _unitOfDist;
		if (_wghtVolFix != null && !_wghtVolFix.trim().isEmpty())
			result += ", _wghtVolFix: " + _wghtVolFix;
		if (_packMatType != null && !_packMatType.trim().isEmpty())
			result += ", _packMatType: " + _packMatType;
		if (_unitOfDistIso != null && !_unitOfDistIso.trim().isEmpty())
			result += ", _unitOfDistIso: " + _unitOfDistIso;
		if (_loadVol != null)
			result += ", _loadVol: " + _loadVol;
		if (_lgthLoad != null)
			result += ", _lgthLoad: " + _lgthLoad;
		if (_plant != null && !_plant.trim().isEmpty())
			result += ", _plant: " + _plant;
		if (_packMatObject != null && !_packMatObject.trim().isEmpty())
			result += ", _packMatObject: " + _packMatObject;
		if (_stgeLoc != null && !_stgeLoc.trim().isEmpty())
			result += ", _stgeLoc: " + _stgeLoc;
		if (_packMat != null && !_packMat.trim().isEmpty())
			result += ", _packMat: " + _packMat;
		if (_huGrp2 != null && !_huGrp2.trim().isEmpty())
			result += ", _huGrp2: " + _huGrp2;
		if (_noSimilarPackMat != null)
			result += ", _noSimilarPackMat: " + _noSimilarPackMat;
		if (_packMatCustomer != null && !_packMatCustomer.trim().isEmpty())
			result += ", _packMatCustomer: " + _packMatCustomer;
		if (_shipPoint != null && !_shipPoint.trim().isEmpty())
			result += ", _shipPoint: " + _shipPoint;
		if (_huId != null && !_huId.trim().isEmpty())
			result += ", _huId: " + _huId;
		if (_extIdHu2 != null && !_extIdHu2.trim().isEmpty())
			result += ", _extIdHu2: " + _extIdHu2;
		if (_loadWght != null)
			result += ", _loadWght: " + _loadWght;
		if (_huExid != null && !_huExid.trim().isEmpty())
			result += ", _huExid: " + _huExid;
		if (_allowedVol != null)
			result += ", _allowedVol: " + _allowedVol;
		if (_itemCateg != null && !_itemCateg.trim().isEmpty())
			result += ", _itemCateg: " + _itemCateg;
		if (_salesorg != null && !_salesorg.trim().isEmpty())
			result += ", _salesorg: " + _salesorg;
		if (_maxUnitOfWght != null && !_maxUnitOfWght.trim().isEmpty())
			result += ", _maxUnitOfWght: " + _maxUnitOfWght;
		if (_changedDate != null)
			result += ", _changedDate: " + _changedDate;
		if (_changedTime != null)
			result += ", _changedTime: " + _changedTime;
		if (_height != null)
			result += ", _height: " + _height;
		if (_lPackgStatusHu != null && !_lPackgStatusHu.trim().isEmpty())
			result += ", _lPackgStatusHu: " + _lPackgStatusHu;
		if (_unitOfWtIso != null && !_unitOfWtIso.trim().isEmpty())
			result += ", _unitOfWtIso: " + _unitOfWtIso;
		if (_huStorLoc != null && !_huStorLoc.trim().isEmpty())
			result += ", _huStorLoc: " + _huStorLoc;
		if (_huExidType != null && !_huExidType.trim().isEmpty())
			result += ", _huExidType: " + _huExidType;
		if (_createdBy != null && !_createdBy.trim().isEmpty())
			result += ", _createdBy: " + _createdBy;
		if (_unitDim != null && !_unitDim.trim().isEmpty())
			result += ", _unitDim: " + _unitDim;
		if (_lgthLoadUnit != null && !_lgthLoadUnit.trim().isEmpty())
			result += ", _lgthLoadUnit: " + _lgthLoadUnit;
		if (_volumeunit != null && !_volumeunit.trim().isEmpty())
			result += ", _volumeunit: " + _volumeunit;
		if (_allowedWght != null)
			result += ", _allowedWght: " + _allowedWght;
		if (_containerStat != null && !_containerStat.trim().isEmpty())
			result += ", _containerStat: " + _containerStat;
		if (_higherLevelHu != null && !_higherLevelHu.trim().isEmpty())
			result += ", _higherLevelHu: " + _higherLevelHu;
		if (_cntryShpMatIso != null && !_cntryShpMatIso.trim().isEmpty())
			result += ", _cntryShpMatIso: " + _cntryShpMatIso;
		if (_huGrp1 != null && !_huGrp1.trim().isEmpty())
			result += ", _huGrp1: " + _huGrp1;
		if (_huGrp5 != null && !_huGrp5.trim().isEmpty())
			result += ", _huGrp5: " + _huGrp5;
		if (_createdDate != null)
			result += ", _createdDate: " + _createdDate;
		if (_cntryShpMat != null && !_cntryShpMat.trim().isEmpty())
			result += ", _cntryShpMat: " + _cntryShpMat;
		if (_handle != null && !_handle.trim().isEmpty())
			result += ", _handle: " + _handle;
		if (_baseUomIso != null && !_baseUomIso.trim().isEmpty())
			result += ", _baseUomIso: " + _baseUomIso;
		if (_packMatCat != null && !_packMatCat.trim().isEmpty())
			result += ", _packMatCat: " + _packMatCat;
		if (_volumeunitIso != null && !_volumeunitIso.trim().isEmpty())
			result += ", _volumeunitIso: " + _volumeunitIso;
		if (_nationalityDriverIso != null && !_nationalityDriverIso.trim().isEmpty())
			result += ", _nationalityDriverIso: " + _nationalityDriverIso;
		if (_nationalityDriver != null && !_nationalityDriver.trim().isEmpty())
			result += ", _nationalityDriver: " + _nationalityDriver;
		if (_unitDimIso != null && !_unitDimIso.trim().isEmpty())
			result += ", _unitDimIso: " + _unitDimIso;
		if (_travelTimeUnit != null && !_travelTimeUnit.trim().isEmpty())
			result += ", _travelTimeUnit: " + _travelTimeUnit;
		if (_totalWght != null)
			result += ", _totalWght: " + _totalWght;
		if (_travelTimeUnitIso != null && !_travelTimeUnitIso.trim().isEmpty())
			result += ", _travelTimeUnitIso: " + _travelTimeUnitIso;
		if (_loadingPoint != null && !_loadingPoint.trim().isEmpty())
			result += ", _loadingPoint: " + _loadingPoint;
		if (_flagPackgLvDangGoods != null && !_flagPackgLvDangGoods.trim().isEmpty())
			result += ", _flagPackgLvDangGoods: " + _flagPackgLvDangGoods;
		if (_nameCoDriver != null && !_nameCoDriver.trim().isEmpty())
			result += ", _nameCoDriver: " + _nameCoDriver;
		if (_maxVolUnit != null && !_maxVolUnit.trim().isEmpty())
			result += ", _maxVolUnit: " + _maxVolUnit;
		return result;
	}
}