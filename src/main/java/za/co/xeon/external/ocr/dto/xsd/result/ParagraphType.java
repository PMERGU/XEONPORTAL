//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2016.02.29 at 06:44:09 PM SAST
//

package za.co.xeon.external.ocr.dto.xsd.result;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ParagraphType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="ParagraphType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="line" type="{http://www.abbyy.com/FineReader_xml/FineReader10-schema-v1.xml}LineType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dropCapCharsCount" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="dropCap-l" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="dropCap-t" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="dropCap-r" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="dropCap-b" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="align" type="{http://www.abbyy.com/FineReader_xml/FineReader10-schema-v1.xml}ParagraphAlignment" default="Left" />
 *       &lt;attribute name="leftIndent" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="rightIndent" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="startIndent" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="lineSpacing" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="style" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="hasOverflowedHead" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="hasOverflowedTail" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParagraphType", propOrder = { "line" })
public class ParagraphType {

	protected List<LineType> line;
	@XmlAttribute(name = "dropCapCharsCount")
	protected BigInteger dropCapCharsCount;
	@XmlAttribute(name = "dropCap-l")
	protected BigInteger dropCapL;
	@XmlAttribute(name = "dropCap-t")
	protected BigInteger dropCapT;
	@XmlAttribute(name = "dropCap-r")
	protected BigInteger dropCapR;
	@XmlAttribute(name = "dropCap-b")
	protected BigInteger dropCapB;
	@XmlAttribute(name = "align")
	protected ParagraphAlignment align;
	@XmlAttribute(name = "leftIndent")
	protected BigInteger leftIndent;
	@XmlAttribute(name = "rightIndent")
	protected BigInteger rightIndent;
	@XmlAttribute(name = "startIndent")
	protected BigInteger startIndent;
	@XmlAttribute(name = "lineSpacing")
	protected BigInteger lineSpacing;
	@XmlAttribute(name = "id")
	protected String id;
	@XmlAttribute(name = "style")
	protected String style;
	@XmlAttribute(name = "hasOverflowedHead")
	protected Boolean hasOverflowedHead;
	@XmlAttribute(name = "hasOverflowedTail")
	protected Boolean hasOverflowedTail;

	/**
	 * Gets the value of the line property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the line property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getLine().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link LineType
	 * }
	 *
	 *
	 */
	public List<LineType> getLine() {
		if (line == null) {
			line = new ArrayList<LineType>();
		}
		return this.line;
	}

	/**
	 * Gets the value of the dropCapCharsCount property.
	 *
	 * @return possible object is {@link BigInteger }
	 *
	 */
	public BigInteger getDropCapCharsCount() {
		if (dropCapCharsCount == null) {
			return new BigInteger("0");
		} else {
			return dropCapCharsCount;
		}
	}

	/**
	 * Sets the value of the dropCapCharsCount property.
	 *
	 * @param value
	 *            allowed object is {@link BigInteger }
	 *
	 */
	public void setDropCapCharsCount(BigInteger value) {
		this.dropCapCharsCount = value;
	}

	/**
	 * Gets the value of the dropCapL property.
	 *
	 * @return possible object is {@link BigInteger }
	 *
	 */
	public BigInteger getDropCapL() {
		return dropCapL;
	}

	/**
	 * Sets the value of the dropCapL property.
	 *
	 * @param value
	 *            allowed object is {@link BigInteger }
	 *
	 */
	public void setDropCapL(BigInteger value) {
		this.dropCapL = value;
	}

	/**
	 * Gets the value of the dropCapT property.
	 *
	 * @return possible object is {@link BigInteger }
	 *
	 */
	public BigInteger getDropCapT() {
		return dropCapT;
	}

	/**
	 * Sets the value of the dropCapT property.
	 *
	 * @param value
	 *            allowed object is {@link BigInteger }
	 *
	 */
	public void setDropCapT(BigInteger value) {
		this.dropCapT = value;
	}

	/**
	 * Gets the value of the dropCapR property.
	 *
	 * @return possible object is {@link BigInteger }
	 *
	 */
	public BigInteger getDropCapR() {
		return dropCapR;
	}

	/**
	 * Sets the value of the dropCapR property.
	 *
	 * @param value
	 *            allowed object is {@link BigInteger }
	 *
	 */
	public void setDropCapR(BigInteger value) {
		this.dropCapR = value;
	}

	/**
	 * Gets the value of the dropCapB property.
	 *
	 * @return possible object is {@link BigInteger }
	 *
	 */
	public BigInteger getDropCapB() {
		return dropCapB;
	}

	/**
	 * Sets the value of the dropCapB property.
	 *
	 * @param value
	 *            allowed object is {@link BigInteger }
	 *
	 */
	public void setDropCapB(BigInteger value) {
		this.dropCapB = value;
	}

	/**
	 * Gets the value of the align property.
	 *
	 * @return possible object is {@link ParagraphAlignment }
	 *
	 */
	public ParagraphAlignment getAlign() {
		if (align == null) {
			return ParagraphAlignment.LEFT;
		} else {
			return align;
		}
	}

	/**
	 * Sets the value of the align property.
	 *
	 * @param value
	 *            allowed object is {@link ParagraphAlignment }
	 *
	 */
	public void setAlign(ParagraphAlignment value) {
		this.align = value;
	}

	/**
	 * Gets the value of the leftIndent property.
	 *
	 * @return possible object is {@link BigInteger }
	 *
	 */
	public BigInteger getLeftIndent() {
		if (leftIndent == null) {
			return new BigInteger("0");
		} else {
			return leftIndent;
		}
	}

	/**
	 * Sets the value of the leftIndent property.
	 *
	 * @param value
	 *            allowed object is {@link BigInteger }
	 *
	 */
	public void setLeftIndent(BigInteger value) {
		this.leftIndent = value;
	}

	/**
	 * Gets the value of the rightIndent property.
	 *
	 * @return possible object is {@link BigInteger }
	 *
	 */
	public BigInteger getRightIndent() {
		if (rightIndent == null) {
			return new BigInteger("0");
		} else {
			return rightIndent;
		}
	}

	/**
	 * Sets the value of the rightIndent property.
	 *
	 * @param value
	 *            allowed object is {@link BigInteger }
	 *
	 */
	public void setRightIndent(BigInteger value) {
		this.rightIndent = value;
	}

	/**
	 * Gets the value of the startIndent property.
	 *
	 * @return possible object is {@link BigInteger }
	 *
	 */
	public BigInteger getStartIndent() {
		if (startIndent == null) {
			return new BigInteger("0");
		} else {
			return startIndent;
		}
	}

	/**
	 * Sets the value of the startIndent property.
	 *
	 * @param value
	 *            allowed object is {@link BigInteger }
	 *
	 */
	public void setStartIndent(BigInteger value) {
		this.startIndent = value;
	}

	/**
	 * Gets the value of the lineSpacing property.
	 *
	 * @return possible object is {@link BigInteger }
	 *
	 */
	public BigInteger getLineSpacing() {
		if (lineSpacing == null) {
			return new BigInteger("0");
		} else {
			return lineSpacing;
		}
	}

	/**
	 * Sets the value of the lineSpacing property.
	 *
	 * @param value
	 *            allowed object is {@link BigInteger }
	 *
	 */
	public void setLineSpacing(BigInteger value) {
		this.lineSpacing = value;
	}

	/**
	 * Gets the value of the id property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Gets the value of the style property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * Sets the value of the style property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setStyle(String value) {
		this.style = value;
	}

	/**
	 * Gets the value of the hasOverflowedHead property.
	 *
	 * @return possible object is {@link Boolean }
	 *
	 */
	public boolean isHasOverflowedHead() {
		if (hasOverflowedHead == null) {
			return false;
		} else {
			return hasOverflowedHead;
		}
	}

	/**
	 * Sets the value of the hasOverflowedHead property.
	 *
	 * @param value
	 *            allowed object is {@link Boolean }
	 *
	 */
	public void setHasOverflowedHead(Boolean value) {
		this.hasOverflowedHead = value;
	}

	/**
	 * Gets the value of the hasOverflowedTail property.
	 *
	 * @return possible object is {@link Boolean }
	 *
	 */
	public boolean isHasOverflowedTail() {
		if (hasOverflowedTail == null) {
			return false;
		} else {
			return hasOverflowedTail;
		}
	}

	/**
	 * Sets the value of the hasOverflowedTail property.
	 *
	 * @param value
	 *            allowed object is {@link Boolean }
	 *
	 */
	public void setHasOverflowedTail(Boolean value) {
		this.hasOverflowedTail = value;
	}

}
