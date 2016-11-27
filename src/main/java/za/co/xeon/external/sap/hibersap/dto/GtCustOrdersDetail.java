package za.co.xeon.external.sap.hibersap.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

/**
 * Created by Derick on 7/25/2016.
 */
@BapiStructure
public class GtCustOrdersDetail {
	/**
	 * "Customer Number 1"
	 */
	@Parameter("SKUNNR")
	private String skunnr;

	/**
	 * "Customer purchase order number"
	 */
	@Parameter("BSTKD")
	private String bstkd;

	/**
	 * "Sales and Distribution Document Number"
	 */
	@Parameter("VBELN")
	private String vbeln;

	/**
	 * "Document date (date received/sent)"
	 */
	@Parameter("AUDAT")
	private java.util.Date audat;

	/**
	 * "Entry time"
	 */
	@Parameter("ERZET")
	private java.util.Date erzet;

	/**
	 * "Cumulative order quantity in sales units"
	 */
	@Parameter("KWMENG")
	private java.math.BigDecimal kwmeng;

	/**
	 * "Shipment Number"
	 */
	@Parameter("TKNUM")
	private String tknum;

	/**
	 * "Current date for start of shipment"
	 */
	@Parameter("DATBG")
	private java.util.Date datbg;

	/**
	 * "Actual transport start time"
	 */
	@Parameter("UATBG")
	private java.util.Date uatbg;

	/**
	 * "Actual Date for End of Shipment"
	 */
	@Parameter("DATEN")
	private java.util.Date daten;

	/**
	 * "Actual shipment end time"
	 */
	@Parameter("UATEN")
	private java.util.Date uaten;

	/**
	 * "Sales and Distribution Document Number"
	 */
	@Parameter("DBELN")
	private String dbeln;

	/**
	 * "Status of pick confirmation"
	 */
	@Parameter("KOQUK")
	private String koquk;

	/**
	 * "Overall status of warehouse management activities"
	 */
	@Parameter("LVSTK")
	private String lvstk;

	/**
	 * "Total goods movement status"
	 */
	@Parameter("WBSTK")
	private String wbstk;

	/**
	 * "Transportation planning status"
	 */
	@Parameter("TRSTA")
	private String trsta;

	/**
	 * "POD status on header level"
	 */
	@Parameter("PDSTK")
	private String pdstk;

	/**
	 * "Billing status"
	 */
	@Parameter("FKSTK")
	private String fkstk;

	/**
	 * "Overall picking / putaway status"
	 */
	@Parameter("KOSTK")
	private String kostk;

	/**
	 * "Overall packing status of all items"
	 */
	@Parameter("PKSTK")
	private String pkstk;

	/**
	 * "Distribution Status (Decentralized Warehouse Processing)"
	 */
	@Parameter("VLSTK")
	private String vlstk;

	/**
	 * "Billing totals status for intercompany billing"
	 */
	@Parameter("FKIVK")
	private String fkivk;

	/**
	 * "Overall status of credit checks"
	 */
	@Parameter("CMGST")
	private String cmgst;

	/**
	 * "Actual quantity delivered (in sales units)"
	 */
	@Parameter("LFIMG")
	private java.math.BigDecimal lfimg;

	/**
	 * "Sales and Distribution Document Number"
	 */
	@Parameter("BELNR")
	private String belnr;

	/**
	 * "Amount in document currency"
	 */
	@Parameter("WRBTR")
	private java.math.BigDecimal wrbtr;

	public GtCustOrdersDetail(String skunnr, String bstkd, String vbeln, java.util.Date audat, java.util.Date erzet, java.math.BigDecimal kwmeng, String tknum, java.util.Date datbg, java.util.Date uatbg, java.util.Date daten, java.util.Date uaten, String dbeln, String koquk, String lvstk, String wbstk, String trsta, String pdstk, String fkstk, String kostk, String pkstk, String vlstk, String fkivk, String cmgst, java.math.BigDecimal lfimg, String belnr, java.math.BigDecimal wrbtr) {
		this.skunnr = skunnr;
		this.bstkd = bstkd;
		this.vbeln = vbeln;
		this.audat = audat;
		this.erzet = erzet;
		this.kwmeng = kwmeng;
		this.tknum = tknum;
		this.datbg = datbg;
		this.uatbg = uatbg;
		this.daten = daten;
		this.uaten = uaten;
		this.dbeln = dbeln;
		this.koquk = koquk;
		this.lvstk = lvstk;
		this.wbstk = wbstk;
		this.trsta = trsta;
		this.pdstk = pdstk;
		this.fkstk = fkstk;
		this.kostk = kostk;
		this.pkstk = pkstk;
		this.vlstk = vlstk;
		this.fkivk = fkivk;
		this.cmgst = cmgst;
		this.lfimg = lfimg;
		this.belnr = belnr;
		this.wrbtr = wrbtr;
	}

