package za.co.xeon.external.sap.hibersap.forge.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;
import java.util.Date;
import java.lang.Override;

@BapiStructure
public class ImDateR {

	@Parameter("SIGN")
	String _sign;
	@Parameter("LOW")
	Date _low;
	@Parameter("HIGH")
	Date _high;

	public ImDateR(String _sign, Date _low, Date _high, String _option) {
		super();
		this._sign = _sign;
		this._low = _low;
		this._high = _high;
		this._option = _option;
	}

	@Parameter("OPTION")
	String _option;

	public String get_sign() {
		return this._sign;
	}

	public void set_sign(final String _sign) {
		this._sign = _sign;
	}

	public Date get_low() {
		return this._low;
	}

	public void set_low(final Date _low) {
		this._low = _low;
	}

	public Date get_high() {
		return this._high;
	}

	public void set_high(final Date _high) {
		this._high = _high;
	}

	public String get_option() {
		return this._option;
	}

	public void set_option(final String _option) {
		this._option = _option;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (_sign != null && !_sign.trim().isEmpty())
			result += "_sign: " + _sign;
		if (_low != null)
			result += ", _low: " + _low;
		if (_high != null)
			result += ", _high: " + _high;
		if (_option != null && !_option.trim().isEmpty())
			result += ", _option: " + _option;
		return result;
	}
}