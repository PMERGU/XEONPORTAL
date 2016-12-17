package za.co.xeon.external.sap.hibersap.forge.hu.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;
import java.lang.Override;

@BapiStructure
public class Hunumbers {

	@Parameter("HU_EXID")
	String _huExid;
	@Parameter("HU_EXIDV2")
	String _huExidv2;

	public String get_huExid() {
		return this._huExid;
	}

	public void set_huExid(final String _huExid) {
		this._huExid = _huExid;
	}

	public String get_huExidv2() {
		return this._huExidv2;
	}

	public void set_huExidv2(final String _huExidv2) {
		this._huExidv2 = _huExidv2;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (_huExid != null && !_huExid.trim().isEmpty())
			result += "_huExid: " + _huExid;
		if (_huExidv2 != null && !_huExidv2.trim().isEmpty())
			result += ", _huExidv2: " + _huExidv2;
		return result;
	}
}