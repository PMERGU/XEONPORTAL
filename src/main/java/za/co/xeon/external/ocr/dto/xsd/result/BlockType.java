//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2016.02.29 at 06:44:09 PM SAST
//


package za.co.xeon.external.ocr.dto.xsd.result;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for BlockType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BlockType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="region" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="rect" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="l" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="t" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="r" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="b" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="text" type="{http://www.abbyy.com/FineReader_xml/FineReader10-schema-v1.xml}TextType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="row" type="{http://www.abbyy.com/FineReader_xml/FineReader10-schema-v1.xml}TableRowType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="separatorsBox" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="separator" type="{http://www.abbyy.com/FineReader_xml/FineReader10-schema-v1.xml}SeparatorBlockType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="separator" type="{http://www.abbyy.com/FineReader_xml/FineReader10-schema-v1.xml}SeparatorBlockType" minOccurs="0"/>
 *         &lt;element name="barcodeInfo" type="{http://www.abbyy.com/FineReader_xml/FineReader10-schema-v1.xml}BarcodeInfoType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="blockType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Text"/>
 *             &lt;enumeration value="Table"/>
 *             &lt;enumeration value="Picture"/>
 *             &lt;enumeration value="Barcode"/>
 *             &lt;enumeration value="Separator"/>
 *             &lt;enumeration value="SeparatorsBox"/>
 *             &lt;enumeration value="Checkmark"/>
 *             &lt;enumeration value="GroupCheckmark"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="pageElemId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="blockName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="isHidden" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="l" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="t" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="r" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="b" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BlockType", propOrder = {
    "region",
    "text",
    "row",
    "separatorsBox",
    "separator",
    "barcodeInfo"
})
public class BlockType {

    protected BlockType.Region region;
    protected List<TextType> text;
    protected List<TableRowType> row;
    protected BlockType.SeparatorsBox separatorsBox;
    protected SeparatorBlockType separator;
    protected BarcodeInfoType barcodeInfo;
    @XmlAttribute(name = "blockType", required = true)
    protected String blockType;
    @XmlAttribute(name = "pageElemId")
    protected String pageElemId;
    @XmlAttribute(name = "blockName")
    protected String blockName;
    @XmlAttribute(name = "isHidden")
    protected Boolean isHidden;
    @XmlAttribute(name = "l")
    protected BigInteger l;
    @XmlAttribute(name = "t")
    protected BigInteger t;
    @XmlAttribute(name = "r")
    protected BigInteger r;
    @XmlAttribute(name = "b")
    protected BigInteger b;

    /**
     * Gets the value of the region property.
     *
     * @return
     *     possible object is
     *     {@link BlockType.Region }
     *
     */
    public BlockType.Region getRegion() {
        return region;
    }

    /**
     * Sets the value of the region property.
     *
     * @param value
     *     allowed object is
     *     {@link BlockType.Region }
     *
     */
    public void setRegion(BlockType.Region value) {
        this.region = value;
    }

    /**
     * Gets the value of the text property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the text property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getText().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     *
     *
     */
    public List<TextType> getText() {
        if (text == null) {
            text = new ArrayList<TextType>();
        }
        return this.text;
    }

    /**
     * Gets the value of the row property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the row property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRow().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TableRowType }
     *
     *
     */
    public List<TableRowType> getRow() {
        if (row == null) {
            row = new ArrayList<TableRowType>();
        }
        return this.row;
    }

    /**
     * Gets the value of the separatorsBox property.
     *
     * @return
     *     possible object is
     *     {@link BlockType.SeparatorsBox }
     *
     */
    public BlockType.SeparatorsBox getSeparatorsBox() {
        return separatorsBox;
    }

    /**
     * Sets the value of the separatorsBox property.
     *
     * @param value
     *     allowed object is
     *     {@link BlockType.SeparatorsBox }
     *
     */
    public void setSeparatorsBox(BlockType.SeparatorsBox value) {
        this.separatorsBox = value;
    }

    /**
     * Gets the value of the separator property.
     *
     * @return
     *     possible object is
     *     {@link SeparatorBlockType }
     *
     */
    public SeparatorBlockType getSeparator() {
        return separator;
    }

    /**
     * Sets the value of the separator property.
     *
     * @param value
     *     allowed object is
     *     {@link SeparatorBlockType }
     *
     */
    public void setSeparator(SeparatorBlockType value) {
        this.separator = value;
    }

    /**
     * Gets the value of the barcodeInfo property.
     *
     * @return
     *     possible object is
     *     {@link BarcodeInfoType }
     *
     */
    public BarcodeInfoType getBarcodeInfo() {
        return barcodeInfo;
    }

