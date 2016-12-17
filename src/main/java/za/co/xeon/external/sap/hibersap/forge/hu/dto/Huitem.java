package za.co.xeon.external.sap.hibersap.forge.hu.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;
import java.util.Date;
import java.math.BigDecimal;
import java.lang.Override;

@BapiStructure
public class Huitem {

	@Parameter("BASE_UNIT_QTY")
	String _baseUnitQty;
	@Parameter("LOWER_LEVEL_EXID")
	String _lowerLevelExid;
	@Parameter("MATERIAL")
	String _material;
	@Parameter("BATCH")
	String _batch;
	@Parameter("NUMBER_PACK_MAT")
	Integer _numberPackMat;
	@Parameter("ALT_UNIT_QTY")
	String _altUnitQty;
	@Parameter("HU_ITEM_NUMBER")
	String _huItemNumber;
	@Parameter("OBJECT_DOC")
	String _objectDoc;
	@Parameter("HU_EXID")
	String _huExid;
	@Parameter("PACK_QTY_FLOAT")
	Double _packQtyFloat;
	@Parameter("STOCK_CAT")
	String _stockCat;
	@Parameter("ITEM_CATEG")
	String _itemCateg;
	@Parameter("INSPLOT")
	String _insplot;
	@Parameter("ALT_UNIT_QTY_ISO")
	String _altUnitQtyIso;
	@Parameter("SP_STCK_NO")
	String _spStckNo;
	@Parameter("CLIENT")
	String _client;
	@Parameter("FLAG_SUPLMT_ITEM")
	String _flagSuplmtItem;
	@Parameter("NO_OF_SERIAL_NUMBERS")
	Integer _noOfSerialNumbers;
	@Parameter("SPEC_STOCK")
	String _specStock;
	@Parameter("EXPIRYDATE")
	Date _expirydate;
	@Parameter("PACK_MAT_NAME")
	String _packMatName;
	@Parameter("HU_ITEM_TYPE")
	String _huItemType;
	@Parameter("MATERIAL_PARTNER")
	String _materialPartner;
	@Parameter("PACK_QTY")
	BigDecimal _packQty;
	@Parameter("OBJ_ITEM_NUMBER")
	String _objItemNumber;
	@Parameter("BASE_UNIT_QTY_ISO")
	String _baseUnitQtyIso;
	@Parameter("INT_OBJ_NO")
	String _intObjNo;
	@Parameter("PACK_MAT_TYPE")
	String _packMatType;
	@Parameter("GR_DATE")
	Date _grDate;
	@Parameter("SERNO_PROF")
	String _sernoProf;
	@Parameter("PLANT")
	String _plant;
	@Parameter("STGE_LOC")
	String _stgeLoc;

	public String get_baseUnitQty() {
		return this._baseUnitQty;
	}

	public void set_baseUnitQty(final String _baseUnitQty) {
		this._baseUnitQty = _baseUnitQty;
	}

	public String get_lowerLevelExid() {
		return this._lowerLevelExid;
	}

	public void set_lowerLevelExid(final String _lowerLevelExid) {
		this._lowerLevelExid = _lowerLevelExid;
	}

	public String get_material() {
		return this._material;
	}

	public void set_material(final String _material) {
		this._material = _material;
	}

	public String get_batch() {
		return this._batch;
	}

	public void set_batch(final String _batch) {
		this._batch = _batch;
	}

	public Integer get_numberPackMat() {
		return this._numberPackMat;
	}

	public void set_numberPackMat(final Integer _numberPackMat) {
		this._numberPackMat = _numberPackMat;
	}

	public String get_altUnitQty() {
		return this._altUnitQty;
	}

	public void set_altUnitQty(final String _altUnitQty) {
		this._altUnitQty = _altUnitQty;
	}

	public String get_huItemNumber() {
		return this._huItemNumber;
	}

	public void set_huItemNumber(final String _huItemNumber) {
		this._huItemNumber = _huItemNumber;
	}

	public String get_objectDoc() {
		return this._objectDoc;
	}

	public void set_objectDoc(final String _objectDoc) {
		this._objectDoc = _objectDoc;
	}

