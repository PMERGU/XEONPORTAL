package za.co.xeon.web.rest.dto;

import za.co.xeon.external.sap.hibersap.forge.dto.EtCustOrders;

public class PODReportResDTO extends EtCustOrders {
	private String podStatus;

	public String getPodStatus() {
		return podStatus;
	}

	public void setPodStatus(String podStatus) {
		this.podStatus = podStatus;
	}

}
