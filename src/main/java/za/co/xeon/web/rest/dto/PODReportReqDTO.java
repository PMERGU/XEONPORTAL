package za.co.xeon.web.rest.dto;

import java.util.Date;

public class PODReportReqDTO {
	private Date fromDate;
	private Date toDate;
	private char podType;
	private Long id;

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

	public char getPodType() {
		return podType;
	}

	public void setPodType(char podType) {
		this.podType = podType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