	public String get_huExid() {
		return this._huExid;
	}

	public void set_huExid(final String _huExid) {
		this._huExid = _huExid;
	}

	public Double get_packQtyFloat() {
		return this._packQtyFloat;
	}

	public void set_packQtyFloat(final Double _packQtyFloat) {
		this._packQtyFloat = _packQtyFloat;
	}

	public String get_stockCat() {
		return this._stockCat;
	}

	public void set_stockCat(final String _stockCat) {
		this._stockCat = _stockCat;
	}

	public String get_itemCateg() {
		return this._itemCateg;
	}

	public void set_itemCateg(final String _itemCateg) {
		this._itemCateg = _itemCateg;
	}

	public String get_insplot() {
		return this._insplot;
	}

	public void set_insplot(final String _insplot) {
		this._insplot = _insplot;
	}

	public String get_altUnitQtyIso() {
		return this._altUnitQtyIso;
	}

	public void set_altUnitQtyIso(final String _altUnitQtyIso) {
		this._altUnitQtyIso = _altUnitQtyIso;
	}

	public String get_spStckNo() {
		return this._spStckNo;
	}

	public void set_spStckNo(final String _spStckNo) {
		this._spStckNo = _spStckNo;
	}

	public String get_client() {
		return this._client;
	}

	public void set_client(final String _client) {
		this._client = _client;
	}

	public String get_flagSuplmtItem() {
		return this._flagSuplmtItem;
	}

	public void set_flagSuplmtItem(final String _flagSuplmtItem) {
		this._flagSuplmtItem = _flagSuplmtItem;
	}

	public Integer get_noOfSerialNumbers() {
		return this._noOfSerialNumbers;
	}

	public void set_noOfSerialNumbers(final Integer _noOfSerialNumbers) {
		this._noOfSerialNumbers = _noOfSerialNumbers;
	}

	public String get_specStock() {
		return this._specStock;
	}

	public void set_specStock(final String _specStock) {
		this._specStock = _specStock;
	}

	public Date get_expirydate() {
		return this._expirydate;
	}

	public void set_expirydate(final Date _expirydate) {
		this._expirydate = _expirydate;
	}

	public String get_packMatName() {
		return this._packMatName;
	}

	public void set_packMatName(final String _packMatName) {
		this._packMatName = _packMatName;
	}

	public String get_huItemType() {
		return this._huItemType;
	}

	public void set_huItemType(final String _huItemType) {
		this._huItemType = _huItemType;
	}

	public String get_materialPartner() {
		return this._materialPartner;
	}

	public void set_materialPartner(final String _materialPartner) {
		this._materialPartner = _materialPartner;
	}

	public BigDecimal get_packQty() {
		return this._packQty;
	}

	public void set_packQty(final BigDecimal _packQty) {
		this._packQty = _packQty;
	}

	public String get_objItemNumber() {
		return this._objItemNumber;
	}

	public void set_objItemNumber(final String _objItemNumber) {
		this._objItemNumber = _objItemNumber;
	}

	public String get_baseUnitQtyIso() {
		return this._baseUnitQtyIso;
	}

	public void set_baseUnitQtyIso(final String _baseUnitQtyIso) {
		this._baseUnitQtyIso = _baseUnitQtyIso;
	}

	public String get_intObjNo() {
		return this._intObjNo;
	}

	public void set_intObjNo(final String _intObjNo) {
		this._intObjNo = _intObjNo;
	}

	public String get_packMatType() {
		return this._packMatType;
	}

	public void set_packMatType(final String _packMatType) {
		this._packMatType = _packMatType;
	}

	public Date get_grDate() {
		return this._grDate;
	}

	public void set_grDate(final Date _grDate) {
		this._grDate = _grDate;
	}

	public String get_sernoProf() {
		return this._sernoProf;
	}

	public void set_sernoProf(final String _sernoProf) {
		this._sernoProf = _sernoProf;
	}

	public String get_plant() {
		return this._plant;
	}

	public void set_plant(final String _plant) {
		this._plant = _plant;
	}

