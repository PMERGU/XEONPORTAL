package za.co.xeon.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import za.co.xeon.domain.enumeration.DVType;
import za.co.xeon.domain.enumeration.MaterialType;
import za.co.xeon.domain.enumeration.UnitOfMeasure;

/**
 * A PoLine.
 */
@Entity
@Table(name = "po_line")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PoLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4612452073433387968L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "material_number")
	private String materialNumber;

	@Column(name = "order_quantity")
	private Integer orderQuantity;

	@Column(name = "warehouse")
	private String warehouse;

	@Column(name = "length")
	private BigDecimal length;

	@Column(name = "width")
	private BigDecimal width;

	@Column(name = "height")
	private BigDecimal height;

	@Column(name = "gross_weight")
	private BigDecimal grossWeight;

	@Column(name = "net_weight")
	private BigDecimal netWeight;

	@Column(name = "batch_number")
	private String batchNumber;

	@Enumerated(EnumType.STRING)
	@Column(name = "material_type")
	private MaterialType materialType;

	@Enumerated(EnumType.STRING)
	@Column(name = "unit_of_measure")
	private UnitOfMeasure unitOfMeasure;

	@ManyToOne
	@JoinColumn(name = "purchase_order_id")
	private PurchaseOrder purchaseOrder;

	@Column(name = "volume")
	private BigDecimal volume;

	@Enumerated(EnumType.STRING)
	@Column(name = "dv_type")
	private DVType dvType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaterialNumber() {
		return materialNumber;
	}

	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}

	public BigDecimal getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public MaterialType getMaterialType() {
		return materialType;
	}

	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public DVType getDvType() {
		return dvType;
	}

	public void setDvType(DVType dvType) {
		this.dvType = dvType;
	}

	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PoLine poLine = (PoLine) o;
		if (poLine.id == null || id == null) {
			return false;
		}
		return Objects.equals(id, poLine.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "PoLine{" + "id=" + id + ", materialNumber='" + materialNumber + "'" + ", orderQuantity='" + orderQuantity + "'" + ", warehouse='" + warehouse + "'" + ", length='" + length + "'" + ", width='" + width + "'" + ", height='" + height + "'" + ", grossWeight='" + grossWeight + "'" + ", netWeight='" + netWeight + "'" + ", batchNumber='" + batchNumber + "'" + ", dvType='" + dvType + "'" + ", volume='" + volume + "'" + ", unitOfMeasure='" + unitOfMeasure + "'" + '}';
	}
}
