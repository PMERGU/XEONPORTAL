package za.co.xeon.web.rest.dto;

import za.co.xeon.external.sap.hibersap.forge.so.dto.EtCustOrders;

public class PODReportResDTO extends EtCustOrders {
	private String podStatus;
	private String invStatus;

	public String getPodStatus() {
		return podStatus;
	}

	public void setPodStatus(String podStatus) {
		this.podStatus = podStatus;
	}

	public String getInvStatus() {
		return invStatus;
	}

	public void setInvStatus(String invStatus) {
		this.invStatus = invStatus;
	}

}
