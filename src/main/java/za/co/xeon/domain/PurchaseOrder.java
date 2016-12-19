package za.co.xeon.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import za.co.xeon.domain.enumeration.CargoClassificationType;
import za.co.xeon.domain.enumeration.CargoType;
import za.co.xeon.domain.enumeration.CustomerType;
import za.co.xeon.domain.enumeration.DeliveryType;
import za.co.xeon.domain.enumeration.ModeOfTransport;
import za.co.xeon.domain.enumeration.PoState;
import za.co.xeon.domain.enumeration.Service;
import za.co.xeon.domain.enumeration.ServiceLevel;
import za.co.xeon.domain.enumeration.ServiceType;
import za.co.xeon.domain.enumeration.TradeType;
import za.co.xeon.domain.enumeration.TransportParty;
import za.co.xeon.domain.enumeration.VehicleSize;

/**
 * A PurchaseOrder.
 */
@Entity
@Table(name = "purchase_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class PurchaseOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4745545617591667683L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "state", nullable = false)
	private PoState state;

	@Enumerated(EnumType.STRING)
	@Column(name = "service_level")
	private ServiceLevel serviceLevel;

	@Column(name = "capture_date")
	private ZonedDateTime captureDate;

	@Column(name = "updated_date")
	private ZonedDateTime updatedDate;

	@Column(name = "delivery_date")
	private LocalDate dropOffDate;

	@Column(name = "collection_date")
	private LocalDate collectionDate;

	@Column(name = "collection_reference")
	private String collectionReference;

	@NotNull
	@Column(name = "po_number", nullable = false)
	private String poNumber;

	@NotNull
	@Column(name = "reference", nullable = false)
	private String reference;

	@Enumerated(EnumType.STRING)
	@Column(name = "customer_type")
	private CustomerType customerType;

	@Enumerated(EnumType.STRING)
	@Column(name = "ship_to_type")
	private DeliveryType shipToType;

	@Enumerated(EnumType.STRING)
	@Column(name = "pick_up_type")
	private DeliveryType pickUpType;

	@Column(name = "telephone")
	private String telephone;

	@NotNull
	@Column(name = "collective", nullable = false)
	private String collective;

	@NotNull
	@Column(name = "account_reference", nullable = false)
	private String accountReference;

	@Enumerated(EnumType.STRING)
	@Column(name = "mode_of_transport")
	private ModeOfTransport modeOfTransport;

	@Column(name = "comment")
	private String comment;

	@OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<PoLine> poLines = new ArrayList<>();

	@OneToOne
	private Party shipToParty;

	@OneToOne
	private Party pickUpParty;

	@Enumerated(EnumType.STRING)
	@Column(name = "cargo_classification")
	private CargoClassificationType cargoClassification;

	@Enumerated(EnumType.STRING)
	@Column(name = "cargo_type")
	private CargoType cargoType;

	@Enumerated(EnumType.STRING)
	@Column(name = "transport_party")
	private TransportParty transportParty;

	@Enumerated(EnumType.STRING)
	@Column(name = "vehicle_size")
	private VehicleSize vehicleSize;

	@Enumerated(EnumType.STRING)
	@Column(name = "service_type")
	private ServiceType serviceType;

	@Column(name = "labour_required")
	private String labourRequired;

	@Column(name = "special_instruction")
	private String specialInstruction;

	@OneToOne
	private Party soldToParty;

	@Column(name = "financial_controller")
	private String financialController;

	@Column(name = "so_number")
	private String soNumber;

	@Column(name = "so_comment")
	private String soComment;

	@Column(name = "cv_name")
	private String cvName;

	@Column(name = "cv_number")
	private String cvNumber;

	@Column(name = "cv_origin")
	private String cvOrigin;

	@Column(name = "cv_eta")
	private LocalDate cvEta;

	@Column(name = "cv_container_no")
	private String cvContainerNo;

	@Column(name = "cv_carrier_ref")
	private String cvCarrierRef;

	@Column(name = "cv_consol")
	private String cvConsol;

	@Column(name = "cv_waybill")
	private String cvWaybill;

	@Column(name = "cv_waybill_issue")
	private LocalDate cvWaybillIssue;

	@Column(name = "cv_house_waybill")
	private String cvHouseWaybill;

	@Column(name = "cv_house_waybill_issue")
	private LocalDate cvHouseWaybillIssue;

	@Column(name = "cv_shipper")
	private String cvShipper;

	@Column(name = "cv_etd")
	private LocalDate cvEtd;

	@Column(name = "cv_destination")
	private String cvDestination;

	@Column(name = "cv_commodity")
	private String cvCommodity;

	@Enumerated(EnumType.STRING)
	@Column(name = "service")
	private Service service;

	@Enumerated(EnumType.STRING)
	@Column(name = "trade_type")
	private TradeType tradeType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "xeon_user_id")
	private User xeonUser;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "captured_by")
	private User capturedBy;

	@OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Attachment> attachments = new ArrayList<>();

	@OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.EAGER, orphanRemoval = true)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	private List<Comment> comments = new ArrayList<>();

	public ZonedDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(ZonedDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public User getXeonUser() {
		return xeonUser;
	}

	public void setXeonUser(User xeonUser) {
		this.xeonUser = xeonUser;
	}

	public String getSoNumber() {
		return soNumber;
	}

	public void setSoNumber(String soNumber) {
		this.soNumber = soNumber;
	}

	public String getSoComment() {
		return soComment;
	}

	public void setSoComment(String soComment) {
		this.soComment = soComment;
	}

	public String getSpecialInstruction() {
		return specialInstruction;
	}

	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}

	public Party getSoldToParty() {
		return soldToParty;
	}

	public void setSoldToParty(Party soldToParty) {
		this.soldToParty = soldToParty;
	}

	public String getFinancialController() {
		return financialController;
	}

	public void setFinancialController(String financialController) {
		this.financialController = financialController;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public LocalDate getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(LocalDate collectionDate) {
		this.collectionDate = collectionDate;
	}

	public String getCollectionReference() {
		return collectionReference;
	}

	public void setCollectionReference(String collectionReference) {
		this.collectionReference = collectionReference;
	}

	public CargoClassificationType getCargoClassification() {
		return cargoClassification;
	}

	public void setCargoClassification(CargoClassificationType cargoClassification) {
		this.cargoClassification = cargoClassification;
	}

	public CargoType getCargoType() {
		return cargoType;
	}

	public void setCargoType(CargoType cargoType) {
		this.cargoType = cargoType;
	}

	public TransportParty getTransportParty() {
		return transportParty;
	}

	public void setTransportParty(TransportParty transportParty) {
		this.transportParty = transportParty;
	}

	public VehicleSize getVehicleSize() {
		return vehicleSize;
	}

	public void setVehicleSize(VehicleSize vehicleSize) {
		this.vehicleSize = vehicleSize;
	}

	public String getLabourRequired() {
		return labourRequired;
	}

	public void setLabourRequired(String labourRequired) {
		this.labourRequired = labourRequired;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PoState getState() {
		return state;
	}

	public void setState(PoState state) {
		this.state = state;
	}

	public ServiceLevel getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(ServiceLevel serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public ZonedDateTime getCaptureDate() {
		return captureDate;
	}

	public void setCaptureDate(ZonedDateTime captureDate) {
		this.captureDate = captureDate;
	}

	public LocalDate getDropOffDate() {
		return dropOffDate;
	}

	public void setDropOffDate(LocalDate dropOffDate) {
		this.dropOffDate = dropOffDate;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public DeliveryType getShipToType() {
		return shipToType;
	}

	public void setShipToType(DeliveryType shipToType) {
		this.shipToType = shipToType;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCollective() {
		return collective;
	}

	public void setCollective(String collective) {
		this.collective = collective;
	}

	public String getAccountReference() {
		return accountReference;
	}

	public void setAccountReference(String accountReference) {
		this.accountReference = accountReference;
	}

	public ModeOfTransport getModeOfTransport() {
		return modeOfTransport;
	}

	public void setModeOfTransport(ModeOfTransport modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public DeliveryType getPickUpType() {
		return pickUpType;
	}

	public void setPickUpType(DeliveryType pickUpType) {
		this.pickUpType = pickUpType;
	}

	public List<PoLine> getPoLines() {
		return poLines;
	}

	public void setPoLines(List<PoLine> poLines) {
		this.poLines = poLines;
	}

	public Party getShipToParty() {
		return shipToParty;
	}

	public void setShipToParty(Party party) {
		this.shipToParty = party;
	}

	public Party getPickUpParty() {
		return pickUpParty;
	}

	public void setPickUpParty(Party party) {
		this.pickUpParty = party;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCvName() {
		return cvName;
	}

	public void setCvName(String cvName) {
		this.cvName = cvName;
	}

	public String getCvNumber() {
		return cvNumber;
	}

	public void setCvNumber(String cvNumber) {
		this.cvNumber = cvNumber;
	}

	public String getCvOrigin() {
		return cvOrigin;
	}

	public void setCvOrigin(String cvOrigin) {
		this.cvOrigin = cvOrigin;
	}

	public LocalDate getCvEta() {
		return cvEta;
	}

	public void setCvEta(LocalDate cvEta) {
		this.cvEta = cvEta;
	}

	public String getCvContainerNo() {
		return cvContainerNo;
	}

	public void setCvContainerNo(String cvContainerNo) {
		this.cvContainerNo = cvContainerNo;
	}

	public String getCvCarrierRef() {
		return cvCarrierRef;
	}

	public void setCvCarrierRef(String cvCarrierRef) {
		this.cvCarrierRef = cvCarrierRef;
	}

	public String getCvConsol() {
		return cvConsol;
	}

	public void setCvConsol(String cvConsol) {
		this.cvConsol = cvConsol;
	}

	public String getCvWaybill() {
		return cvWaybill;
	}

	public void setCvWaybill(String cvWaybill) {
		this.cvWaybill = cvWaybill;
	}

	public LocalDate getCvWaybillIssue() {
		return cvWaybillIssue;
	}

	public void setCvWaybillIssue(LocalDate cvWaybillIssue) {
		this.cvWaybillIssue = cvWaybillIssue;
	}

	public String getCvHouseWaybill() {
		return cvHouseWaybill;
	}

	public void setCvHouseWaybill(String cvHouseWaybill) {
		this.cvHouseWaybill = cvHouseWaybill;
	}

	public LocalDate getCvHouseWaybillIssue() {
		return cvHouseWaybillIssue;
	}

	public void setCvHouseWaybillIssue(LocalDate cvHouseWaybillIssue) {
		this.cvHouseWaybillIssue = cvHouseWaybillIssue;
	}

	public String getCvShipper() {
		return cvShipper;
	}

	public void setCvShipper(String cvShipper) {
		this.cvShipper = cvShipper;
	}

	public LocalDate getCvEtd() {
		return cvEtd;
	}

	public void setCvEtd(LocalDate cvEtd) {
		this.cvEtd = cvEtd;
	}

	public String getCvDestination() {
		return cvDestination;
	}

	public void setCvDestination(String cvDestination) {
		this.cvDestination = cvDestination;
	}

	public String getCvCommodity() {
		return cvCommodity;
	}

	public void setCvCommodity(String cvCommodity) {
		this.cvCommodity = cvCommodity;
	}

	public User getCapturedBy() {
		return capturedBy;
	}

	public void setCapturedBy(User capturedBy) {
		this.capturedBy = capturedBy;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PurchaseOrder purchaseOrder = (PurchaseOrder) o;
		if (purchaseOrder.id == null || id == null) {
			return false;
		}
		return Objects.equals(id, purchaseOrder.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "PurchaseOrder{" + "id=" + id + ",  state=" + state + ",  captureDate=" + captureDate + ",  poNumber='" + poNumber + '\'' + ",  reference='" + reference + '\'' + ",  user=" + user + ",  xeonUser=" + xeonUser + ",  capturedBy=" + capturedBy + '}';
	}

	public String toStringFull() {
		return "PurchaseOrder{" + "id=" + id + ",  state=" + state + ",  serviceLevel=" + serviceLevel + ",  captureDate=" + captureDate + ",  updatedDate=" + updatedDate + ",  dropOffDate=" + dropOffDate + ",  collectionDate=" + collectionDate + ",  collectionReference='" + collectionReference + '\'' + ",  poNumber='" + poNumber + '\'' + ",  reference='" + reference + '\'' + ",  customerType=" + customerType + ",  shipToType=" + shipToType + ",  pickUpType=" + pickUpType + ",  telephone='" + telephone + '\'' + ",  collective='" + collective + '\'' + ",  accountReference='" + accountReference + '\'' + ",  modeOfTransport=" + modeOfTransport + ",  comment='" + comment + '\'' + ",  poLines=" + poLines + ",  shipToParty=" + shipToParty + ",  pickUpParty=" + pickUpParty + ",  cargoClassification=" + cargoClassification + ",  cargoType=" + cargoType + ",  transportParty=" + transportParty + ",  vehicleSize=" + vehicleSize + ",  serviceType=" + serviceType + ",  labourRequired='" + labourRequired + '\'' + ",  specialInstruction='" + specialInstruction + '\'' + ",  soldToParty=" + soldToParty + ",  financialController='" + financialController + '\'' + ",  soNumber='" + soNumber + '\''
				+ ",  soComment='" + soComment + '\'' + ",  cvName='" + cvName + '\'' + ",  cvNumber='" + cvNumber + '\'' + ",  cvOrigin='" + cvOrigin + '\'' + ",  cvEta=" + cvEta + ",  cvContainerNo='" + cvContainerNo + '\'' + ",  cvCarrierRef='" + cvCarrierRef + '\'' + ",  cvConsol='" + cvConsol + '\'' + ",  cvWaybill='" + cvWaybill + '\'' + ",  cvWaybillIssue=" + cvWaybillIssue + ",  cvHouseWaybill='" + cvHouseWaybill + '\'' + ",  cvHouseWaybillIssue=" + cvHouseWaybillIssue + ",  cvShipper='" + cvShipper + '\'' + ",  cvEtd=" + cvEtd + ",  cvDestination='" + cvDestination + '\'' + ",  cvCommodity='" + cvCommodity + '\'' + ",  user=" + user + ",  xeonUser=" + xeonUser + ",  capturedBy=" + capturedBy + '}';
	}
}
