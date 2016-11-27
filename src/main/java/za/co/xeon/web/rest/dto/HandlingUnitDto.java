package za.co.xeon.web.rest.dto;

import java.io.Serializable;

/**
 * Created by Derick on 3/26/2016.
 */
public class HandlingUnitDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7015688403305160853L;
	private String handlingUnit;

	public HandlingUnitDto() {
	}

	public HandlingUnitDto(String handlingUnit) {
		this.handlingUnit = handlingUnit;
	}

	public String getHandlingUnit() {
		return handlingUnit;
	}

	public void setHandlingUnit(String handlingUnit) {
		this.handlingUnit = handlingUnit;
	}
}
