package za.co.xeon.web.rest.dto;

import java.util.List;

import za.co.xeon.external.sap.hibersap.dto.ExReturn;

/**
 * Created by derick on 2016/07/29.
 */
public class SalesOrderCreatedDTO {
	private String soNumber;
	private List<ExReturn> status;

	public SalesOrderCreatedDTO(String soNumber, List<ExReturn> status) {
		this.soNumber = soNumber;
		this.status = status;
	}

	public String getSoNumber() {
		return soNumber;
	}

	public void setSoNumber(String soNumber) {
		this.soNumber = soNumber;
	}

	public List<ExReturn> getStatus() {
		return status;
	}

	public void setStatus(List<ExReturn> status) {
		this.status = status;
	}
}
