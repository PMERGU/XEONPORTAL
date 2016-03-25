package za.co.xeon.domain.dto;

import za.co.xeon.external.sap.hibersap.CustomerOrdersByDateRFC;
import za.co.xeon.external.sap.hibersap.dto.EvResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Derick on 3/25/2016.
 */
public class DeliveryDto implements Serializable {
    private List<EvResult> shipments;

    public DeliveryDto() {
    }

    public DeliveryDto(List<EvResult> shipments) {
        this.shipments = shipments;
    }

    public List<EvResult> getShipments() {
        return shipments;
    }

    public void setShipments(List<EvResult> shipments) {
        this.shipments = shipments;
    }
}
