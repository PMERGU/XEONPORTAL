package za.co.xeon.web.rest.mapper;

import za.co.xeon.domain.*;
import za.co.xeon.web.rest.dto.PurchaseOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PurchaseOrder and its DTO PurchaseOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PurchaseOrderMapper {

    @Mapping(source = "shipToParty.id", target = "shipToPartyId")
    @Mapping(source = "pickUpParty.id", target = "pickUpPartyId")
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "employee.name", target = "employeeName")
    PurchaseOrderDTO purchaseOrderToPurchaseOrderDTO(PurchaseOrder purchaseOrder);

    @Mapping(target = "poLines", ignore = true)
    @Mapping(source = "shipToPartyId", target = "shipToParty")
    @Mapping(source = "pickUpPartyId", target = "pickUpParty")
    @Mapping(source = "employeeId", target = "employee")
    PurchaseOrder purchaseOrderDTOToPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO);

    default Party partyFromId(Long id) {
        if (id == null) {
            return null;
        }
        Party party = new Party();
        party.setId(id);
        return party;
    }

    default Employee employeeFromId(Long id) {
        if (id == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(id);
        return employee;
    }
}
