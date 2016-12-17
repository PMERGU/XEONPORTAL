package za.co.xeon.external.sap.hibersap.forge.od.dto;

import org.hibersap.annotations.BapiStructure;
import java.util.Date;
import org.hibersap.annotations.Parameter;
import java.math.BigDecimal;
import java.lang.Override;

@BapiStructure
public class GtCustOrdersDetail {

	@Parameter("DATEN")
	Date _daten;
	@Parameter("DATBG")
	Date _datbg;
	@Parameter("ORIGIN")
	String _origin;
	@Parameter("IHREZ")
	String _ihrez;
	@Parameter("VLSTK")
	String _vlstk;
	@Parameter("PARVW_AG")
	String _parvwAg;
	@Parameter("PDSTK")
	String _pdstk;
	@Parameter("LVSTK")
	String _lvstk;
	@Parameter("SUBMI")
	String _submi;
	@Parameter("PARVW_WE")
	String _parvwWe;
	@Parameter("OBIOLI")
	Date _obioli;
	@Parameter("VSART")
	String _vsart;
	@Parameter("TRSTA")
	String _trsta;
	@Parameter("FKIVK")
	String _fkivk;
	@Parameter("PARVW_YC")
	String _parvwYc;
	@Parameter("DESTIN")
	String _destin;
	@Parameter("VESSEL")
	String _vessel;
	@Parameter("AUDAT")
	Date _audat;
	@Parameter("TKNUM")
	String _tknum;
	@Parameter("KOSTK")
	String _kostk;
	@Parameter("CONTNR")
	String _contnr;
	@Parameter("CONSOL")
	String _consol;
	@Parameter("FKSTK")
	String _fkstk;
	@Parameter("SDABW")
	String _sdabw;
	@Parameter("COMMOD")
	String _commod;
	@Parameter("HBIOLA")
	String _hbiola;
	@Parameter("UATBG")
	Date _uatbg;
	@Parameter("UATEN")
	Date _uaten;
	@Parameter("PARVW_YH")
	String _parvwYh;
	@Parameter("BSTKD")
	String _bstkd;
	@Parameter("OBIOLA")
	String _obiola;
	@Parameter("DBELN")
	String _dbeln;
	@Parameter("ETD")
	Date _etd;
	@Parameter("LFIMG")
	BigDecimal _lfimg;
	@Parameter("BELNR")
	String _belnr;
	@Parameter("VESVOY")
	String _vesvoy;
	@Parameter("CMGST")
	String _cmgst;
	@Parameter("ETADAT")
	Date _etadat;
	@Parameter("WRBTR")
	BigDecimal _wrbtr;
	@Parameter("BEZEI")
	String _bezei;
	@Parameter("OWNREF")
	String _ownref;
	@Parameter("PARVW_YE")
	String _parvwYe;
	@Parameter("HBIOL1")
	Date _hbiol1;
	@Parameter("SKUNNR")
	String _skunnr;
	@Parameter("VSBED")
	String _vsbed;
	@Parameter("KOQUK")
	String _koquk;
	@Parameter("WBSTK")
	String _wbstk;
	@Parameter("IHREZ_E")
	String _ihrezE;
	@Parameter("ERZET")
	Date _erzet;
	@Parameter("VBELN")
	String _vbeln;
	@Parameter("SHIPER")
	String _shiper;
	@Parameter("KWMENG")
	BigDecimal _kwmeng;
	@Parameter("PKSTK")
	String _pkstk;
	@Parameter("AUART")
	String _auart;

	public Date get_daten() {
		return this._daten;
	}

	public void set_daten(final Date _daten) {
		this._daten = _daten;
	}

	public Date get_datbg() {
		return this._datbg;
	}

	public void set_datbg(final Date _datbg) {
		this._datbg = _datbg;
	}

	public String get_origin() {
		return this._origin;
	}

	public void set_origin(final String _origin) {
		this._origin = _origin;
	}

	public String get_ihrez() {
		return this._ihrez;
	}

	public void set_ihrez(final String _ihrez) {
		this._ihrez = _ihrez;
	}

	public String get_vlstk() {
		return this._vlstk;
	}

	public void set_vlstk(final String _vlstk) {
		this._vlstk = _vlstk;
	}

	public String get_parvwAg() {
		return this._parvwAg;
	}

