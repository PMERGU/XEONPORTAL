package za.co.xeon.domain.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Derick on 3/25/2016.
 */
public class SalesOrderDto implements Serializable {
    private List<DeliveryDto> deliveries;

    public SalesOrderDto() {
    }

    public SalesOrderDto(List<DeliveryDto> deliveries) {
        this.deliveries = deliveries;
    }

    public List<DeliveryDto> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryDto> deliveries) {
        this.deliveries = deliveries;
    }
}
