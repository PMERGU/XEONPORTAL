package za.co.xeon.external.sap.hibersap.forge.dto;

import org.hibersap.annotations.BapiStructure;
import java.util.Date;
import org.hibersap.annotations.Parameter;
import java.math.BigDecimal;
import java.lang.Override;

@BapiStructure
public class EvResult {

	@Parameter("DATEN")
	Date _daten;
	@Parameter("VENUM")
	String _venum;
	@Parameter("DATBG")
	Date _datbg;
	@Parameter("BSTKD")
	String _bstkd;
	@Parameter("IHREZ")
	String _ihrez;
	@Parameter("EXIDV")
	String _exidv;
	@Parameter("DBELN")
	String _dbeln;
	@Parameter("PNAME1")
	String _pname1;
	@Parameter("LFIMG")
	BigDecimal _lfimg;
	@Parameter("BELNR")
	String _belnr;
	@Parameter("VEGR2_TEXT")
	String _vegr2Text;
	@Parameter("PDSTK")
	String _pdstk;
	@Parameter("ERNAM")
	String _ernam;
	@Parameter("VEGR1_TEXT")
	String _vegr1Text;
	@Parameter("BNAME1")
	String _bname1;
	@Parameter("LVSTK")
	String _lvstk;
	@Parameter("EXIDV2")
	String _exidv2;
	@Parameter("BEZEI")
	String _bezei;
	@Parameter("WRBTR")
	BigDecimal _wrbtr;
	@Parameter("TRSTA")
	String _trsta;
	@Parameter("VEGR2")
	String _vegr2;
	@Parameter("SKUNNR")
	String _skunnr;
	@Parameter("AUDAT")
	Date _audat;
	@Parameter("VEGR3_TEXT")
	String _vegr3Text;
	@Parameter("KOQUK")
	String _koquk;
	@Parameter("TKNUM")
	String _tknum;
	@Parameter("WBSTK")
	String _wbstk;
	@Parameter("PKUNNR")
	String _pkunnr;
	@Parameter("FKSTK")
	String _fkstk;
	@Parameter("ERZET")
	Date _erzet;
	@Parameter("VBELN")
	String _vbeln;
	@Parameter("BKUNNR")
	String _bkunnr;
	@Parameter("SNAME1")
	String _sname1;
	@Parameter("KONDA")
	String _konda;
	@Parameter("PARVW")
	String _parvw;
	@Parameter("UATBG")
	Date _uatbg;
	@Parameter("UATEN")
	Date _uaten;
	@Parameter("KWMENG")
	BigDecimal _kwmeng;
	@Parameter("VEGR1")
	String _vegr1;
	@Parameter("VEGR3")
	String _vegr3;

	public Date get_daten() {
		return this._daten;
	}

	public void set_daten(final Date _daten) {
		this._daten = _daten;
	}

	public String get_venum() {
		return this._venum;
	}

	public void set_venum(final String _venum) {
		this._venum = _venum;
	}

	public Date get_datbg() {
		return this._datbg;
	}

	public void set_datbg(final Date _datbg) {
		this._datbg = _datbg;
	}

	public String get_bstkd() {
		return this._bstkd;
	}

	public void set_bstkd(final String _bstkd) {
		this._bstkd = _bstkd;
	}

	public String get_ihrez() {
		return this._ihrez;
	}

	public void set_ihrez(final String _ihrez) {
		this._ihrez = _ihrez;
	}

	public String get_exidv() {
		return this._exidv;
	}

	public void set_exidv(final String _exidv) {
		this._exidv = _exidv;
	}

	public String get_dbeln() {
		return this._dbeln;
	}

	public void set_dbeln(final String _dbeln) {
		this._dbeln = _dbeln;
	}

	public String get_pname1() {
		return this._pname1;
	}

