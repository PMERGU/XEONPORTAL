package za.co.xeon.web.rest.dto;

import java.util.Date;

public class PODReportReqDTO {
	private Date fromDate;
	private Date toDate;
	private String podType;
	private String orderType;
	private String sapId;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getPodType() {
		return podType;
	}

	public void setPodType(String podType) {
		this.podType = podType;
	}

	public String getSapId() {
		return sapId;
	}

	public void setSapId(String sapId) {
		this.sapId = sapId;
	}

	@Override
	public String toString() {
		return "PODReportReqDTO [fromDate=" + fromDate + ", toDate=" + toDate + ", podType=" + podType + ", sapId=" + sapId + "]";
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}
