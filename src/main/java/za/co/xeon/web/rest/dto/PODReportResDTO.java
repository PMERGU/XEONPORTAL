package za.co.xeon.web.rest.dto;

import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.external.sap.hibersap.forge.dto.EvResult;

public class PODReportResDTO extends EvResult {
	private PurchaseOrder po;

	public PurchaseOrder getPo() {
		return po;
	}

	public void setPo(PurchaseOrder po) {
		this.po = po;
	}

	@Override
	public String toString() {
		return "PODReportResDTO [po=" + po + "]";
	}

}
