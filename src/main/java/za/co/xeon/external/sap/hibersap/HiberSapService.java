package za.co.xeon.external.sap.hibersap;

import org.hibersap.bapi.BapiRet2;
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

import javax.annotation.PostConstruct;
import java.util.List;

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
        configuration.addBapiClasses( CustomerOrdersByDateRFC.class, HandlingUnitsRFC.class );
        sessionManager = configuration.buildSessionManager();
    }

    public CustomerOrdersByDateRFC getCustomerOrdersByDate(String customerNumber){
        String paddedNumber = "0000000000".substring(customerNumber.length()) + customerNumber;
        Session session = sessionManager.openSession();
        try {
            CustomerOrdersByDateRFC rfc = new CustomerOrdersByDateRFC(paddedNumber, null, null);
            session.execute(rfc);
            List<EvResult> tmp = rfc.getEvResult();
            for (EvResult line : tmp) {
                log.debug("\t" + line.getSkunnr() + " - " + line.getBstkd());
            }

            log.debug("\nReturn");
            BapiRet2 returnStruct = rfc.getEvReturn();
            log.debug("\tMessage: " + returnStruct.getMessage());
            log.debug("\tNumber: " + returnStruct.getNumber());
            log.debug("\tType: " + returnStruct.getType());

            return rfc;
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

            log.debug("\nReturn");
            List<BapiRet2> returnStruct = rfc.getReturn();
            for (BapiRet2 bapiRet2 : returnStruct) {
                log.debug("\tMessage: " + bapiRet2.getMessage());
                log.debug("\tNumber: " + bapiRet2.getNumber());
                log.debug("\tType: " + bapiRet2.getType());
            }
            return rfc;
        }catch(Exception e){
            log.error("Couldnt complete getHandelingUnits : + " + e.getMessage(), e);
            throw e;
        }finally {
            session.close();
        }
    }

}
