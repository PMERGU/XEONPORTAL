package za.co.xeon.external.sap.hibersap.forge.rfc;

import java.util.List;

import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.Export;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.ParameterType;

import za.co.xeon.external.sap.hibersap.forge.dto.ior.EtCustOrders;
import za.co.xeon.external.sap.hibersap.forge.dto.ior.EtReturn;
import za.co.xeon.external.sap.hibersap.forge.dto.ior.ImDateR;

@Bapi("Z_ORDERS_REPORT")
public class ZOrdersReport {

	@Import
	@Parameter("IM_STATUS_POD")
	private final String _imStatusPod;
	@Import
	@Parameter(value = "IM_DATE_R", type = ParameterType.TABLE_STRUCTURE)
	private final List<ImDateR> _imDateR;
	@Import
	@Parameter("IM_CUSTOMER")
	private final String _imCustomer;
	@Export
	@Parameter(value = "ET_CUST_ORDERS", type = ParameterType.TABLE_STRUCTURE)
	private List<EtCustOrders> _etCustOrders;
	@Export
	@Parameter(value = "ET_RETURN", type = ParameterType.STRUCTURE)
	private EtReturn _etReturn;

	public ZOrdersReport(final String imStatusPod, final List<ImDateR> imDateR, final String imCustomer) {
		this._imStatusPod = imStatusPod;
		this._imDateR = imDateR;
		this._imCustomer = imCustomer;
	}

	public String get_imStatusPod() {
		return this._imStatusPod;
	}

	public List<ImDateR> get_imDateR() {
		return this._imDateR;
	}

	public String get_imCustomer() {
		return this._imCustomer;
	}

	public List<EtCustOrders> get_etCustOrders() {
		return this._etCustOrders;
	}

	public void set_etCustOrders(final List<EtCustOrders> _etCustOrders) {
		this._etCustOrders = _etCustOrders;
	}

	public EtReturn get_etReturn() {
		return this._etReturn;
	}

	public void set_etReturn(final EtReturn _etReturn) {
		this._etReturn = _etReturn;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (_imStatusPod != null && !_imStatusPod.trim().isEmpty())
			result += "_imStatusPod: " + _imStatusPod;
		if (_imDateR != null)
			result += ", _imDateR: " + _imDateR;
		if (_imCustomer != null && !_imCustomer.trim().isEmpty())
			result += ", _imCustomer: " + _imCustomer;
		if (_etCustOrders != null)
			result += ", _etCustOrders: " + _etCustOrders;
		if (_etReturn != null)
			result += ", _etReturn: " + _etReturn;
		return result;
	}
}