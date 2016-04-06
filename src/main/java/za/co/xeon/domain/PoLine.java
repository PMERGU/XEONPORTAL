package za.co.xeon.domain;

import javafx.scene.paint.Material;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import za.co.xeon.domain.enumeration.MaterialType;
import za.co.xeon.domain.enumeration.PoState;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A PoLine.
 */
@Entity
@Table(name = "po_line")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PoLine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "material_number", nullable = false)
    private String materialNumber;

    @NotNull
    @Column(name = "order_quantity", nullable = false)
    private Integer orderQuantity;

    @NotNull
    @Column(name = "unit_of_measure", nullable = false)
    private String unitOfMeasure;

    @NotNull
    @Column(name = "warehouse", nullable = false)
    private String warehouse;

    @NotNull
    @Column(name = "length", nullable = false)
    private Integer length;

    @NotNull
    @Column(name = "width", nullable = false)
    private Integer width;

    @NotNull
    @Column(name = "height", nullable = false)
    private Integer height;

    @NotNull
    @Column(name = "gross_weight", nullable = false)
    private Integer grossWeight;

    @NotNull
    @Column(name = "net_weight", nullable = false)
    private Integer netWeight;

    @Column(name = "batch_number")
    private String batchNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "materialType", nullable = false)
    private MaterialType materialType;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

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

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Integer grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Integer getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Integer netWeight) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PoLine poLine = (PoLine) o;
        if(poLine.id == null || id == null) {
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
        return "PoLine{" +
            "id=" + id +
            ", materialNumber='" + materialNumber + "'" +
            ", orderQuantity='" + orderQuantity + "'" +
            ", unitOfMeasure='" + unitOfMeasure + "'" +
            ", warehouse='" + warehouse + "'" +
            ", length='" + length + "'" +
            ", width='" + width + "'" +
            ", height='" + height + "'" +
            ", grossWeight='" + grossWeight + "'" +
            ", netWeight='" + netWeight + "'" +
            ", batchNumber='" + batchNumber + "'" +
            '}';
    }
}
