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
import za.co.xeon.web.rest.dto.HandlingUnitDto;
import za.co.xeon.web.rest.dto.HandlingUnitUpdateDto;

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
        configuration.addBapiClasses( CustomerOrdersByDateRFC.class, HandlingUnitsRFC.class, UpdateHandlingUnitsRFC.class, UpdatePodRFC.class);
        sessionManager = configuration.buildSessionManager();
    }

    public List<EvResult> getCustomerOrdersByDate(String customerNumber){
        customerNumber = leftPad(customerNumber, 10);
        Session session = sessionManager.openSession();
        try {
            CustomerOrdersByDateRFC rfc = new CustomerOrdersByDateRFC(customerNumber, null, null);
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
        barcode = leftPad(barcode, 10);
        Session session = sessionManager.openSession();
        try {
            HandlingUnitsRFC rfc = new HandlingUnitsRFC(barcode);
            session.execute(rfc);

            return rfc;
        }catch(Exception e){
            log.error("Couldnt complete getHandelingUnits : + " + e.getMessage(), e);
            throw e;
        }finally {
            session.close();
        }
    }

    public List<BapiRet2> updateDeliveredHandelingUnits(String barcode, HandlingUnitUpdateDto handlingUnitUpdateDto) throws Exception {
        barcode = leftPad(barcode, 10);
        Session session = sessionManager.openSession();
        try {
            List<ImHuitem> imHuitems = new ArrayList<>();
            for(HandlingUnitDto dto : handlingUnitUpdateDto.getHandlingUnits()){
                imHuitems.add(new ImHuitem(barcode, dto.getHandlingUnit(), handlingUnitUpdateDto.getDate(), handlingUnitUpdateDto.getDate()));
            }
            UpdateHandlingUnitsRFC rfc = new UpdateHandlingUnitsRFC(imHuitems);
            session.execute(rfc);

            if(rfc.getReturn().get(0).getType() == 'E'){
                throw new Exception("Request [barcode:" + barcode + "] failed with SAP status code " + rfc.getReturn().get(0).getType() + " : " + rfc.getReturn().get(0).getMessage());
            }else{
                return rfc.getReturn();
            }
        }catch(Exception e){
            log.error("Couldnt complete getHandelingUnits : + " + e.getMessage(), e);
            throw e;
        }finally {
            session.close();
        }
    }

    public List<BapiRet2> updatePod(String barcode, String url) throws Exception {
        barcode = leftPad(barcode, 10);
        Session session = sessionManager.openSession();
        try {
            UpdatePodRFC rfc = new UpdatePodRFC(barcode, url);
            session.execute(rfc);
            if(rfc.getReturn().size() > 0 && rfc.getReturn().get(0).getType() == 'E'){
                throw new Exception("Request [barcode:" + barcode + "] failed with SAP status code " + rfc.getReturn().get(0).getType() + " : " + rfc.getReturn().get(0).getMessage());
            }else{
                return rfc.getReturn();
            }
        }catch(Exception e){
            log.error("Couldn't complete updatePod : " + e.getMessage(), e);
            throw e;
        }finally {
            session.close();
        }
    }

    private String leftPad(String padMePlease, int length){
        return org.apache.commons.lang.StringUtils.leftPad(padMePlease, length, "0");
    }

}