	public void set_parvwAg(final String _parvwAg) {
		this._parvwAg = _parvwAg;
	}

	public String get_pdstk() {
		return this._pdstk;
	}

	public void set_pdstk(final String _pdstk) {
		this._pdstk = _pdstk;
	}

	public String get_lvstk() {
		return this._lvstk;
	}

	public void set_lvstk(final String _lvstk) {
		this._lvstk = _lvstk;
	}

	public String get_submi() {
		return this._submi;
	}

	public void set_submi(final String _submi) {
		this._submi = _submi;
	}

	public String get_parvwWe() {
		return this._parvwWe;
	}

	public void set_parvwWe(final String _parvwWe) {
		this._parvwWe = _parvwWe;
	}

	public Date get_obioli() {
		return this._obioli;
	}

	public void set_obioli(final Date _obioli) {
		this._obioli = _obioli;
	}

	public String get_vsart() {
		return this._vsart;
	}

	public void set_vsart(final String _vsart) {
		this._vsart = _vsart;
	}

	public String get_trsta() {
		return this._trsta;
	}

	public void set_trsta(final String _trsta) {
		this._trsta = _trsta;
	}

	public String get_fkivk() {
		return this._fkivk;
	}

	public void set_fkivk(final String _fkivk) {
		this._fkivk = _fkivk;
	}

	public String get_parvwYc() {
		return this._parvwYc;
	}

	public void set_parvwYc(final String _parvwYc) {
		this._parvwYc = _parvwYc;
	}

	public String get_destin() {
		return this._destin;
	}

	public void set_destin(final String _destin) {
		this._destin = _destin;
	}

	public String get_vessel() {
		return this._vessel;
	}

	public void set_vessel(final String _vessel) {
		this._vessel = _vessel;
	}

	public Date get_audat() {
		return this._audat;
	}

	public void set_audat(final Date _audat) {
		this._audat = _audat;
	}

	public String get_tknum() {
		return this._tknum;
	}

	public void set_tknum(final String _tknum) {
		this._tknum = _tknum;
	}

	public String get_kostk() {
		return this._kostk;
	}

	public void set_kostk(final String _kostk) {
		this._kostk = _kostk;
	}

	public String get_contnr() {
		return this._contnr;
	}

	public void set_contnr(final String _contnr) {
		this._contnr = _contnr;
	}

	public String get_consol() {
		return this._consol;
	}

	public void set_consol(final String _consol) {
		this._consol = _consol;
	}

	public String get_fkstk() {
		return this._fkstk;
	}

	public void set_fkstk(final String _fkstk) {
		this._fkstk = _fkstk;
	}

	public String get_sdabw() {
		return this._sdabw;
	}

	public void set_sdabw(final String _sdabw) {
		this._sdabw = _sdabw;
	}

	public String get_commod() {
		return this._commod;
	}

	public void set_commod(final String _commod) {
		this._commod = _commod;
	}

	public String get_hbiola() {
		return this._hbiola;
	}

	public void set_hbiola(final String _hbiola) {
		this._hbiola = _hbiola;
	}

	public Date get_uatbg() {
		return this._uatbg;
	}

	public void set_uatbg(final Date _uatbg) {
		this._uatbg = _uatbg;
	}

	public Date get_uaten() {
		return this._uaten;
	}

	public void set_uaten(final Date _uaten) {
		this._uaten = _uaten;
	}

	public String get_parvwYh() {
		return this._parvwYh;
	}

	public void set_parvwYh(final String _parvwYh) {
		this._parvwYh = _parvwYh;
	}

	public String get_bstkd() {
		return this._bstkd;
	}

	public void set_bstkd(final String _bstkd) {
		this._bstkd = _bstkd;
	}

	public String get_obiola() {
		return this._obiola;
	}

	public void set_obiola(final String _obiola) {
		this._obiola = _obiola;
	}

	public String get_dbeln() {
		return this._dbeln;
	}

	public void set_dbeln(final String _dbeln) {
		this._dbeln = _dbeln;
	}

	public Date get_etd() {
		return this._etd;
	}

	public void set_etd(final Date _etd) {
		this._etd = _etd;
	}

	public BigDecimal get_lfimg() {
		return this._lfimg;
	}

	public void set_lfimg(final BigDecimal _lfimg) {
		this._lfimg = _lfimg;
	}

