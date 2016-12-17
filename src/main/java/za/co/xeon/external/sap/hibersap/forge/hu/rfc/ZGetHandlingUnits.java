package za.co.xeon.external.sap.hibersap.forge.hu.rfc;

import java.util.List;

import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.Table;

import za.co.xeon.external.sap.hibersap.forge.hu.dto.Huheader;
import za.co.xeon.external.sap.hibersap.forge.hu.dto.Huitem;
import za.co.xeon.external.sap.hibersap.forge.hu.dto.Hunumbers;
import za.co.xeon.external.sap.hibersap.forge.hu.dto.Return;

@Bapi("Z_GET_HANDLING_UNITS")
public class ZGetHandlingUnits {

	@Import
	@Parameter("IM_DEL_VBELN")
	private final String _imDelVbeln;
	@Table
	@Parameter(value = "RETURN")
	private List<Return> _return;
	@Table
	@Parameter(value = "HUHEADER")
	private List<Huheader> _huheader;
	@Table
	@Parameter(value = "HUNUMBERS")
	private List<Hunumbers> _hunumbers;
	@Table
	@Parameter(value = "HUITO")
	private List<Huitem> _huitem;

	public ZGetHandlingUnits(final String imDelVbeln) {
		this._imDelVbeln = imDelVbeln;
	}

	public String get_imDelVbeln() {
		return this._imDelVbeln;
	}

	public List<Return> get_return() {
		return this._return;
	}

	public void set_return(final List<Return> _return) {
		this._return = _return;
	}

	public List<Huheader> get_huheader() {
		return this._huheader;
	}

	public void set_huheader(final List<Huheader> _huheader) {
		this._huheader = _huheader;
	}

	public List<Hunumbers> get_hunumbers() {
		return this._hunumbers;
	}

	public void set_hunumbers(final List<Hunumbers> _hunumbers) {
		this._hunumbers = _hunumbers;
	}

	public List<Huitem> get_huitem() {
		return this._huitem;
	}

	public void set_huitem(final List<Huitem> _huitem) {
		this._huitem = _huitem;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (_imDelVbeln != null && !_imDelVbeln.trim().isEmpty())
			result += "_imDelVbeln: " + _imDelVbeln;
		if (_return != null)
			result += ", _return: " + _return;
		if (_huheader != null)
			result += ", _huheader: " + _huheader;
		if (_hunumbers != null)
			result += ", _hunumbers: " + _hunumbers;
		if (_huitem != null)
			result += ", _huitem: " + _huitem;
		return result;
	}
}