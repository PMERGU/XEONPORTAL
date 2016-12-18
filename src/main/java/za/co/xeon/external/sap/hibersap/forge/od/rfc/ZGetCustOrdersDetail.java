package za.co.xeon.external.sap.hibersap.forge.od.rfc;

import org.hibersap.annotations.Bapi;
import java.util.List;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.Export;
import org.hibersap.annotations.ParameterType;

import za.co.xeon.external.sap.hibersap.forge.od.dto.GtCustOrdersDetail;
import za.co.xeon.external.sap.hibersap.forge.od.dto.GtReturn;
import za.co.xeon.external.sap.hibersap.forge.od.dto.ImDateR;

import java.lang.Override;

@Bapi("Z_GET_CUST_ORDERS_DETAIL_1")
public class ZGetCustOrdersDetail {

	@Import
	@Parameter(value = "IM_DATE_R", type = ParameterType.TABLE_STRUCTURE)
	private final List<ImDateR> _imDateR;
	@Import
	@Parameter("IM_VBELN")
	private final String _imVbeln;
	@Export
	@Parameter(value = "GT_RETURN", type = ParameterType.STRUCTURE)
	private GtReturn _gtReturn;
	@Export
	@Parameter(value = "GT_CUST_ORDERS_DETAIL", type = ParameterType.TABLE_STRUCTURE)
	private List<GtCustOrdersDetail> _gtCustOrdersDetail;

	public ZGetCustOrdersDetail(final List<ImDateR> imDateR, final String imVbeln) {
		this._imDateR = imDateR;
		this._imVbeln = imVbeln;
	}

	public List<ImDateR> get_imDateR() {
		return this._imDateR;
	}

	public String get_imVbeln() {
		return this._imVbeln;
	}

	public GtReturn get_gtReturn() {
		return this._gtReturn;
	}

	public void set_gtReturn(final GtReturn _gtReturn) {
		this._gtReturn = _gtReturn;
	}

	public List<GtCustOrdersDetail> get_gtCustOrdersDetail() {
		return this._gtCustOrdersDetail;
	}

	public void set_gtCustOrdersDetail(final List<GtCustOrdersDetail> _gtCustOrdersDetail) {
		this._gtCustOrdersDetail = _gtCustOrdersDetail;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (_imDateR != null)
			result += "_imDateR: " + _imDateR;
		if (_imVbeln != null && !_imVbeln.trim().isEmpty())
			result += ", _imVbeln: " + _imVbeln;
		if (_gtReturn != null)
			result += ", _gtReturn: " + _gtReturn;
		if (_gtCustOrdersDetail != null)
			result += ", _gtCustOrdersDetail: " + _gtCustOrdersDetail;
		return result;
	}
}