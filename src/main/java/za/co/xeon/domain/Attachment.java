package za.co.xeon.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "attachment", indexes = { @Index(name = "attachment_uuid_idx", columnList = "uuid", unique = true), @Index(name = "attachment_deliveryNumber_idx", columnList = "delivery_number") })
public class Attachment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8653444127008812156L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "uuid", nullable = false)
	private String uuid = UUID.randomUUID().toString();

	@Column(name = "delivery_number", nullable = false)
	private String deliveryNumber;

	@NotNull
	@Column(name = "description")
	private String description;

	@NotNull
	@Column(name = "category", nullable = false)
	private String category;

	@Column(name = "created_date")
	private ZonedDateTime createdDate = ZonedDateTime.now();

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "mime_type")
	private String mimeType;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "activated")
	private boolean activated = true;

	@Column(name = "visible")
	private boolean visible = true;

	@ManyToOne
	@JoinColumn(name = "purchase_order_id")
	private PurchaseOrder purchaseOrder;

	public Attachment() {
	}

	public Attachment(String deliveryNumber, String description, String category, User user, boolean visible) {
		this.deliveryNumber = deliveryNumber;
		this.description = description;
		this.category = category;
		this.user = user;
		this.visible = visible;
	}

	public Attachment(PurchaseOrder purchaseOrder, String description, String category, User user, boolean visible) {
		this.purchaseOrder = purchaseOrder;
		this.description = description;
		this.category = category;
		this.user = user;
		this.visible = visible;
	}

	public Attachment(String deliveryNumber, String description, String category, String fileName, String mimeType, User user, boolean visible) {
		this(deliveryNumber, description, category, user, visible);
		this.fileName = fileName;
		this.mimeType = mimeType;
	}

	public Long getId() {
		return id;
	}

	public String getUuid() {
		return uuid;
	}

	public String getDeliveryNumber() {
		return deliveryNumber;
	}

	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	@Override
	public String toString() {
		return "Attachment{" + "uuid='" + uuid + '\'' + ", deliveryNumber='" + deliveryNumber + '\'' + ", description='" + description + '\'' + ", category='" + category + '\'' + ", createdDate=" + createdDate + ", fileName='" + fileName + '\'' + ", mimeType='" + mimeType + '\'' + ", activated=" + activated + ", visible=" + visible + '}';
	}
}
