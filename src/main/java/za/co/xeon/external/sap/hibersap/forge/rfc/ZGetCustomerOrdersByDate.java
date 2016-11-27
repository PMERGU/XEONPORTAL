package za.co.xeon.external.sap.hibersap.forge.rfc;

import java.util.List;

import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.Export;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.ParameterType;

import za.co.xeon.external.sap.hibersap.forge.dto.EvResult;
import za.co.xeon.external.sap.hibersap.forge.dto.EvReturn;
import za.co.xeon.external.sap.hibersap.forge.dto.ImDateR;

@Bapi("Z_GET_CUSTOMER_ORDERS_BY_DATE")
public class ZGetCustomerOrdersByDate {

	@Import
	@Parameter(value = "IM_DATE_R", type = ParameterType.TABLE_STRUCTURE)
	private List<ImDateR> _imDateR;
	@Import
	@Parameter("IM_STATUS_POD")
	private String _imStatusPod;
	@Import
	@Parameter("IM_CUSTOMER")
	private String _imCustomer;
	@Export
	@Parameter(value = "EV_RETURN", type = ParameterType.STRUCTURE)
	private EvReturn _evReturn;
	@Export
	@Parameter("EV_RESULT")
	private List<EvResult> _evResult;

	public ZGetCustomerOrdersByDate() {
		super();
	}

	public ZGetCustomerOrdersByDate(List<ImDateR> _imDateR, String _imStatusPod, String _imCustomer) {
		super();
		this._imDateR = _imDateR;
		this._imStatusPod = _imStatusPod;
		this._imCustomer = _imCustomer;
	}

	public List<ImDateR> get_imDateR() {
		return this._imDateR;
	}

	public String get_imStatusPod() {
		return this._imStatusPod;
	}

	public String get_imCustomer() {
		return this._imCustomer;
	}

	public EvReturn get_evReturn() {
		return this._evReturn;
	}

	public void set_evReturn(final EvReturn _evReturn) {
		this._evReturn = _evReturn;
	}

	public List<EvResult> get_evResult() {
		return this._evResult;
	}

	public void set_evResult(final List<EvResult> _evResult) {
		this._evResult = _evResult;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (_imDateR != null)
			result += "_imDateR: " + _imDateR;
		if (_imStatusPod != null && !_imStatusPod.trim().isEmpty())
			result += ", _imStatusPod: " + _imStatusPod;
		if (_imCustomer != null && !_imCustomer.trim().isEmpty())
			result += ", _imCustomer: " + _imCustomer;
		if (_evReturn != null)
			result += ", _evReturn: " + _evReturn;
		if (_evResult != null)
			result += ", _evResult: " + _evResult;
		return result;
	}
}