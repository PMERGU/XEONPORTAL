package za.co.xeon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import za.co.xeon.domain.enumeration.*;

/**
 * A PurchaseOrder.
 */
@Entity
@Table(name = "purchase_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PurchaseOrder implements Serializable {

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

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

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

    @Column(name = "carrier_vessel_name")
    private String carrierVesselName;

    @Column(name = "carrier_vessel_number")
    private String carrierVesselNumber;

    @NotNull
    @Column(name = "comment", nullable = true)
    private String comment;

    @OneToMany(mappedBy = "purchaseOrder")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PoLine> poLines = new HashSet<>();

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public String getCarrierVesselName() {
        return carrierVesselName;
    }

    public void setCarrierVesselName(String carrierVesselName) {
        this.carrierVesselName = carrierVesselName;
    }

    public String getCarrierVesselNumber() {
        return carrierVesselNumber;
    }

    public void setCarrierVesselNumber(String carrierVesselNumber) {
        this.carrierVesselNumber = carrierVesselNumber;
    }

    public DeliveryType getPickUpType() {
        return pickUpType;
    }

    public void setPickUpType(DeliveryType pickUpType) {
        this.pickUpType = pickUpType;
    }

    public Set<PoLine> getPoLines() {
        return poLines;
    }

    public void setPoLines(Set<PoLine> poLines) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PurchaseOrder purchaseOrder = (PurchaseOrder) o;
        if(purchaseOrder.id == null || id == null) {
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
        return "PurchaseOrder{" +
            "id=" + id +
            ", state='" + state + "'" +
            ", serviceLevel='" + serviceLevel + "'" +
            ", captureDate='" + captureDate + "'" +
            ", deliveryDate='" + deliveryDate + "'" +
            ", poNumber='" + poNumber + "'" +
            ", reference='" + reference + "'" +
            ", customerType='" + customerType + "'" +
            ", shipToType='" + shipToType + "'" +
            ", telephone='" + telephone + "'" +
            ", collective='" + collective + "'" +
            ", accountReference='" + accountReference + "'" +
            ", modeOfTransport='" + modeOfTransport + "'" +
            ", carrierVesselName='" + carrierVesselName + "'" +
            ", carrierVesselNumber='" + carrierVesselNumber + "'" +
            ", pickUpType='" + pickUpType + "'" +
            '}';
    }
}
