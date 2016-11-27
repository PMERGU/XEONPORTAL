package za.co.xeon.external.sap.hibersap.forge.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;
import java.lang.Override;

@BapiStructure
public class SLgber {

	@Parameter("HIGH")
	String _high;
	@Parameter("SIGN")
	String _sign;
	@Parameter("LOW")
	String _low;
	@Parameter("OPTION")
	String _option;

	public String get_high() {
		return this._high;
	}

	public void set_high(final String _high) {
		this._high = _high;
	}

	public String get_sign() {
		return this._sign;
	}

	public void set_sign(final String _sign) {
		this._sign = _sign;
	}

	public String get_low() {
		return this._low;
	}

	public void set_low(final String _low) {
		this._low = _low;
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
		if (_high != null && !_high.trim().isEmpty())
			result += "_high: " + _high;
		if (_sign != null && !_sign.trim().isEmpty())
			result += ", _sign: " + _sign;
		if (_low != null && !_low.trim().isEmpty())
			result += ", _low: " + _low;
		if (_option != null && !_option.trim().isEmpty())
			result += ", _option: " + _option;
		return result;
	}
}