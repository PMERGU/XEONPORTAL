package za.co.xeon.external.sap.hibersap.forge.dto.sr;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;
import java.math.BigDecimal;
import java.lang.Override;

@BapiStructure
public class StockInventory {

	@Parameter("MEINS")
	String _meins;
	@Parameter("VERME")
	BigDecimal _verme;
	@Parameter("LTYPT")
	String _ltypt;
	@Parameter("CHARG")
	String _charg;
	@Parameter("EINME")
	BigDecimal _einme;
	@Parameter("AUSME")
	BigDecimal _ausme;
	@Parameter("MATNR")
	String _matnr;
	@Parameter("MAKTX")
	String _maktx;
	@Parameter("LGNUMT")
	String _lgnumt;

	public String get_meins() {
		return this._meins;
	}

	public void set_meins(final String _meins) {
		this._meins = _meins;
	}

	public BigDecimal get_verme() {
		return this._verme;
	}

	public void set_verme(final BigDecimal _verme) {
		this._verme = _verme;
	}

	public String get_ltypt() {
		return this._ltypt;
	}

	public void set_ltypt(final String _ltypt) {
		this._ltypt = _ltypt;
	}

	public String get_charg() {
		return this._charg;
	}

	public void set_charg(final String _charg) {
		this._charg = _charg;
	}

	public BigDecimal get_einme() {
		return this._einme;
	}

	public void set_einme(final BigDecimal _einme) {
		this._einme = _einme;
	}

	public BigDecimal get_ausme() {
		return this._ausme;
	}

	public void set_ausme(final BigDecimal _ausme) {
		this._ausme = _ausme;
	}

	public String get_matnr() {
		return this._matnr;
	}

	public void set_matnr(final String _matnr) {
		this._matnr = _matnr;
	}

	public String get_maktx() {
		return this._maktx;
	}

	public void set_maktx(final String _maktx) {
		this._maktx = _maktx;
	}

	public String get_lgnumt() {
		return this._lgnumt;
	}

	public void set_lgnumt(final String _lgnumt) {
		this._lgnumt = _lgnumt;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (_meins != null && !_meins.trim().isEmpty())
			result += "_meins: " + _meins;
		if (_verme != null)
			result += ", _verme: " + _verme;
		if (_ltypt != null && !_ltypt.trim().isEmpty())
			result += ", _ltypt: " + _ltypt;
		if (_charg != null && !_charg.trim().isEmpty())
			result += ", _charg: " + _charg;
		if (_einme != null)
			result += ", _einme: " + _einme;
		if (_ausme != null)
			result += ", _ausme: " + _ausme;
		if (_matnr != null && !_matnr.trim().isEmpty())
			result += ", _matnr: " + _matnr;
		if (_maktx != null && !_maktx.trim().isEmpty())
			result += ", _maktx: " + _maktx;
		if (_lgnumt != null && !_lgnumt.trim().isEmpty())
			result += ", _lgnumt: " + _lgnumt;
		return result;
	}
}