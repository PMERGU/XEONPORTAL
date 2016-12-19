package za.co.xeon.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import za.co.xeon.domain.enumeration.CommentCategory;

/**
 * A Party.
 */
@Entity
@Table(name = "comment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7816873968319998217L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "message", nullable = false)
	private String message;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchase_order_id")
	private PurchaseOrder purchaseOrder;

	@Column(name = "capture_date")
	private ZonedDateTime captureDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "internal", nullable = false)
	@NotNull
	private boolean internal;

	@Column(name = "category", nullable = false)
	@NotNull
	@Enumerated(value = EnumType.STRING)
	private CommentCategory category;

	public Comment() {
		this.captureDate = ZonedDateTime.now();
	}

	public Comment(String message, PurchaseOrder purchaseOrder, User user) {
		this.message = message;
		this.purchaseOrder = purchaseOrder;
		this.user = user;
		this.internal = false;
		this.category = CommentCategory.ORDER;
		this.captureDate = ZonedDateTime.now();
	}

	public Comment(String message, PurchaseOrder purchaseOrder, User user, boolean internal, CommentCategory category) {
		this.message = message;
		this.purchaseOrder = purchaseOrder;
		this.user = user;
		this.internal = internal;
		this.category = category;
		this.captureDate = ZonedDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isInternal() {
		return internal;
	}

	public void setInternal(boolean internal) {
		this.internal = internal;
	}

	public CommentCategory getCategory() {
		return category;
	}

	public void setCategory(CommentCategory category) {
		this.category = category;
	}

	public ZonedDateTime getCaptureDate() {
		return captureDate;
	}

	public void setCaptureDate(ZonedDateTime captureDate) {
		this.captureDate = captureDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Comment comment = (Comment) o;

		if (id != null ? !id.equals(comment.id) : comment.id != null)
			return false;
		if (message != null ? !message.equals(comment.message) : comment.message != null)
			return false;
		if (purchaseOrder != null ? !purchaseOrder.equals(comment.purchaseOrder) : comment.purchaseOrder != null)
			return false;
		return user != null ? user.equals(comment.user) : comment.user == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (message != null ? message.hashCode() : 0);
		result = 31 * result + (purchaseOrder != null ? purchaseOrder.hashCode() : 0);
		result = 31 * result + (user != null ? user.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Comment{" + "id=" + id + ", message='" + message + '\'' + ", purchaseOrder=" + purchaseOrder.getId() + ", user=" + user + '}';
	}
}
