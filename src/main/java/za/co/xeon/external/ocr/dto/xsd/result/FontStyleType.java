//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2016.02.29 at 06:44:09 PM SAST
//


package za.co.xeon.external.ocr.dto.xsd.result;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FontStyleType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FontStyleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="baseFont" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="italic" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="bold" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="underline" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="strikeout" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="smallcaps" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="scaling" type="{http://www.w3.org/2001/XMLSchema}integer" default="1000" />
 *       &lt;attribute name="spacing" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="color" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="backgroundColor" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="ff" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="fs" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FontStyleType")
public class FontStyleType {

    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "baseFont")
    protected Boolean baseFont;
    @XmlAttribute(name = "italic")
    protected Boolean italic;
    @XmlAttribute(name = "bold")
    protected Boolean bold;
    @XmlAttribute(name = "underline")
    protected Boolean underline;
    @XmlAttribute(name = "strikeout")
    protected Boolean strikeout;
    @XmlAttribute(name = "smallcaps")
    protected Boolean smallcaps;
    @XmlAttribute(name = "scaling")
    protected BigInteger scaling;
    @XmlAttribute(name = "spacing")
    protected BigInteger spacing;
    @XmlAttribute(name = "color")
    protected BigInteger color;
    @XmlAttribute(name = "backgroundColor")
    protected BigInteger backgroundColor;
    @XmlAttribute(name = "ff", required = true)
    protected String ff;
    @XmlAttribute(name = "fs", required = true)
    protected float fs;

    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the baseFont property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public boolean isBaseFont() {
        if (baseFont == null) {
            return false;
        } else {
            return baseFont;
        }
    }

    /**
     * Sets the value of the baseFont property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setBaseFont(Boolean value) {
        this.baseFont = value;
    }

    /**
     * Gets the value of the italic property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public boolean isItalic() {
        if (italic == null) {
            return false;
        } else {
            return italic;
        }
    }

    /**
     * Sets the value of the italic property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setItalic(Boolean value) {
        this.italic = value;
    }

    /**
     * Gets the value of the bold property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public boolean isBold() {
        if (bold == null) {
            return false;
        } else {
            return bold;
        }
    }

    /**
     * Sets the value of the bold property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setBold(Boolean value) {
        this.bold = value;
    }

    /**
     * Gets the value of the underline property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public boolean isUnderline() {
        if (underline == null) {
            return false;
        } else {
            return underline;
        }
    }

    /**
     * Sets the value of the underline property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setUnderline(Boolean value) {
        this.underline = value;
    }

    /**
     * Gets the value of the strikeout property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public boolean isStrikeout() {
        if (strikeout == null) {
            return false;
        } else {
            return strikeout;
        }
    }

    /**
     * Sets the value of the strikeout property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setStrikeout(Boolean value) {
        this.strikeout = value;
    }

    /**
     * Gets the value of the smallcaps property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public boolean isSmallcaps() {
        if (smallcaps == null) {
            return false;
        } else {
            return smallcaps;
        }
    }

    /**
     * Sets the value of the smallcaps property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setSmallcaps(Boolean value) {
        this.smallcaps = value;
    }

    /**
     * Gets the value of the scaling property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getScaling() {
        if (scaling == null) {
            return new BigInteger("1000");
        } else {
            return scaling;
        }
    }

    /**
     * Sets the value of the scaling property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setScaling(BigInteger value) {
        this.scaling = value;
    }

    /**
     * Gets the value of the spacing property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getSpacing() {
        if (spacing == null) {
            return new BigInteger("0");
        } else {
            return spacing;
        }
    }

    /**
     * Sets the value of the spacing property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setSpacing(BigInteger value) {
        this.spacing = value;
    }

    /**
     * Gets the value of the color property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getColor() {
        if (color == null) {
            return new BigInteger("0");
        } else {
            return color;
        }
    }

    /**
     * Sets the value of the color property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setColor(BigInteger value) {
        this.color = value;
    }

    /**
     * Gets the value of the backgroundColor property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getBackgroundColor() {
        if (backgroundColor == null) {
            return new BigInteger("0");
        } else {
            return backgroundColor;
        }
    }

    /**
     * Sets the value of the backgroundColor property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setBackgroundColor(BigInteger value) {
        this.backgroundColor = value;
    }

    /**
     * Gets the value of the ff property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFf() {
        return ff;
    }

    /**
     * Sets the value of the ff property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFf(String value) {
        this.ff = value;
    }

    /**
     * Gets the value of the fs property.
     *
     */
    public float getFs() {
        return fs;
    }

    /**
     * Sets the value of the fs property.
     *
     */
    public void setFs(float value) {
        this.fs = value;
    }

}
