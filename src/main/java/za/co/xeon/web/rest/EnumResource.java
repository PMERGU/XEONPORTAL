package za.co.xeon.web.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import za.co.xeon.domain.enumeration.AttachmentCategories;
import za.co.xeon.domain.enumeration.BillingInfoTypes;
import za.co.xeon.domain.enumeration.CargoType;
import za.co.xeon.domain.enumeration.DeliveryType;
import za.co.xeon.domain.enumeration.MaterialType;
import za.co.xeon.domain.enumeration.ModeOfTransport;
import za.co.xeon.domain.enumeration.PartyType;
import za.co.xeon.domain.enumeration.Service;
import za.co.xeon.domain.enumeration.ServiceLevel;
import za.co.xeon.domain.enumeration.ServiceType;
import za.co.xeon.domain.enumeration.TradeType;
import za.co.xeon.domain.enumeration.UnitOfMeasure;
import za.co.xeon.domain.enumeration.VehicleSize;
import za.co.xeon.domain.enumeration.ZoneTypes;

@RestController
@RequestMapping("/api/static")
public class EnumResource {


    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map<String, List<?>> allStaticTypes(){
        Map<String, List<?>> enums = new HashMap<>();
        enums.put("attachmentCategories", Arrays.asList(AttachmentCategories.values()));
        enums.put("cargoTypes", Arrays.asList(CargoType.values()));
        enums.put("deliveryTypes", Arrays.asList(DeliveryType.values()));
        enums.put("materialTypes", Arrays.asList(MaterialType.values()));
        enums.put("partyTypes", Arrays.asList(PartyType.values()));
        enums.put("serviceLevels", Arrays.asList(ServiceLevel.values()));
        enums.put("serviceTypes", Arrays.asList(ServiceType.values()));
        enums.put("services", Arrays.asList(Service.values()));
        enums.put("tradeTypes", Arrays.asList(TradeType.values()));
        enums.put("vehicleSizes", Arrays.asList(VehicleSize.values()));
        enums.put("modeOfTransports", Arrays.asList(ModeOfTransport.values()));
        enums.put("unitOfMeasures", Arrays.asList(UnitOfMeasure.values()));
        enums.put("billinginfo", Arrays.asList(BillingInfoTypes.values()));
        enums.put("zones", Arrays.asList(ZoneTypes.values()));
        return enums;
    }

    @RequestMapping(value = "/decode/{enumName}", method = RequestMethod.GET)
    public AttachmentCategories detectEnum(@PathVariable String enumName){
        return AttachmentCategories.valueOf(enumName);
    }

    @RequestMapping(value = "/decode2/{enumName}", method = RequestMethod.GET)
    public AttachmentCategories detect2Enum(@PathVariable AttachmentCategories enumName){
        return enumName;
    }
}
