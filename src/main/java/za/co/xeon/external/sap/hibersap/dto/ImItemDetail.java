package za.co.xeon.external.sap.hibersap.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

/**
 * Created by Derick on 7/26/2016.
 */
@BapiStructure
public class ImItemDetail {
	/**
	 * "Material Number"
	 */
	@Parameter("MATERIAL")
	private String material;

	/**
	 * "Target quantity in sales units"
	 */
	@Parameter("TARGET_QTY")
	private java.math.BigDecimal targetQty;

	/**
	 * "Target quantity UoM"
	 */
	@Parameter("TARGET_QU")
	private String targetQu;

	/**
	 * "Plant"
	 */
	@Parameter("PLANT")
	private String plant;

	/**
	 * "Batch Number"
	 */
	@Parameter("BATCH")
	private String batch;

	/**
	 * "Storage Location"
	 */
	@Parameter("STORE_LOC")
	private String storeLoc;

	/**
	 * "Shipping Point/Receiving Point"
	 */
	@Parameter("SHIP_POINT")
	private String shipPoint;

	/**
	 * "Field of length 16"
	 */
	@Parameter("ZLAENG")
	private java.math.BigDecimal zlaeng;

	/**
	 * "Field of length 16"
	 */
	@Parameter("ZBREIT")
	private java.math.BigDecimal zbreit;

	/**
	 * "Field of length 16"
	 */
	@Parameter("ZHOEHE")
	private java.math.BigDecimal zhoehe;

	/**
	 * "Unit of dimension for length/width/height"
	 */
	@Parameter("UNIT_L")
	private String unitL;

	/**
	 * "Unit of dimension for length/width/height"
	 */
	@Parameter("UNIT_W")
	private String unitW;

	/**
	 * "Unit of dimension for length/width/height"
	 */
	@Parameter("UNIT_H")
	private String unitH;

	/**
	 * "Field of length 16"
	 */
	@Parameter("ZBRGEW")
	private java.math.BigDecimal zbrgew;

	/**
	 * "Field of length 16"
	 */
	@Parameter("ZNTGEW")
	private java.math.BigDecimal zntgew;

	/**
	 * "Weight Unit"
	 */
	@Parameter("UNIT_GW")
	private String unitGw;

	/**
	 * "Weight Unit"
	 */
	@Parameter("UNIT_NW")
	private String unitNw;

	public ImItemDetail(String material, java.math.BigDecimal targetQty, String targetQu, String plant, String batch, String storeLoc, String shipPoint, java.math.BigDecimal zlaeng, java.math.BigDecimal zbreit, java.math.BigDecimal zhoehe, String unitL, String unitW, String unitH, java.math.BigDecimal zbrgew, java.math.BigDecimal zntgew, String unitGw, String unitNw) {
		this.material = material;
		this.targetQty = targetQty;
		this.targetQu = targetQu;
		this.plant = plant;
		this.batch = batch;
		this.storeLoc = storeLoc;
		this.shipPoint = shipPoint;
		this.zlaeng = zlaeng;
		this.zbreit = zbreit;
		this.zhoehe = zhoehe;
		this.unitL = unitL;
		this.unitW = unitW;
		this.unitH = unitH;
		this.zbrgew = zbrgew;
		this.zntgew = zntgew;
		this.unitGw = unitGw;
		this.unitNw = unitNw;
	}

	public ImItemDetail() {
	}

	/**
	 * @return "Material" - "Material Number"
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @return "TargetQty" - "Target quantity in sales units"
	 */
	public java.math.BigDecimal getTargetQty() {
		return targetQty;
	}

	/**
	 * @return "TargetQu" - "Target quantity UoM"
	 */
	public String getTargetQu() {
		return targetQu;
	}

	/**
	 * @return "Plant" - "Plant"
	 */
	public String getPlant() {
		return plant;
	}

	/**
	 * @return "Batch" - "Batch Number"
	 */
	public String getBatch() {
		return batch;
	}

	/**
	 * @return "StoreLoc" - "Storage Location"
	 */
	public String getStoreLoc() {
		return storeLoc;
	}

	/**
	 * @return "ShipPoint" - "Shipping Point/Receiving Point"
	 */
	public String getShipPoint() {
		return shipPoint;
	}

	/**
	 * @return "Zlaeng" - "Field of length 16"
	 */
	public java.math.BigDecimal getZlaeng() {
		return zlaeng;
	}

	/**
	 * @return "Zbreit" - "Field of length 16"
	 */
	public java.math.BigDecimal getZbreit() {
		return zbreit;
	}

	/**
	 * @return "Zhoehe" - "Field of length 16"
	 */
	public java.math.BigDecimal getZhoehe() {
		return zhoehe;
	}

	/**
	 * @return "UnitL" - "Unit of dimension for length/width/height"
	 */
	public String getUnitL() {
		return unitL;
	}

	/**
	 * @return "UnitW" - "Unit of dimension for length/width/height"
	 */
	public String getUnitW() {
		return unitW;
	}

	/**
	 * @return "UnitH" - "Unit of dimension for length/width/height"
	 */
	public String getUnitH() {
		return unitH;
	}

	/**
	 * @return "Zbrgew" - "Field of length 16"
	 */
	public java.math.BigDecimal getZbrgew() {
		return zbrgew;
	}

	/**
	 * @return "Zntgew" - "Field of length 16"
	 */
	public java.math.BigDecimal getZntgew() {
		return zntgew;
	}

	/**
	 * @return "UnitGw" - "Weight Unit"
	 */
	public String getUnitGw() {
		return unitGw;
	}

	/**
	 * @return "UnitNw" - "Weight Unit"
	 */
	public String getUnitNw() {
		return unitNw;
	}

	@Override
	public String toString() {
		return "ImItemDetail{" + "material='" + material + '\'' + ", targetQty=" + targetQty + ", targetQu='" + targetQu + '\'' + ", plant='" + plant + '\'' + ", batch='" + batch + '\'' + ", storeLoc='" + storeLoc + '\'' + ", shipPoint='" + shipPoint + '\'' + ", zlaeng=" + zlaeng + ", zbreit=" + zbreit + ", zhoehe=" + zhoehe + ", unitL='" + unitL + '\'' + ", unitW='" + unitW + '\'' + ", unitH='" + unitH + '\'' + ", zbrgew=" + zbrgew + ", zntgew=" + zntgew + ", unitGw='" + unitGw + '\'' + ", unitNw='" + unitNw + '\'' + '}';
	}
}
