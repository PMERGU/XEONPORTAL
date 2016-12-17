package za.co.xeon.external.sap.hibersap.forge.ior.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;
import java.util.Date;
import java.math.BigDecimal;
import java.lang.Override;

@BapiStructure
public class EtCustOrders {

	@Parameter("BSTKD")
	String _bstkd;
	@Parameter("CHARG")
	String _charg;
	@Parameter("DBELN")
	String _dbeln;
	@Parameter("PARVW_AG")
	String _parvwAg;
	@Parameter("PODAT")
	Date _podat;
	@Parameter("PDSTK")
	String _pdstk;
	@Parameter("POTIM")
	Date _potim;
	@Parameter("ERNAM")
	String _ernam;
	@Parameter("PARVW_WE")
	String _parvwWe;
	@Parameter("PARVW_YE")
	String _parvwYe;
	@Parameter("PARVW_YC")
	String _parvwYc;
	@Parameter("SKUNNR")
	String _skunnr;
	@Parameter("AUDAT")
	Date _audat;
	@Parameter("POSNR")
	String _posnr;
	@Parameter("FKSTK")
	String _fkstk;
	@Parameter("DRZET")
	Date _drzet;
	@Parameter("FKDAT")
	Date _fkdat;
	@Parameter("ARKTX")
	String _arktx;
	@Parameter("ERZET")
	Date _erzet;
	@Parameter("VBELN")
	String _vbeln;
	@Parameter("AUART")
	String _auart;
	@Parameter("KWMENG")
	BigDecimal _kwmeng;
	@Parameter("PARVW_YH")
	String _parvwYh;
	@Parameter("VRKME")
	String _vrkme;
	@Parameter("MATNR")
	String _matnr;

	public String get_bstkd() {
		return this._bstkd;
	}

	public void set_bstkd(final String _bstkd) {
		this._bstkd = _bstkd;
	}

	public String get_charg() {
		return this._charg;
	}

	public void set_charg(final String _charg) {
		this._charg = _charg;
	}

	public String get_dbeln() {
		return this._dbeln;
	}

	public void set_dbeln(final String _dbeln) {
		this._dbeln = _dbeln;
	}

	public String get_parvwAg() {
		return this._parvwAg;
	}

	public void set_parvwAg(final String _parvwAg) {
		this._parvwAg = _parvwAg;
	}

	public Date get_podat() {
		return this._podat;
	}

	public void set_podat(final Date _podat) {
		this._podat = _podat;
	}

	public String get_pdstk() {
		return this._pdstk;
	}

	public void set_pdstk(final String _pdstk) {
		this._pdstk = _pdstk;
	}

	public Date get_potim() {
		return this._potim;
	}

	public void set_potim(final Date _potim) {
		this._potim = _potim;
	}

	public String get_ernam() {
		return this._ernam;
	}

	public void set_ernam(final String _ernam) {
		this._ernam = _ernam;
	}

	public String get_parvwWe() {
		return this._parvwWe;
	}

	public void set_parvwWe(final String _parvwWe) {
		this._parvwWe = _parvwWe;
	}

	public String get_parvwYe() {
		return this._parvwYe;
	}

	public void set_parvwYe(final String _parvwYe) {
		this._parvwYe = _parvwYe;
	}

	public String get_parvwYc() {
		return this._parvwYc;
	}

	public void set_parvwYc(final String _parvwYc) {
		this._parvwYc = _parvwYc;
	}

	public String get_skunnr() {
		return this._skunnr;
	}

	public void set_skunnr(final String _skunnr) {
		this._skunnr = _skunnr;
	}

	public Date get_audat() {
		return this._audat;
	}

	public void set_audat(final Date _audat) {
		this._audat = _audat;
	}

	public String get_posnr() {
		return this._posnr;
	}

	public void set_posnr(final String _posnr) {
		this._posnr = _posnr;
	}

	public String get_fkstk() {
		return this._fkstk;
	}

	public void set_fkstk(final String _fkstk) {
		this._fkstk = _fkstk;
	}

	public Date get_drzet() {
		return this._drzet;
	}

	public void set_drzet(final Date _drzet) {
		this._drzet = _drzet;
	}

