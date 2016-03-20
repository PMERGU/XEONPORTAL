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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for TableType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="caption" type="{http://www.abbyy.com/FineReader_xml/FineReader10-schema-v1.xml}CaptionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tableCell" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="text" type="{http://www.abbyy.com/FineReader_xml/FineReader10-schema-v1.xml}PageElementType" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="topPos" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="bottomPos" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="leftPos" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="rightPos" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="VerticalAlignment" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;enumeration value="top"/>
 *                       &lt;enumeration value="center"/>
 *                       &lt;enumeration value="bottom"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TableType", propOrder = {
    "caption",
    "tableCell"
})
public class TableType {

    protected List<CaptionType> caption;
    protected List<TableCell> tableCell;
    @XmlAttribute(name = "id", required = true)
    protected String id;

    /**
     * Gets the value of the caption property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the caption property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCaption().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CaptionType }
     *
     *
     */
    public List<CaptionType> getCaption() {
        if (caption == null) {
            caption = new ArrayList<CaptionType>();
        }
        return this.caption;
    }

    /**
     * Gets the value of the tableCell property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tableCell property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTableCell().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TableType.TableCell }
     *
     *
     */
    public List<TableCell> getTableCell() {
        if (tableCell == null) {
            tableCell = new ArrayList<TableCell>();
        }
        return this.tableCell;
    }

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
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="text" type="{http://www.abbyy.com/FineReader_xml/FineReader10-schema-v1.xml}PageElementType" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="topPos" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *       &lt;attribute name="bottomPos" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *       &lt;attribute name="leftPos" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *       &lt;attribute name="rightPos" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *       &lt;attribute name="VerticalAlignment" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;enumeration value="top"/>
     *             &lt;enumeration value="center"/>
     *             &lt;enumeration value="bottom"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "text"
    })
    public static class TableCell {

        protected PageElementType text;
        @XmlAttribute(name = "topPos", required = true)
        protected BigInteger topPos;
        @XmlAttribute(name = "bottomPos", required = true)
        protected BigInteger bottomPos;
        @XmlAttribute(name = "leftPos", required = true)
        protected BigInteger leftPos;
        @XmlAttribute(name = "rightPos", required = true)
        protected BigInteger rightPos;
        @XmlAttribute(name = "VerticalAlignment", required = true)
        protected String verticalAlignment;

        /**
         * Gets the value of the text property.
         *
         * @return
         *     possible object is
         *     {@link PageElementType }
         *
         */
        public PageElementType getText() {
            return text;
        }

        /**
         * Sets the value of the text property.
         *
         * @param value
         *     allowed object is
         *     {@link PageElementType }
         *
         */
        public void setText(PageElementType value) {
            this.text = value;
        }

        /**
         * Gets the value of the topPos property.
         *
         * @return
         *     possible object is
         *     {@link BigInteger }
         *
         */
        public BigInteger getTopPos() {
            return topPos;
        }

        /**
         * Sets the value of the topPos property.
         *
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *
         */
        public void setTopPos(BigInteger value) {
            this.topPos = value;
        }

        /**
         * Gets the value of the bottomPos property.
         *
         * @return
         *     possible object is
         *     {@link BigInteger }
         *
         */
        public BigInteger getBottomPos() {
            return bottomPos;
        }

        /**
         * Sets the value of the bottomPos property.
         *
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *
         */
        public void setBottomPos(BigInteger value) {
            this.bottomPos = value;
        }

        /**
         * Gets the value of the leftPos property.
         *
         * @return
         *     possible object is
         *     {@link BigInteger }
         *
         */
        public BigInteger getLeftPos() {
            return leftPos;
        }

        /**
         * Sets the value of the leftPos property.
         *
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *
         */
        public void setLeftPos(BigInteger value) {
            this.leftPos = value;
        }

        /**
         * Gets the value of the rightPos property.
         *
         * @return
         *     possible object is
         *     {@link BigInteger }
         *
         */
        public BigInteger getRightPos() {
            return rightPos;
        }

        /**
         * Sets the value of the rightPos property.
         *
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *
         */
        public void setRightPos(BigInteger value) {
            this.rightPos = value;
        }

        /**
         * Gets the value of the verticalAlignment property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getVerticalAlignment() {
            return verticalAlignment;
        }

        /**
         * Sets the value of the verticalAlignment property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setVerticalAlignment(String value) {
            this.verticalAlignment = value;
        }

    }

}
