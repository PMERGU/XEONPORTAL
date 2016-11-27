package za.co.xeon.external.sap.hibersap;

import java.util.List;

import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.Table;
import org.hibersap.bapi.BapiRet2;

import za.co.xeon.external.sap.hibersap.dto.Huheader;
import za.co.xeon.external.sap.hibersap.dto.Huitem;
import za.co.xeon.external.sap.hibersap.dto.Hunumbers;

@Bapi("Z_GET_HANDLING_UNITS")
public class HandlingUnitsRFC {
	/**
	 * "Sales and Distribution Document Number"
	 */
	@Import
	@Parameter("IM_DEL_VBELN")
	private final String imDelVbeln;

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

	/**
	 * "Return Parameter"@return "Return" - "Return Parameter"
	 */
	@Table
	@Parameter("RETURN")
	private List<BapiRet2> returnType;

	/**
	 * @param "imDelVbeln"
	 *            - "Sales and Distribution Document Number"
	 */
	public HandlingUnitsRFC(String imDelVbeln) {
		this.imDelVbeln = imDelVbeln;
	}

	public HandlingUnitsRFC() {
		this.imDelVbeln = String.format("%010d", 80108175);
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

	public List<BapiRet2> getReturn() {
		return returnType;
	}

}