	public void set_pname1(final String _pname1) {
		this._pname1 = _pname1;
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

	public String get_vegr2Text() {
		return this._vegr2Text;
	}

	public void set_vegr2Text(final String _vegr2Text) {
		this._vegr2Text = _vegr2Text;
	}

	public String get_pdstk() {
		return this._pdstk;
	}

	public void set_pdstk(final String _pdstk) {
		this._pdstk = _pdstk;
	}

	public String get_ernam() {
		return this._ernam;
	}

	public void set_ernam(final String _ernam) {
		this._ernam = _ernam;
	}

	public String get_vegr1Text() {
		return this._vegr1Text;
	}

	public void set_vegr1Text(final String _vegr1Text) {
		this._vegr1Text = _vegr1Text;
	}

	public String get_bname1() {
		return this._bname1;
	}

	public void set_bname1(final String _bname1) {
		this._bname1 = _bname1;
	}

	public String get_lvstk() {
		return this._lvstk;
	}

	public void set_lvstk(final String _lvstk) {
		this._lvstk = _lvstk;
	}

	public String get_exidv2() {
		return this._exidv2;
	}

	public void set_exidv2(final String _exidv2) {
		this._exidv2 = _exidv2;
	}

	public String get_bezei() {
		return this._bezei;
	}

	public void set_bezei(final String _bezei) {
		this._bezei = _bezei;
	}

	public BigDecimal get_wrbtr() {
		return this._wrbtr;
	}

	public void set_wrbtr(final BigDecimal _wrbtr) {
		this._wrbtr = _wrbtr;
	}

	public String get_trsta() {
		return this._trsta;
	}

	public void set_trsta(final String _trsta) {
		this._trsta = _trsta;
	}

	public String get_vegr2() {
		return this._vegr2;
	}

	public void set_vegr2(final String _vegr2) {
		this._vegr2 = _vegr2;
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

	public String get_vegr3Text() {
		return this._vegr3Text;
	}

	public void set_vegr3Text(final String _vegr3Text) {
		this._vegr3Text = _vegr3Text;
	}

	public String get_koquk() {
		return this._koquk;
	}

	public void set_koquk(final String _koquk) {
		this._koquk = _koquk;
	}

	public String get_tknum() {
		return this._tknum;
	}

	public void set_tknum(final String _tknum) {
		this._tknum = _tknum;
	}

	public String get_wbstk() {
		return this._wbstk;
	}

	public void set_wbstk(final String _wbstk) {
		this._wbstk = _wbstk;
	}

	public String get_pkunnr() {
		return this._pkunnr;
	}

	public void set_pkunnr(final String _pkunnr) {
		this._pkunnr = _pkunnr;
	}

	public String get_fkstk() {
		return this._fkstk;
	}

	public void set_fkstk(final String _fkstk) {
		this._fkstk = _fkstk;
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

	public String get_bkunnr() {
		return this._bkunnr;
	}

	public void set_bkunnr(final String _bkunnr) {
		this._bkunnr = _bkunnr;
	}

	public String get_sname1() {
		return this._sname1;
	}

	public void set_sname1(final String _sname1) {
		this._sname1 = _sname1;
	}

	public String get_konda() {
		return this._konda;
	}

	public void set_konda(final String _konda) {
		this._konda = _konda;
	}

	public String get_parvw() {
		return this._parvw;
	}

	public void set_parvw(final String _parvw) {
		this._parvw = _parvw;
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

	public BigDecimal get_kwmeng() {
		return this._kwmeng;
	}

	public void set_kwmeng(final BigDecimal _kwmeng) {
		this._kwmeng = _kwmeng;
	}

	public String get_vegr1() {
		return this._vegr1;
	}

	public void set_vegr1(final String _vegr1) {
		this._vegr1 = _vegr1;
	}

	public String get_vegr3() {
		return this._vegr3;
	}

	public void set_vegr3(final String _vegr3) {
		this._vegr3 = _vegr3;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (_daten != null)
			result += "_daten: " + _daten;
		if (_venum != null && !_venum.trim().isEmpty())
			result += ", _venum: " + _venum;
		if (_datbg != null)
			result += ", _datbg: " + _datbg;
		if (_bstkd != null && !_bstkd.trim().isEmpty())
			result += ", _bstkd: " + _bstkd;
		if (_ihrez != null && !_ihrez.trim().isEmpty())
			result += ", _ihrez: " + _ihrez;
		if (_exidv != null && !_exidv.trim().isEmpty())
			result += ", _exidv: " + _exidv;
		if (_dbeln != null && !_dbeln.trim().isEmpty())
			result += ", _dbeln: " + _dbeln;
		if (_pname1 != null && !_pname1.trim().isEmpty())
			result += ", _pname1: " + _pname1;
		if (_lfimg != null)
			result += ", _lfimg: " + _lfimg;
		if (_belnr != null && !_belnr.trim().isEmpty())
			result += ", _belnr: " + _belnr;
		if (_vegr2Text != null && !_vegr2Text.trim().isEmpty())
			result += ", _vegr2Text: " + _vegr2Text;
		if (_pdstk != null && !_pdstk.trim().isEmpty())
			result += ", _pdstk: " + _pdstk;
		if (_ernam != null && !_ernam.trim().isEmpty())
			result += ", _ernam: " + _ernam;
		if (_vegr1Text != null && !_vegr1Text.trim().isEmpty())
			result += ", _vegr1Text: " + _vegr1Text;
		if (_bname1 != null && !_bname1.trim().isEmpty())
			result += ", _bname1: " + _bname1;
		if (_lvstk != null && !_lvstk.trim().isEmpty())
			result += ", _lvstk: " + _lvstk;
		if (_exidv2 != null && !_exidv2.trim().isEmpty())
			result += ", _exidv2: " + _exidv2;
		if (_bezei != null && !_bezei.trim().isEmpty())
			result += ", _bezei: " + _bezei;
		if (_wrbtr != null)
			result += ", _wrbtr: " + _wrbtr;
		if (_trsta != null && !_trsta.trim().isEmpty())
			result += ", _trsta: " + _trsta;
		if (_vegr2 != null && !_vegr2.trim().isEmpty())
			result += ", _vegr2: " + _vegr2;
		if (_skunnr != null && !_skunnr.trim().isEmpty())
			result += ", _skunnr: " + _skunnr;
		if (_audat != null)
			result += ", _audat: " + _audat;
		if (_vegr3Text != null && !_vegr3Text.trim().isEmpty())
			result += ", _vegr3Text: " + _vegr3Text;
		if (_koquk != null && !_koquk.trim().isEmpty())
			result += ", _koquk: " + _koquk;
		if (_tknum != null && !_tknum.trim().isEmpty())
			result += ", _tknum: " + _tknum;
		if (_wbstk != null && !_wbstk.trim().isEmpty())
			result += ", _wbstk: " + _wbstk;
		if (_pkunnr != null && !_pkunnr.trim().isEmpty())
			result += ", _pkunnr: " + _pkunnr;
		if (_fkstk != null && !_fkstk.trim().isEmpty())
			result += ", _fkstk: " + _fkstk;
		if (_erzet != null)
			result += ", _erzet: " + _erzet;
		if (_vbeln != null && !_vbeln.trim().isEmpty())
			result += ", _vbeln: " + _vbeln;
		if (_bkunnr != null && !_bkunnr.trim().isEmpty())
			result += ", _bkunnr: " + _bkunnr;
		if (_sname1 != null && !_sname1.trim().isEmpty())
			result += ", _sname1: " + _sname1;
		if (_konda != null && !_konda.trim().isEmpty())
			result += ", _konda: " + _konda;
		if (_parvw != null && !_parvw.trim().isEmpty())
			result += ", _parvw: " + _parvw;
		if (_uatbg != null)
			result += ", _uatbg: " + _uatbg;
		if (_uaten != null)
			result += ", _uaten: " + _uaten;
		if (_kwmeng != null)
			result += ", _kwmeng: " + _kwmeng;
		if (_vegr1 != null && !_vegr1.trim().isEmpty())
			result += ", _vegr1: " + _vegr1;
		if (_vegr3 != null && !_vegr3.trim().isEmpty())
			result += ", _vegr3: " + _vegr3;
		return result;
	}
}