	public String get_stgeLoc() {
		return this._stgeLoc;
	}

	public void set_stgeLoc(final String _stgeLoc) {
		this._stgeLoc = _stgeLoc;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (_baseUnitQty != null && !_baseUnitQty.trim().isEmpty())
			result += "_baseUnitQty: " + _baseUnitQty;
		if (_lowerLevelExid != null && !_lowerLevelExid.trim().isEmpty())
			result += ", _lowerLevelExid: " + _lowerLevelExid;
		if (_material != null && !_material.trim().isEmpty())
			result += ", _material: " + _material;
		if (_batch != null && !_batch.trim().isEmpty())
			result += ", _batch: " + _batch;
		if (_numberPackMat != null)
			result += ", _numberPackMat: " + _numberPackMat;
		if (_altUnitQty != null && !_altUnitQty.trim().isEmpty())
			result += ", _altUnitQty: " + _altUnitQty;
		if (_huItemNumber != null && !_huItemNumber.trim().isEmpty())
			result += ", _huItemNumber: " + _huItemNumber;
		if (_objectDoc != null && !_objectDoc.trim().isEmpty())
			result += ", _objectDoc: " + _objectDoc;
		if (_huExid != null && !_huExid.trim().isEmpty())
			result += ", _huExid: " + _huExid;
		if (_packQtyFloat != null)
			result += ", _packQtyFloat: " + _packQtyFloat;
		if (_stockCat != null && !_stockCat.trim().isEmpty())
			result += ", _stockCat: " + _stockCat;
		if (_itemCateg != null && !_itemCateg.trim().isEmpty())
			result += ", _itemCateg: " + _itemCateg;
		if (_insplot != null && !_insplot.trim().isEmpty())
			result += ", _insplot: " + _insplot;
		if (_altUnitQtyIso != null && !_altUnitQtyIso.trim().isEmpty())
			result += ", _altUnitQtyIso: " + _altUnitQtyIso;
		if (_spStckNo != null && !_spStckNo.trim().isEmpty())
			result += ", _spStckNo: " + _spStckNo;
		if (_client != null && !_client.trim().isEmpty())
			result += ", _client: " + _client;
		if (_flagSuplmtItem != null && !_flagSuplmtItem.trim().isEmpty())
			result += ", _flagSuplmtItem: " + _flagSuplmtItem;
		if (_noOfSerialNumbers != null)
			result += ", _noOfSerialNumbers: " + _noOfSerialNumbers;
		if (_specStock != null && !_specStock.trim().isEmpty())
			result += ", _specStock: " + _specStock;
		if (_expirydate != null)
			result += ", _expirydate: " + _expirydate;
		if (_packMatName != null && !_packMatName.trim().isEmpty())
			result += ", _packMatName: " + _packMatName;
		if (_huItemType != null && !_huItemType.trim().isEmpty())
			result += ", _huItemType: " + _huItemType;
		if (_materialPartner != null && !_materialPartner.trim().isEmpty())
			result += ", _materialPartner: " + _materialPartner;
		if (_packQty != null)
			result += ", _packQty: " + _packQty;
		if (_objItemNumber != null && !_objItemNumber.trim().isEmpty())
			result += ", _objItemNumber: " + _objItemNumber;
		if (_baseUnitQtyIso != null && !_baseUnitQtyIso.trim().isEmpty())
			result += ", _baseUnitQtyIso: " + _baseUnitQtyIso;
		if (_intObjNo != null && !_intObjNo.trim().isEmpty())
			result += ", _intObjNo: " + _intObjNo;
		if (_packMatType != null && !_packMatType.trim().isEmpty())
			result += ", _packMatType: " + _packMatType;
		if (_grDate != null)
			result += ", _grDate: " + _grDate;
		if (_sernoProf != null && !_sernoProf.trim().isEmpty())
			result += ", _sernoProf: " + _sernoProf;
		if (_plant != null && !_plant.trim().isEmpty())
			result += ", _plant: " + _plant;
		if (_stgeLoc != null && !_stgeLoc.trim().isEmpty())
			result += ", _stgeLoc: " + _stgeLoc;
		return result;
	}
}