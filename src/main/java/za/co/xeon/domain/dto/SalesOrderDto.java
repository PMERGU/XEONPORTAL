package za.co.xeon.domain.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Derick on 3/25/2016.
 */
public class SalesOrderDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5956623688653120876L;
	private Map<String, DeliveryDto> deliveryOrders = new HashMap<>();

    public SalesOrderDto(Map<String, DeliveryDto> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
    }

    public SalesOrderDto() {
    }

    public Map<String, DeliveryDto> getDeliveryOrders() {
        return deliveryOrders;
    }

    public void setDeliveryOrders(Map<String, DeliveryDto> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
    }

    public static SalesOrderDto newSalesOrder(String deliveryNo, DeliveryDto deliveryDto){
        Map<String, DeliveryDto> deliveryOrders = new HashMap<>();
        deliveryOrders.put(deliveryNo, deliveryDto);
        return new SalesOrderDto(deliveryOrders);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalesOrderDto that = (SalesOrderDto) o;

        return deliveryOrders != null ? deliveryOrders.equals(that.deliveryOrders) : that.deliveryOrders == null;

    }

    @Override
    public int hashCode() {
        return deliveryOrders != null ? deliveryOrders.hashCode() : 0;
    }
}
