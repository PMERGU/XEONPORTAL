//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2016.02.29 at 06:44:09 PM SAST
//

package za.co.xeon.external.ocr.dto.xsd.result;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for BarcodeTypeEnum.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="BarcodeTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CODE39"/>
 *     &lt;enumeration value="INTERLEAVED25"/>
 *     &lt;enumeration value="EAN13"/>
 *     &lt;enumeration value="CODE128"/>
 *     &lt;enumeration value="EAN8"/>
 *     &lt;enumeration value="PDF417"/>
 *     &lt;enumeration value="CODABAR"/>
 *     &lt;enumeration value="UPCE"/>
 *     &lt;enumeration value="INDUSTRIAL25"/>
 *     &lt;enumeration value="IATA25"/>
 *     &lt;enumeration value="MATRIX25"/>
 *     &lt;enumeration value="CODE93"/>
 *     &lt;enumeration value="POSTNET"/>
 *     &lt;enumeration value="UCC128"/>
 *     &lt;enumeration value="PATCH"/>
 *     &lt;enumeration value="AZTEC"/>
 *     &lt;enumeration value="DATAMATRIX"/>
 *     &lt;enumeration value="QRCODE"/>
 *     &lt;enumeration value="UPCA"/>
 *     &lt;enumeration value="MAXICODE"/>
 *     &lt;enumeration value="CODE32"/>
 *     &lt;enumeration value="FULLASCII"/>
 *     &lt;enumeration value="ROYAL"/>
 *     &lt;enumeration value="KIX"/>
 *     &lt;enumeration value="INTELLIGENT"/>
 *     &lt;enumeration value="AUSTRALIA_POST"/>
 *     &lt;enumeration value="Unknown"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "BarcodeTypeEnum")
@XmlEnum
public enum BarcodeTypeEnum {

	@XmlEnumValue("CODE39") CODE_39("CODE39"), @XmlEnumValue("INTERLEAVED25") INTERLEAVED_25("INTERLEAVED25"), @XmlEnumValue("EAN13") EAN_13("EAN13"), @XmlEnumValue("CODE128") CODE_128("CODE128"), @XmlEnumValue("EAN8") EAN_8("EAN8"), @XmlEnumValue("PDF417") PDF_417("PDF417"), CODABAR("CODABAR"), UPCE("UPCE"), @XmlEnumValue("INDUSTRIAL25") INDUSTRIAL_25("INDUSTRIAL25"), @XmlEnumValue("IATA25") IATA_25("IATA25"), @XmlEnumValue("MATRIX25") MATRIX_25("MATRIX25"), @XmlEnumValue("CODE93") CODE_93("CODE93"), POSTNET("POSTNET"), @XmlEnumValue("UCC128") UCC_128("UCC128"), PATCH("PATCH"), AZTEC("AZTEC"), DATAMATRIX("DATAMATRIX"), QRCODE("QRCODE"), UPCA("UPCA"), MAXICODE("MAXICODE"), @XmlEnumValue("CODE32") CODE_32("CODE32"), FULLASCII("FULLASCII"), ROYAL("ROYAL"), KIX("KIX"), INTELLIGENT("INTELLIGENT"), AUSTRALIA_POST("AUSTRALIA_POST"), @XmlEnumValue("Unknown") UNKNOWN("Unknown");
	private final String value;

	BarcodeTypeEnum(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static BarcodeTypeEnum fromValue(String v) {
		for (BarcodeTypeEnum c : BarcodeTypeEnum.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
