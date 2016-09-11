package za.co.xeon.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import za.co.xeon.domain.enumeration.*;

import javax.servlet.annotation.MultipartConfig;
import java.util.*;

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
