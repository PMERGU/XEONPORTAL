package za.co.xeon.external.sap.hibersap.forge.rfc;

import java.util.List;

import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.Table;

import za.co.xeon.external.sap.hibersap.forge.dto.sr.SBestq;
import za.co.xeon.external.sap.hibersap.forge.dto.sr.SLgber;
import za.co.xeon.external.sap.hibersap.forge.dto.sr.SLgnum;
import za.co.xeon.external.sap.hibersap.forge.dto.sr.SLgtyp;
import za.co.xeon.external.sap.hibersap.forge.dto.sr.SMatkl;
import za.co.xeon.external.sap.hibersap.forge.dto.sr.SMatnr;
import za.co.xeon.external.sap.hibersap.forge.dto.sr.SWerks;
import za.co.xeon.external.sap.hibersap.forge.dto.sr.StockData;
import za.co.xeon.external.sap.hibersap.forge.dto.sr.StockInventory;

@Bapi("Z_STOCK_INVENTORY")
public class ZStockInventory {

	@Table
	@Parameter("S_LGNUM")
	private List<SLgnum> _sLgnum;
	@Table
	@Parameter("STOCK_INVENTORY")
	private List<StockInventory> _stockInventory;
	@Table
	@Parameter("S_BESTQ")
	private List<SBestq> _sBestq;
	@Table
	@Parameter("STOCK_DATA")
	private List<StockData> _stockData;
	@Table
	@Parameter("S_MATKL")
	private List<SMatkl> _sMatkl;
	@Table
	@Parameter("S_WERKS")
	private List<SWerks> _sWerks;
	@Table
	@Parameter("S_LGBER")
	private List<SLgber> _sLgber;
	@Table
	@Parameter("S_LGTYP")
	private List<SLgtyp> _sLgtyp;
	@Table
	@Parameter("S_MATNR")
	private List<SMatnr> _sMatnr;

	public ZStockInventory() {
	}

	public List<SLgnum> get_sLgnum() {
		return this._sLgnum;
	}

	public void set_sLgnum(final List<SLgnum> _sLgnum) {
		this._sLgnum = _sLgnum;
	}

	public List<StockInventory> get_stockInventory() {
		return this._stockInventory;
	}

	public void set_stockInventory(final List<StockInventory> _stockInventory) {
		this._stockInventory = _stockInventory;
	}

	public List<SBestq> get_sBestq() {
		return this._sBestq;
	}

	public void set_sBestq(final List<SBestq> _sBestq) {
		this._sBestq = _sBestq;
	}

	public List<StockData> get_stockData() {
		return this._stockData;
	}

	public void set_stockData(final List<StockData> _stockData) {
		this._stockData = _stockData;
	}

	public List<SMatkl> get_sMatkl() {
		return this._sMatkl;
	}

	public void set_sMatkl(final List<SMatkl> _sMatkl) {
		this._sMatkl = _sMatkl;
	}

	public List<SWerks> get_sWerks() {
		return this._sWerks;
	}

	public void set_sWerks(final List<SWerks> _sWerks) {
		this._sWerks = _sWerks;
	}

	public List<SLgber> get_sLgber() {
		return this._sLgber;
	}

	public void set_sLgber(final List<SLgber> _sLgber) {
		this._sLgber = _sLgber;
	}

	public List<SLgtyp> get_sLgtyp() {
		return this._sLgtyp;
	}

	public void set_sLgtyp(final List<SLgtyp> _sLgtyp) {
		this._sLgtyp = _sLgtyp;
	}

	public List<SMatnr> get_sMatnr() {
		return this._sMatnr;
	}

	public void set_sMatnr(final List<SMatnr> _sMatnr) {
		this._sMatnr = _sMatnr;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (_sLgnum != null)
			result += "_sLgnum: " + _sLgnum;
		if (_stockInventory != null)
			result += ", _stockInventory: " + _stockInventory;
		if (_sBestq != null)
			result += ", _sBestq: " + _sBestq;
		if (_stockData != null)
			result += ", _stockData: " + _stockData;
		if (_sMatkl != null)
			result += ", _sMatkl: " + _sMatkl;
		if (_sWerks != null)
			result += ", _sWerks: " + _sWerks;
		if (_sLgber != null)
			result += ", _sLgber: " + _sLgber;
		if (_sLgtyp != null)
			result += ", _sLgtyp: " + _sLgtyp;
		if (_sMatnr != null)
			result += ", _sMatnr: " + _sMatnr;
		return result;
	}
}