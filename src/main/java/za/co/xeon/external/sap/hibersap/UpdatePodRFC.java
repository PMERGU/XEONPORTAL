package za.co.xeon.external.sap.hibersap;

import java.util.List;

import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.Table;
import org.hibersap.bapi.BapiRet2;

@Bapi("Z_SET_URL_ON_POD")
public class UpdatePodRFC {
	/**
	 * "Sales and Distribution Document Number"
	 */
	@Import
	@Parameter("IM_DEL_VBELN")
	private final String imDelVbeln;

	/**
	 * "Character field of length 40"
	 */
	@Import
	@Parameter("IM_POD_URL")
	private final String imPodUrl;

	/**
	 * "Return parameter table"@return "Return" - "Return parameter table"
	 */
	@Table
	@Parameter("RETURN")
	private List<BapiRet2> returnType;

	/**
	 * @param "imDelVbeln"
	 *            - "Sales and Distribution Document Number"@param "imPodUrl" -
	 *            "Character field of length 40"
	 */
	public UpdatePodRFC(String imDelVbeln, String imPodUrl) {
		this.imDelVbeln = imDelVbeln;
		this.imPodUrl = imPodUrl;
	}

	public List<BapiRet2> getReturn() {
		return returnType;
	}
}
