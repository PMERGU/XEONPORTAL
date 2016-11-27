package za.co.xeon.web.rest.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Derick on 3/26/2016.
 */
public class HandlingUnitUpdateDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4590141372347631664L;
	private Date date;
	private List<HandlingUnitDto> handlingUnits;

	public HandlingUnitUpdateDto() {
	}

	public HandlingUnitUpdateDto(Date date, List<HandlingUnitDto> handlingUnits) {
		this.date = date;
		this.handlingUnits = handlingUnits;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<HandlingUnitDto> getHandlingUnits() {
		return handlingUnits;
	}

	public void setHandlingUnits(List<HandlingUnitDto> handlingUnits) {
		this.handlingUnits = handlingUnits;
	}
}