	public String get_belnr() {
		return this._belnr;
	}

	public void set_belnr(final String _belnr) {
		this._belnr = _belnr;
	}

	public String get_vesvoy() {
		return this._vesvoy;
	}

	public void set_vesvoy(final String _vesvoy) {
		this._vesvoy = _vesvoy;
	}

	public String get_cmgst() {
		return this._cmgst;
	}

	public void set_cmgst(final String _cmgst) {
		this._cmgst = _cmgst;
	}

	public Date get_etadat() {
		return this._etadat;
	}

	public void set_etadat(final Date _etadat) {
		this._etadat = _etadat;
	}

	public BigDecimal get_wrbtr() {
		return this._wrbtr;
	}

	public void set_wrbtr(final BigDecimal _wrbtr) {
		this._wrbtr = _wrbtr;
	}

	public String get_bezei() {
		return this._bezei;
	}

	public void set_bezei(final String _bezei) {
		this._bezei = _bezei;
	}

	public String get_ownref() {
		return this._ownref;
	}

	public void set_ownref(final String _ownref) {
		this._ownref = _ownref;
	}

	public String get_parvwYe() {
		return this._parvwYe;
	}

	public void set_parvwYe(final String _parvwYe) {
		this._parvwYe = _parvwYe;
	}

	public Date get_hbiol1() {
		return this._hbiol1;
	}

	public void set_hbiol1(final Date _hbiol1) {
		this._hbiol1 = _hbiol1;
	}

	public String get_skunnr() {
		return this._skunnr;
	}

	public void set_skunnr(final String _skunnr) {
		this._skunnr = _skunnr;
	}

	public String get_vsbed() {
		return this._vsbed;
	}

	public void set_vsbed(final String _vsbed) {
		this._vsbed = _vsbed;
	}

	public String get_koquk() {
		return this._koquk;
	}

	public void set_koquk(final String _koquk) {
		this._koquk = _koquk;
	}

	public String get_wbstk() {
		return this._wbstk;
	}

	public void set_wbstk(final String _wbstk) {
		this._wbstk = _wbstk;
	}

	public String get_ihrezE() {
		return this._ihrezE;
	}

