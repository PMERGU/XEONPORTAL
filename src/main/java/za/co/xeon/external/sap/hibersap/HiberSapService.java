package za.co.xeon.external.sap.hibersap;

import org.hibersap.bapi.BapiRet2;
import org.springframework.cache.annotation.Cacheable;
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
import za.co.xeon.external.sap.hibersap.dto.*;
import za.co.xeon.service.util.Pad;
import za.co.xeon.web.rest.dto.HandlingUnitDto;
import za.co.xeon.web.rest.dto.HandlingUnitUpdateDto;
import za.co.xeon.web.rest.dto.SalesOrderCreatedDTO;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.*;

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
        configuration.addBapiClasses(
            CustOrdersRFC.class,
            CustOrdersDetailRFC.class,
            CustomerOrdersByDateRFC.class,
            HandlingUnitsRFC.class,
            ReceivedHandlingUnitsRFC.class,
            SalesOrderCreateRFC.class,
            UpdateHandlingUnitsRFC.class,
            UpdatePodRFC.class);
        sessionManager = configuration.buildSessionManager();
    }


    public List<GtCustOrders> getCustomerOrdersByDate(String customerNumber, Date from, Date to, boolean evict) throws ParseException {
        customerNumber = Pad.left(customerNumber, 10);
        Session session = sessionManager.openSession();
        try {
            List<ImDateR> dateRange = new ArrayList<>();
            dateRange.add(new ImDateR("I", "BT", from, to));
            CustOrdersRFC rfc = new CustOrdersRFC(customerNumber, dateRange, "B");
            session.execute(rfc);

            return rfc.getGtCustOrders();
        }catch(Exception e){
            log.error("Couldnt complete getCustomerOrdersByDateNew : + " + e.getMessage(), e);
            throw e;
        }finally {
            session.close();
        }
    }

    public List<GtCustOrdersDetail> getCustomerOrderDetails(String orderNumber, Date from, Date to) throws ParseException {
        orderNumber = Pad.left(orderNumber, 10);
        Session session = sessionManager.openSession();
        try {
            List<ImDateR> dateRange = new ArrayList<>();
            dateRange.add(new ImDateR("I", "BT", from, to));
            CustOrdersDetailRFC rfc = new CustOrdersDetailRFC(dateRange, orderNumber);
            session.execute(rfc);

            return rfc.getGtCustOrdersDetail();
        }catch(Exception e){
            log.error("Couldnt complete getCustomerOrderDetails : + " + e.getMessage(), e);
            throw e;
        }finally {
            session.close();
        }
    }

    public HandlingUnitsRFC getHandelingUnits(String barcode){
        barcode = Pad.left(barcode, 10);
        Session session = sessionManager.openSession();
        try {
            HandlingUnitsRFC rfc = new HandlingUnitsRFC(barcode);
            session.execute(rfc);
            rfc.getHunumbers().stream().forEach(hunumbers ->
                hunumbers.setHuExid(Long.valueOf(hunumbers.getHuExid()).toString())
            );
            return rfc;
        }catch(Exception e){
            log.error("Couldnt complete getHandelingUnits : + " + e.getMessage(), e);
            throw e;
        }finally {
            session.close();
        }
    }

    public List<BapiRet2> updateDeliveredHandelingUnits(String barcode, HandlingUnitUpdateDto handlingUnitUpdateDto) throws Exception {
        barcode = Pad.left(barcode, 10);
        log.debug(String.format("[%s] - updateDeliveredHandelingUnits", barcode));
        Session session = sessionManager.openSession();
        try {
            List<ImHuitem> imHuitems = new ArrayList<>();
            log.debug(String.format("[%s] - hu count returned : %s", barcode, handlingUnitUpdateDto.getHandlingUnits().size()));
            for (HandlingUnitDto dto : handlingUnitUpdateDto.getHandlingUnits()) {
                log.debug(String.format("[%s] - ImHuitem.getHuExid(): %s", barcode, Pad.left(dto.getHandlingUnit(), 20)));
                imHuitems.add(new ImHuitem(barcode, Pad.left(dto.getHandlingUnit(), 20), handlingUnitUpdateDto.getDate(), handlingUnitUpdateDto.getDate()));
            };
            if(imHuitems.size() == 0){
                log.debug(String.format("[%s] - hu count 0, so making a new imHuitem record with just barcode....nothing else...damn you fairwell!!!", barcode));
                imHuitems.add(new ImHuitem(barcode, "", new Date(), new Date()));
            }
            UpdateHandlingUnitsRFC rfc = new UpdateHandlingUnitsRFC(imHuitems);
            session.execute(rfc);

            if(rfc.getReturn().isEmpty()) {
                return new ArrayList<>();
            }else if(rfc.getReturn().get(0).getType() == 'E'){
                throw new Exception("Request [barcode:" + barcode + "] failed with SAP status code " + rfc.getReturn().get(0).getType() + " : " + rfc.getReturn().get(0).getMessage());
            }else{
                return rfc.getReturn();
            }
        }catch(Exception e){
            log.error("Couldnt complete updateDeliveredHandelingUnits : + " + e.getMessage(), e);
            e.printStackTrace();
            throw e;
        }finally {
            session.close();
        }
    }

    public List<BapiRet2> pickupHandelingUnits(String barcode, List<ImHuupdate> imHuitems) throws Exception {
        barcode = Pad.left(barcode, 10);
        log.debug(String.format("[%s] - pickupHandelingUnits called with %s hu`s", barcode, imHuitems.size()));
        Session session = sessionManager.openSession();
        try {
            imHuitems.stream().forEach(imHuupdate ->
                imHuupdate.setExidv(Pad.left(imHuupdate.getExidv(), 20))
            );
            if(log.isDebugEnabled()) {
                for (ImHuupdate tmp : imHuitems) {
                    log.debug(String.format("[%s] - imHuupdate.getExidv(): %s, imHuupdate.getExtIdHu2(): %s", barcode, tmp.getExidv(), tmp.getExtIdHu2()));
                }
            }
            ReceivedHandlingUnitsRFC rfc = new ReceivedHandlingUnitsRFC(imHuitems);
            session.execute(rfc);

            if(rfc.getReturn().isEmpty()) {
                return new ArrayList<>();
            }else if(rfc.getReturn().get(0).getType() == 'E'){
                throw new Exception("Request [barcode:" + barcode + "] failed with SAP status code " + rfc.getReturn().get(0).getType() + " : " + rfc.getReturn().get(0).getMessage());
            }else{
                return rfc.getReturn();
            }
        }catch(Exception e){
            log.error("Couldnt complete pickupHandelingUnits : + " + e.getMessage(), e);
            e.printStackTrace();
            throw e;
        }finally {
            session.close();
        }
    }

    public List<BapiRet2> updatePod(String barcode, String url) throws Exception {
        barcode = Pad.left(barcode, 10);
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

    public SalesOrderCreatedDTO createSalesOrder(String po, SalesOrderCreateRFC rfc) throws Exception {
        log.debug("[{}] - createSalesOrder", po);
        Session session = sessionManager.openSession();
        try {
            session.execute(rfc);
            log.debug("[{}] - SO created : {}", po, rfc.getExSalesorder());

            if(rfc.getExReturn().size() > 0 && rfc.getExReturn().get(0).getType().equals("E")){
                throw new Exception("Request [po:" + po + "] failed with SAP status code " + rfc.getExReturn().get(0).getType() + " : " + rfc.getExReturn().get(0).getMessage());
            }else{
                log.debug("[{}] - Return count : [{}]", po, rfc.getExReturn().size());
                log.debug("[{}] - return", po);
                rfc.getExReturn().stream().forEach(exReturn -> log.debug("[{}] - return : \n{}", po, exReturn.toString()));
                for(ExReturn exReturn : rfc.getExReturn()){
                    if(exReturn.getType().equals("E") || exReturn.getType().equals("I")){
                        if(exReturn.getType().equals("I") && exReturn.getMessage().startsWith("Purchase order number " + po + " already exists")){
                            throw new DuplicatePoException("Request [po:" + po + "] failed with SAP status code " + exReturn.getType() + " : " + exReturn.getMessage());
                        }else {
                            throw new Exception("Request [po:" + po + "] failed with SAP status code " + exReturn.getType() + " : " + exReturn.getMessage());
                        }
                    }
                }
                return new SalesOrderCreatedDTO(rfc.getExSalesorder(), rfc.getExReturn());
            }
        }catch(Exception e){
            log.error("Couldn't complete createSalesOrder : " + e.getMessage(), e);
            throw e;
        }finally {
            session.close();
        }
    }

}
