package za.co.xeon.domain.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import za.co.xeon.external.sap.hibersap.dto.EvResult;

/**
 * Created by Derick on 3/25/2016.
 */
public class DeliveryDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6579009755477702424L;
	private Map<String, EvResult> shipments = new HashMap<>();

	public DeliveryDto(Map<String, EvResult> shipments) {
		this.shipments = shipments;
	}

	public DeliveryDto() {
	}

	public Map<String, EvResult> getShipments() {
		return shipments;
	}

	public void setShipments(Map<String, EvResult> shipments) {
		this.shipments = shipments;
	}

	public static DeliveryDto newDelivery(String shipment, EvResult evResult) {
		Map<String, EvResult> shipments = new HashMap<>();
		shipments.put(shipment, evResult);
		return new DeliveryDto(shipments);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		DeliveryDto that = (DeliveryDto) o;

		return shipments != null ? shipments.equals(that.shipments) : that.shipments == null;

	}

	@Override
	public int hashCode() {
		return shipments != null ? shipments.hashCode() : 0;
	}
}
