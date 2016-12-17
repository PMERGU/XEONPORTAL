package za.co.xeon.external.sap.hibersap.forge.so.rfc;

import org.hibersap.annotations.Bapi;
import java.util.List;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.Export;
import org.hibersap.annotations.ParameterType;
import java.lang.Override;

@Bapi("Z_GET_CUST_ORDERS_BY_DATE_NEW")
public class ZGetCustOrdersByDateNew {

	@Import
	@Parameter("IM_AUART")
	private final List<ImAuart> _imAuart;
	@Import
	@Parameter("IM_DATE_R")
	private final List<ImDateR> _imDateR;
	@Import
	@Parameter("IM_STATUS_POD")
	private final String _imStatusPod;
	@Import
	@Parameter("IM_CUSTOMER")
	private final String _imCustomer;
	@Export
	@Parameter(value = "ET_RETURN", type = ParameterType.STRUCTURE)
	private EtReturn _etReturn;
	@Export
	@Parameter("ET_CUST_ORDERS")
	private List<EtCustOrders> _etCustOrders;

	public ZGetCustOrdersByDateNew(final List<ImAuart> imAuart, final List<ImDateR> imDateR, final String imStatusPod, final String imCustomer) {
		this._imAuart = imAuart;
		this._imDateR = imDateR;
		this._imStatusPod = imStatusPod;
		this._imCustomer = imCustomer;
	}

	public List<ImAuart> get_imAuart() {
		return this._imAuart;
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

	public EtReturn get_etReturn() {
		return this._etReturn;
	}

	public void set_etReturn(final EtReturn _etReturn) {
		this._etReturn = _etReturn;
	}

	public List<EtCustOrders> get_etCustOrders() {
		return this._etCustOrders;
	}

	public void set_etCustOrders(final List<EtCustOrders> _etCustOrders) {
		this._etCustOrders = _etCustOrders;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (_imAuart != null)
			result += "_imAuart: " + _imAuart;
		if (_imDateR != null)
			result += ", _imDateR: " + _imDateR;
		if (_imStatusPod != null && !_imStatusPod.trim().isEmpty())
			result += ", _imStatusPod: " + _imStatusPod;
		if (_imCustomer != null && !_imCustomer.trim().isEmpty())
			result += ", _imCustomer: " + _imCustomer;
		if (_etReturn != null)
			result += ", _etReturn: " + _etReturn;
		if (_etCustOrders != null)
			result += ", _etCustOrders: " + _etCustOrders;
		return result;
	}
}