	public GtCustOrdersDetail() {
	}

	/**
	 * @return "Skunnr" - "Customer Number 1"
	 */
	public String getSkunnr() {
		return skunnr;
	}

	/**
	 * @return "Bstkd" - "Customer purchase order number"
	 */
	public String getBstkd() {
		return bstkd;
	}

	/**
	 * @return "Vbeln" - "Sales and Distribution Document Number"
	 */
	public String getVbeln() {
		return vbeln;
	}

	/**
	 * @return "Audat" - "Document date (date received/sent)"
	 */
	public java.util.Date getAudat() {
		return audat;
	}

	/**
	 * @return "Erzet" - "Entry time"
	 */
	public java.util.Date getErzet() {
		return erzet;
	}

	/**
	 * @return "Kwmeng" - "Cumulative order quantity in sales units"
	 */
	public java.math.BigDecimal getKwmeng() {
		return kwmeng;
	}

	/**
	 * @return "Tknum" - "Shipment Number"
	 */
	public String getTknum() {
		return tknum;
	}

	/**
	 * @return "Datbg" - "Current date for start of shipment"
	 */
	public java.util.Date getDatbg() {
		return datbg;
	}

	/**
	 * @return "Uatbg" - "Actual transport start time"
	 */
	public java.util.Date getUatbg() {
		return uatbg;
	}

	/**
	 * @return "Daten" - "Actual Date for End of Shipment"
	 */
	public java.util.Date getDaten() {
		return daten;
	}

	/**
	 * @return "Uaten" - "Actual shipment end time"
	 */
	public java.util.Date getUaten() {
		return uaten;
	}

	/**
	 * @return "Dbeln" - "Sales and Distribution Document Number"
	 */
	public String getDbeln() {
		return dbeln;
	}

	/**
	 * @return "Koquk" - "Status of pick confirmation"
	 */
	public String getKoquk() {
		return koquk;
	}

	/**
	 * @return "Lvstk" - "Overall status of warehouse management activities"
	 */
	public String getLvstk() {
		return lvstk;
	}

	/**
	 * @return "Wbstk" - "Total goods movement status"
	 */
	public String getWbstk() {
		return wbstk;
	}

	/**
	 * @return "Trsta" - "Transportation planning status"
	 */
	public String getTrsta() {
		return trsta;
	}

	/**
	 * @return "Pdstk" - "POD status on header level"
	 */
	public String getPdstk() {
		return pdstk;
	}

	/**
	 * @return "Fkstk" - "Billing status"
	 */
	public String getFkstk() {
		return fkstk;
	}

	/**
	 * @return "Kostk" - "Overall picking / putaway status"
	 */
	public String getKostk() {
		return kostk;
	}

	/**
	 * @return "Pkstk" - "Overall packing status of all items"
	 */
	public String getPkstk() {
		return pkstk;
	}

	/**
	 * @return "Vlstk" -
	 *         "Distribution Status (Decentralized Warehouse Processing)"
	 */
	public String getVlstk() {
		return vlstk;
	}

	/**
	 * @return "Fkivk" - "Billing totals status for intercompany billing"
	 */
	public String getFkivk() {
		return fkivk;
	}

	/**
	 * @return "Cmgst" - "Overall status of credit checks"
	 */
	public String getCmgst() {
		return cmgst;
	}

	/**
	 * @return "Lfimg" - "Actual quantity delivered (in sales units)"
	 */
	public java.math.BigDecimal getLfimg() {
		return lfimg;
	}

	/**
	 * @return "Belnr" - "Sales and Distribution Document Number"
	 */
	public String getBelnr() {
		return belnr;
	}

	/**
	 * @return "Wrbtr" - "Amount in document currency"
	 */
	public java.math.BigDecimal getWrbtr() {
		return wrbtr;
	}
}
