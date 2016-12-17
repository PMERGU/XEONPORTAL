package za.co.xeon.web.rest.dto;

import java.util.List;

import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.Table;

import za.co.xeon.external.sap.hibersap.forge.hu.dto.Huheader;
import za.co.xeon.external.sap.hibersap.forge.hu.dto.Huitem;
import za.co.xeon.external.sap.hibersap.forge.hu.dto.Hunumbers;

/**
 * Created by Derick on 9/17/2016.
 */
public class HandlingUnitDetails {
	/**
	 * "Handling Unit - Header Table"
	 */
	@Parameter("HUHEADER")
	@Table
	private List<Huheader> huheader;

	/**
	 * "Structure for Handling Unit Item"
	 */
	@Parameter("HUITEM")
	@Table
	private List<Huitem> huitem;

	/**
	 * "External identification of a HU"
	 */
	@Parameter("HUNUMBERS")
	@Table
	private List<Hunumbers> hunumbers;

	public HandlingUnitDetails(List<Huheader> huheader, List<Huitem> huitem, List<Hunumbers> hunumbers) {
		this.huheader = huheader;
		this.huitem = huitem;
		this.hunumbers = hunumbers;
	}

	public HandlingUnitDetails() {
	}

	public List<Huheader> getHuheader() {
		return huheader;
	}

	public List<Huitem> getHuitem() {
		return huitem;
	}

	public List<Hunumbers> getHunumbers() {
		return hunumbers;
	}
}
