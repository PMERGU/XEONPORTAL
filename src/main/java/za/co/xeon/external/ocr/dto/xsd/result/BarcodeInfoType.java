//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2016.02.29 at 06:44:09 PM SAST
//


package za.co.xeon.external.ocr.dto.xsd.result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BarcodeInfoType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BarcodeInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="type" use="required" type="{http://www.abbyy.com/FineReader_xml/FineReader10-schema-v1.xml}BarcodeTypeEnum" />
 *       &lt;attribute name="supplement" type="{http://www.abbyy.com/FineReader_xml/FineReader10-schema-v1.xml}BarcodeSupplementEnum" default="void" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BarcodeInfoType")
public class BarcodeInfoType {

    @XmlAttribute(name = "type", required = true)
    protected BarcodeTypeEnum type;
    @XmlAttribute(name = "supplement")
    protected String supplement;

    /**
     * Gets the value of the type property.
     *
     * @return
     *     possible object is
     *     {@link BarcodeTypeEnum }
     *
     */
    public BarcodeTypeEnum getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value
     *     allowed object is
     *     {@link BarcodeTypeEnum }
     *
     */
    public void setType(BarcodeTypeEnum value) {
        this.type = value;
    }

    /**
     * Gets the value of the supplement property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSupplement() {
        if (supplement == null) {
            return "void";
        } else {
            return supplement;
        }
    }

    /**
     * Sets the value of the supplement property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSupplement(String value) {
        this.supplement = value;
    }

}
