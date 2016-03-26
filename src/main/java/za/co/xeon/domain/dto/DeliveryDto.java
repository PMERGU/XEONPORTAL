package za.co.xeon.domain.dto;

import za.co.xeon.external.sap.hibersap.CustomerOrdersByDateRFC;
import za.co.xeon.external.sap.hibersap.dto.EvResult;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Derick on 3/25/2016.
 */
public class DeliveryDto implements Serializable {
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

    public static DeliveryDto newDelivery(String shipment, EvResult evResult){
        Map<String, EvResult> shipments = new HashMap<>();
        shipments.put(shipment, evResult);
        return new DeliveryDto(shipments);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryDto that = (DeliveryDto) o;

        return shipments != null ? shipments.equals(that.shipments) : that.shipments == null;

    }

    @Override
    public int hashCode() {
        return shipments != null ? shipments.hashCode() : 0;
    }
}