    /**
     * Sets the value of the barcodeInfo property.
     *
     * @param value
     *     allowed object is
     *     {@link BarcodeInfoType }
     *
     */
    public void setBarcodeInfo(BarcodeInfoType value) {
        this.barcodeInfo = value;
    }

    /**
     * Gets the value of the blockType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBlockType() {
        return blockType;
    }

    /**
     * Sets the value of the blockType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBlockType(String value) {
        this.blockType = value;
    }

    /**
     * Gets the value of the pageElemId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPageElemId() {
        return pageElemId;
    }

    /**
     * Sets the value of the pageElemId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPageElemId(String value) {
        this.pageElemId = value;
    }

    /**
     * Gets the value of the blockName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBlockName() {
        return blockName;
    }

    /**
     * Sets the value of the blockName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBlockName(String value) {
        this.blockName = value;
    }

    /**
     * Gets the value of the isHidden property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public boolean isIsHidden() {
        if (isHidden == null) {
            return false;
        } else {
            return isHidden;
        }
    }

    /**
     * Sets the value of the isHidden property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIsHidden(Boolean value) {
        this.isHidden = value;
    }

    /**
     * Gets the value of the l property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getL() {
        return l;
    }

    /**
     * Sets the value of the l property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setL(BigInteger value) {
        this.l = value;
    }

    /**
     * Gets the value of the t property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getT() {
        return t;
    }

    /**
     * Sets the value of the t property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setT(BigInteger value) {
        this.t = value;
    }

    /**
     * Gets the value of the r property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getR() {
        return r;
    }

    /**
     * Sets the value of the r property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setR(BigInteger value) {
        this.r = value;
    }

    /**
     * Gets the value of the b property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getB() {
        return b;
    }

    /**
     * Sets the value of the b property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setB(BigInteger value) {
        this.b = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="rect" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="l" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="t" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="r" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="b" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "rect"
    })
    public static class Region {

        @XmlElement(required = true)
        protected List<Rect> rect;

        /**
         * Gets the value of the rect property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rect property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRect().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BlockType.Region.Rect }
         *
         *
         */
        public List<Rect> getRect() {
            if (rect == null) {
                rect = new ArrayList<Rect>();
            }
            return this.rect;
        }


        /**
         * <p>Java class for anonymous complex type.
         *
         * <p>The following schema fragment specifies the expected content contained within this class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="l" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="t" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="r" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="b" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Rect {

            @XmlAttribute(name = "l", required = true)
            protected BigInteger l;
            @XmlAttribute(name = "t", required = true)
            protected BigInteger t;
            @XmlAttribute(name = "r", required = true)
            protected BigInteger r;
            @XmlAttribute(name = "b", required = true)
            protected BigInteger b;

            /**
             * Gets the value of the l property.
             *
             * @return
             *     possible object is
             *     {@link BigInteger }
             *
             */
            public BigInteger getL() {
                return l;
            }

            /**
             * Sets the value of the l property.
             *
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *
             */
            public void setL(BigInteger value) {
                this.l = value;
            }

            /**
             * Gets the value of the t property.
             *
             * @return
             *     possible object is
             *     {@link BigInteger }
             *
             */
            public BigInteger getT() {
                return t;
            }

            /**
             * Sets the value of the t property.
             *
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *
             */
            public void setT(BigInteger value) {
                this.t = value;
            }

            /**
             * Gets the value of the r property.
             *
             * @return
             *     possible object is
             *     {@link BigInteger }
             *
             */
            public BigInteger getR() {
                return r;
            }

            /**
             * Sets the value of the r property.
             *
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *
             */
            public void setR(BigInteger value) {
                this.r = value;
            }

            /**
             * Gets the value of the b property.
             *
             * @return
             *     possible object is
             *     {@link BigInteger }
             *
             */
            public BigInteger getB() {
                return b;
            }

            /**
             * Sets the value of the b property.
             *
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *
             */
            public void setB(BigInteger value) {
                this.b = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="separator" type="{http://www.abbyy.com/FineReader_xml/FineReader10-schema-v1.xml}SeparatorBlockType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "separator"
    })
    public static class SeparatorsBox {

        protected List<SeparatorBlockType> separator;

        /**
         * Gets the value of the separator property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the separator property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSeparator().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SeparatorBlockType }
         *
         *
         */
        public List<SeparatorBlockType> getSeparator() {
            if (separator == null) {
                separator = new ArrayList<SeparatorBlockType>();
            }
            return this.separator;
        }

    }

}