	public void set_ihrezE(final String _ihrezE) {
		this._ihrezE = _ihrezE;
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

	public String get_shiper() {
		return this._shiper;
	}

	public void set_shiper(final String _shiper) {
		this._shiper = _shiper;
	}

	public BigDecimal get_kwmeng() {
		return this._kwmeng;
	}

	public void set_kwmeng(final BigDecimal _kwmeng) {
		this._kwmeng = _kwmeng;
	}

	public String get_pkstk() {
		return this._pkstk;
	}

	public void set_pkstk(final String _pkstk) {
		this._pkstk = _pkstk;
	}

	public String get_auart() {
		return this._auart;
	}

	public void set_auart(final String _auart) {
		this._auart = _auart;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (_daten != null)
			result += "_daten: " + _daten;
		if (_datbg != null)
			result += ", _datbg: " + _datbg;
		if (_origin != null && !_origin.trim().isEmpty())
			result += ", _origin: " + _origin;
		if (_ihrez != null && !_ihrez.trim().isEmpty())
			result += ", _ihrez: " + _ihrez;
		if (_vlstk != null && !_vlstk.trim().isEmpty())
			result += ", _vlstk: " + _vlstk;
		if (_parvwAg != null && !_parvwAg.trim().isEmpty())
			result += ", _parvwAg: " + _parvwAg;
		if (_pdstk != null && !_pdstk.trim().isEmpty())
			result += ", _pdstk: " + _pdstk;
		if (_lvstk != null && !_lvstk.trim().isEmpty())
			result += ", _lvstk: " + _lvstk;
		if (_submi != null && !_submi.trim().isEmpty())
			result += ", _submi: " + _submi;
		if (_parvwWe != null && !_parvwWe.trim().isEmpty())
			result += ", _parvwWe: " + _parvwWe;
		if (_obioli != null)
			result += ", _obioli: " + _obioli;
		if (_vsart != null && !_vsart.trim().isEmpty())
			result += ", _vsart: " + _vsart;
		if (_trsta != null && !_trsta.trim().isEmpty())
			result += ", _trsta: " + _trsta;
		if (_fkivk != null && !_fkivk.trim().isEmpty())
			result += ", _fkivk: " + _fkivk;
		if (_parvwYc != null && !_parvwYc.trim().isEmpty())
			result += ", _parvwYc: " + _parvwYc;
		if (_destin != null && !_destin.trim().isEmpty())
			result += ", _destin: " + _destin;
		if (_vessel != null && !_vessel.trim().isEmpty())
			result += ", _vessel: " + _vessel;
		if (_audat != null)
			result += ", _audat: " + _audat;
		if (_tknum != null && !_tknum.trim().isEmpty())
			result += ", _tknum: " + _tknum;
		if (_kostk != null && !_kostk.trim().isEmpty())
			result += ", _kostk: " + _kostk;
		if (_contnr != null && !_contnr.trim().isEmpty())
			result += ", _contnr: " + _contnr;
		if (_consol != null && !_consol.trim().isEmpty())
			result += ", _consol: " + _consol;
		if (_fkstk != null && !_fkstk.trim().isEmpty())
			result += ", _fkstk: " + _fkstk;
		if (_sdabw != null && !_sdabw.trim().isEmpty())
			result += ", _sdabw: " + _sdabw;
		if (_commod != null && !_commod.trim().isEmpty())
			result += ", _commod: " + _commod;
		if (_hbiola != null && !_hbiola.trim().isEmpty())
			result += ", _hbiola: " + _hbiola;
		if (_uatbg != null)
			result += ", _uatbg: " + _uatbg;
		if (_uaten != null)
			result += ", _uaten: " + _uaten;
		if (_parvwYh != null && !_parvwYh.trim().isEmpty())
			result += ", _parvwYh: " + _parvwYh;
		if (_bstkd != null && !_bstkd.trim().isEmpty())
			result += ", _bstkd: " + _bstkd;
		if (_obiola != null && !_obiola.trim().isEmpty())
			result += ", _obiola: " + _obiola;
		if (_dbeln != null && !_dbeln.trim().isEmpty())
			result += ", _dbeln: " + _dbeln;
		if (_etd != null)
			result += ", _etd: " + _etd;
		if (_lfimg != null)
			result += ", _lfimg: " + _lfimg;
		if (_belnr != null && !_belnr.trim().isEmpty())
			result += ", _belnr: " + _belnr;
		if (_vesvoy != null && !_vesvoy.trim().isEmpty())
			result += ", _vesvoy: " + _vesvoy;
		if (_cmgst != null && !_cmgst.trim().isEmpty())
			result += ", _cmgst: " + _cmgst;
		if (_etadat != null)
			result += ", _etadat: " + _etadat;
		if (_wrbtr != null)
			result += ", _wrbtr: " + _wrbtr;
		if (_bezei != null && !_bezei.trim().isEmpty())
			result += ", _bezei: " + _bezei;
		if (_ownref != null && !_ownref.trim().isEmpty())
			result += ", _ownref: " + _ownref;
		if (_parvwYe != null && !_parvwYe.trim().isEmpty())
			result += ", _parvwYe: " + _parvwYe;
		if (_hbiol1 != null)
			result += ", _hbiol1: " + _hbiol1;
		if (_skunnr != null && !_skunnr.trim().isEmpty())
			result += ", _skunnr: " + _skunnr;
		if (_vsbed != null && !_vsbed.trim().isEmpty())
			result += ", _vsbed: " + _vsbed;
		if (_koquk != null && !_koquk.trim().isEmpty())
			result += ", _koquk: " + _koquk;
		if (_wbstk != null && !_wbstk.trim().isEmpty())
			result += ", _wbstk: " + _wbstk;
		if (_ihrezE != null && !_ihrezE.trim().isEmpty())
			result += ", _ihrezE: " + _ihrezE;
		if (_erzet != null)
			result += ", _erzet: " + _erzet;
		if (_vbeln != null && !_vbeln.trim().isEmpty())
			result += ", _vbeln: " + _vbeln;
		if (_shiper != null && !_shiper.trim().isEmpty())
			result += ", _shiper: " + _shiper;
		if (_kwmeng != null)
			result += ", _kwmeng: " + _kwmeng;
		if (_pkstk != null && !_pkstk.trim().isEmpty())
			result += ", _pkstk: " + _pkstk;
		if (_auart != null && !_auart.trim().isEmpty())
			result += ", _auart: " + _auart;
		return result;
	}
}