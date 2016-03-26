package za.co.xeon.external.sap.hibersap;

import org.hibersap.bapi.BapiRet2;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.dto.DeliveryDto;
import za.co.xeon.domain.dto.PurchaseOrderDto;
import za.co.xeon.domain.dto.SalesOrderDto;
import za.co.xeon.external.sap.SapSettings;
import com.sap.conn.jco.ext.DestinationDataProvider;
import org.hibersap.configuration.AnnotationConfiguration;
import org.hibersap.configuration.xml.SessionManagerConfig;
import org.hibersap.execution.jco.JCoContext;
import org.hibersap.session.Session;
import org.hibersap.session.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.xeon.external.sap.hibersap.dto.EvResult;
import za.co.xeon.external.sap.hibersap.dto.Hunumbers;
import za.co.xeon.external.sap.hibersap.dto.ImHuitem;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by derick on 2016/02/07.
 */
@Service
public class HiberSapService {
    private final static Logger log = LoggerFactory.getLogger(HiberSapService.class);

    @Autowired
    private SapSettings s3Settings;

    private SessionManager sessionManager;

    @PostConstruct
    public void init(){
        log.debug("SapService, post construct " + s3Settings.getAshost());
        SessionManagerConfig cfg = new SessionManagerConfig( "A12" )
                .setContext( JCoContext.class.getName() )
                .setProperty(DestinationDataProvider.JCO_ASHOST, s3Settings.getAshost())
                .setProperty(DestinationDataProvider.JCO_SYSNR,  s3Settings.getSysnr())
                .setProperty(DestinationDataProvider.JCO_CLIENT, s3Settings.getClient())
                .setProperty(DestinationDataProvider.JCO_USER,   s3Settings.getUser())
                .setProperty(DestinationDataProvider.JCO_PASSWD, s3Settings.getPasswd())
                .setProperty(DestinationDataProvider.JCO_LANG,   s3Settings.getLang())
                .setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, s3Settings.getPoolCapacity())
                .setProperty(DestinationDataProvider.JCO_PEAK_LIMIT,    s3Settings.getPeakLimit());

        AnnotationConfiguration configuration = new AnnotationConfiguration(cfg);
        configuration.addBapiClasses( CustomerOrdersByDateRFC.class, HandlingUnitsRFC.class, UpdateHandlingUnitsRFC.class);
        sessionManager = configuration.buildSessionManager();
    }

//    public Map<String, PurchaseOrderDto> convertEvResultToPO(List<EvResult> results){
//        Map<String, PurchaseOrderDto> poDtos = new HashMap<>();
//        log.debug(results.get(0).getBstkd());
    // =========== something not working here, it seems to create a hashmap and then below doesnt work. Think there is bug....
//        for(EvResult evResult : results){
//            String po = evResult.getBstkd();
//            String so = evResult.getVbeln();
//            String delivery = evResult.getDbeln();
//            String shipment = evResult.getTknum();
//
//            if(poDtos.containsKey(po)){
//                PurchaseOrderDto purchaseOrderDto = poDtos.get(po);
//
//            }else{
//                poDtos.put(po, PurchaseOrderDto.newPurchaseOrder(so, SalesOrderDto.newSalesOrder(delivery, DeliveryDto.newDelivery(shipment, evResult))));
//            }
//
//        }
//        return poDtos;
//    }

    public List<EvResult> getCustomerOrdersByDate(String customerNumber){
        String paddedNumber = "0000000000".substring(customerNumber.length()) + customerNumber;
        Session session = sessionManager.openSession();
        try {
            CustomerOrdersByDateRFC rfc = new CustomerOrdersByDateRFC(paddedNumber, null, null);
            session.execute(rfc);

//            return convertEvResultToPO(rfc.getEvResult());
            return rfc.getEvResult();
        }catch(Exception e){
            log.error("Couldnt complete getCustomerOrdersByDate : + " + e.getMessage(), e);
            throw e;
        }finally {
            session.close();
        }
    }

    public HandlingUnitsRFC getHandelingUnits(String barcode){
        String paddedNumber = "0000000000".substring(barcode.length()) + barcode;
        Session session = sessionManager.openSession();
        try {
            HandlingUnitsRFC rfc = new HandlingUnitsRFC(paddedNumber);
            session.execute(rfc);

            return rfc;
        }catch(Exception e){
            log.error("Couldnt complete getHandelingUnits : + " + e.getMessage(), e);
            throw e;
        }finally {
            session.close();
        }
    }

    public List<BapiRet2> updateDeliveredHandelingUnits(String barcode, List<ImHuitem> handlingUnits){
//        String paddedNumber = "0000000000".substring(barcode.length()) + barcode;
//        Session session = sessionManager.openSession();
//        try {
//            UpdateHandlingUnitsRFC rfc = new UpdateHandlingUnitsRFC(handlingUnits);
//            session.execute(rfc);
//            return rfc.getReturn();
//        }catch(Exception e){
//            log.error("Couldnt complete getHandelingUnits : + " + e.getMessage(), e);
//            throw e;
//        }finally {
//            session.close();
//        }
        //RFC needs fixing
        return new ArrayList<>();
    }

}
