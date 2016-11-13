package za.co.xeon.web.rest.dto;

public class StockReportDTO {
	private String company;
	private String mName;
	private String plantNo;
	private String sType;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getPlantNo() {
		return plantNo;
	}

	public void setPlantNo(String plantNo) {
		this.plantNo = plantNo;
	}

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	@Override
	public String toString() {
		return "StockReportDTO [company=" + company + ", mName=" + mName + ", plantNo=" + plantNo + ", sType=" + sType
				+ "]";
	}
}
