package za.co.xeon.web.rest.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import za.co.xeon.domain.enumeration.CustomerType;
import za.co.xeon.domain.enumeration.DeliveryType;
import za.co.xeon.domain.enumeration.ModeOfTransport;
import za.co.xeon.domain.enumeration.PoState;
import za.co.xeon.domain.enumeration.ServiceLevel;

/**
 * A DTO for the PurchaseOrder entity.
 */
public class PurchaseOrderDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5162244287676682234L;

	private Long id;

    @NotNull
    private PoState state;


    private ServiceLevel serviceLevel;


    private ZonedDateTime captureDate;


    @NotNull
    private LocalDate deliveryDate;


    @NotNull
    private String poNumber;


    @NotNull
    private String reference;


    private CustomerType customerType;


    private DeliveryType shipToType;


    @NotNull
    private String telephone;


    @NotNull
    private String collective;


    @NotNull
    private String accountReference;


    private ModeOfTransport modeOfTransport;


    @NotNull
    private String carrierVesselName;


    @NotNull
    private String carrierVesselNumber;


    private DeliveryType pickUpType;


    private Long shipToPartyId;
    private Long pickUpPartyId;
    private Long employeeId;

    private String employeeName;

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

    public Long getShipToPartyId() {
        return shipToPartyId;
    }

    public void setShipToPartyId(Long partyId) {
        this.shipToPartyId = partyId;
    }
    public Long getPickUpPartyId() {
        return pickUpPartyId;
    }

    public void setPickUpPartyId(Long partyId) {
        this.pickUpPartyId = partyId;
    }
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PurchaseOrderDTO purchaseOrderDTO = (PurchaseOrderDTO) o;

        if ( ! Objects.equals(id, purchaseOrderDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PurchaseOrderDTO{" +
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