	public Date get_fkdat() {
		return this._fkdat;
	}

	public void set_fkdat(final Date _fkdat) {
		this._fkdat = _fkdat;
	}

	public String get_arktx() {
		return this._arktx;
	}

	public void set_arktx(final String _arktx) {
		this._arktx = _arktx;
	}

	public Date get_erzet() {
		return this._erzet;
	}

	public void set_erzet(final Date _erzet) {
		this._erzet = _erzet;
	}

	public String get_vbeln() {
		return this._vbeln;
	}

	public void set_vbeln(final String _vbeln) {
		this._vbeln = _vbeln;
	}

	public String get_auart() {
		return this._auart;
	}

	public void set_auart(final String _auart) {
		this._auart = _auart;
	}

	public BigDecimal get_kwmeng() {
		return this._kwmeng;
	}

	public void set_kwmeng(final BigDecimal _kwmeng) {
		this._kwmeng = _kwmeng;
	}

	public String get_parvwYh() {
		return this._parvwYh;
	}

	public void set_parvwYh(final String _parvwYh) {
		this._parvwYh = _parvwYh;
	}

	public String get_vrkme() {
		return this._vrkme;
	}

	public void set_vrkme(final String _vrkme) {
		this._vrkme = _vrkme;
	}

	public String get_matnr() {
		return this._matnr;
	}

	public void set_matnr(final String _matnr) {
		this._matnr = _matnr;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (_bstkd != null && !_bstkd.trim().isEmpty())
			result += "_bstkd: " + _bstkd;
		if (_charg != null && !_charg.trim().isEmpty())
			result += ", _charg: " + _charg;
		if (_dbeln != null && !_dbeln.trim().isEmpty())
			result += ", _dbeln: " + _dbeln;
		if (_parvwAg != null && !_parvwAg.trim().isEmpty())
			result += ", _parvwAg: " + _parvwAg;
		if (_podat != null)
			result += ", _podat: " + _podat;
		if (_pdstk != null && !_pdstk.trim().isEmpty())
			result += ", _pdstk: " + _pdstk;
		if (_potim != null)
			result += ", _potim: " + _potim;
		if (_ernam != null && !_ernam.trim().isEmpty())
			result += ", _ernam: " + _ernam;
		if (_parvwWe != null && !_parvwWe.trim().isEmpty())
			result += ", _parvwWe: " + _parvwWe;
		if (_parvwYe != null && !_parvwYe.trim().isEmpty())
			result += ", _parvwYe: " + _parvwYe;
		if (_parvwYc != null && !_parvwYc.trim().isEmpty())
			result += ", _parvwYc: " + _parvwYc;
		if (_skunnr != null && !_skunnr.trim().isEmpty())
			result += ", _skunnr: " + _skunnr;
		if (_audat != null)
			result += ", _audat: " + _audat;
		if (_posnr != null && !_posnr.trim().isEmpty())
			result += ", _posnr: " + _posnr;
		if (_fkstk != null && !_fkstk.trim().isEmpty())
			result += ", _fkstk: " + _fkstk;
		if (_drzet != null)
			result += ", _drzet: " + _drzet;
		if (_fkdat != null)
			result += ", _fkdat: " + _fkdat;
		if (_arktx != null && !_arktx.trim().isEmpty())
			result += ", _arktx: " + _arktx;
		if (_erzet != null)
			result += ", _erzet: " + _erzet;
		if (_vbeln != null && !_vbeln.trim().isEmpty())
			result += ", _vbeln: " + _vbeln;
		if (_auart != null && !_auart.trim().isEmpty())
			result += ", _auart: " + _auart;
		if (_kwmeng != null)
			result += ", _kwmeng: " + _kwmeng;
		if (_parvwYh != null && !_parvwYh.trim().isEmpty())
			result += ", _parvwYh: " + _parvwYh;
		if (_vrkme != null && !_vrkme.trim().isEmpty())
			result += ", _vrkme: " + _vrkme;
		if (_matnr != null && !_matnr.trim().isEmpty())
			result += ", _matnr: " + _matnr;
		return result;
	}
}