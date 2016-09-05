package za.co.xeon.domain;

import javafx.scene.paint.Material;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import za.co.xeon.domain.enumeration.DVType;
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

    @Column(name = "material_number")
    private String materialNumber;

    @Column(name = "order_quantity")
    private Integer orderQuantity;

    @Column(name = "warehouse")
    private String warehouse;

    @Column(name = "length")
    private Integer length;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "gross_weight")
    private Integer grossWeight;

    @Column(name = "net_weight")
    private Integer netWeight;

    @Column(name = "batch_number")
    private String batchNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "material_type")
    private MaterialType materialType;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

    @Column(name = "volume")
    private Float volume;

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

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public DVType getDvType() {
        return dvType;
    }

    public void setDvType(DVType dvType) {
        this.dvType = dvType;
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
