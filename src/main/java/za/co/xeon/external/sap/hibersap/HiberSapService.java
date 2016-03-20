//package za.co.xeon.external.sap.hibersap;
//
//import za.co.xeon.external.sap.SapSettings;
//import com.sap.conn.jco.ext.DestinationDataProvider;
//import org.hibersap.configuration.AnnotationConfiguration;
//import org.hibersap.configuration.xml.SessionManagerConfig;
//import org.hibersap.execution.jco.JCoContext;
//import org.hibersap.session.Session;
//import org.hibersap.session.SessionManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
///**
// * Created by derick on 2016/02/07.
// */
//@Service
//public class HiberSapService {
//    private final static Logger log = LoggerFactory.getLogger(HiberSapService.class);
//
//    @Autowired
//    private SapSettings s3Settings;
//
//    private SessionManager sessionManager;
//
//    @PostConstruct
//    public void init(){
//        log.debug("SapService, post construct " + s3Settings.getAshost());
//        SessionManagerConfig cfg = new SessionManagerConfig( "A12" )
//                .setContext( JCoContext.class.getName() )
//                .setProperty(DestinationDataProvider.JCO_ASHOST, s3Settings.getAshost())
//                .setProperty(DestinationDataProvider.JCO_SYSNR,  s3Settings.getSysnr())
//                .setProperty(DestinationDataProvider.JCO_CLIENT, s3Settings.getClient())
//                .setProperty(DestinationDataProvider.JCO_USER,   s3Settings.getUser())
//                .setProperty(DestinationDataProvider.JCO_PASSWD, s3Settings.getPasswd())
//                .setProperty(DestinationDataProvider.JCO_LANG,   s3Settings.getLang())
//                .setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, s3Settings.getPoolCapacity())
//                .setProperty(DestinationDataProvider.JCO_PEAK_LIMIT,    s3Settings.getPeakLimit());
//
//        AnnotationConfiguration configuration = new AnnotationConfiguration(cfg);
//        configuration.addBapiClasses( CustomerOrdersByDateRFC.class );
//        sessionManager = configuration.buildSessionManager();
//    }
//
//    public void getCustomerOrdersByDate(String customerNumber, String podStatus){
//        Session session = sessionManager.openSession();
//        try {
//            CustomerOrdersByDateRFC ordersRFC = new CustomerOrdersByDateRFC(customerNumber, podStatus);
//            session.execute(ordersRFC);
//            List<Order> orders = ordersRFC.getOrderList();
//            for (Order order : orders) {
//                System.out.print("\t" + order.getSkuNumber() + " - " + order.getName());
//            }
//
//            System.out.println("\nReturn");
//            BapiRet2 returnStruct = ordersRFC.getReturnData();
//            System.out.println("\tMessage: " + returnStruct.getMessage());
//            System.out.println("\tNumber: " + returnStruct.getNumber());
//            System.out.println("\tType: " + returnStruct.getType());
//            System.out.println("\tId: " + returnStruct.getLogMsgNo());
//            System.out.println("\tId: " + returnStruct.getLogNo());
//            System.out.println("\tId: " + returnStruct.getMessaveV1());
//            System.out.println("\tId: " + returnStruct.getMessaveV2());
//            System.out.println("\tId: " + returnStruct.getMessaveV3());
//            System.out.println("\tId: " + returnStruct.getMessaveV4());
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally {
//            session.close();
//        }
//    }
//
//    public void getHandelingUnits(String deliveryNo){
//        Session session = sessionManager.openSession();
//        try {
//            CustomerOrdersByDateRFC ordersRFC = new CustomerOrdersByDateRFC(customerNumber, podStatus);
//            session.execute(ordersRFC);
//            List<Order> orders = ordersRFC.getOrderList();
//            for (Order order : orders) {
//                System.out.print("\t" + order.getSkuNumber() + " - " + order.getName());
//            }
//
//            System.out.println("\nReturn");
//            BapiRet2 returnStruct = ordersRFC.getReturnData();
//            System.out.println("\tMessage: " + returnStruct.getMessage());
//            System.out.println("\tNumber: " + returnStruct.getNumber());
//            System.out.println("\tType: " + returnStruct.getType());
//            System.out.println("\tId: " + returnStruct.getLogMsgNo());
//            System.out.println("\tId: " + returnStruct.getLogNo());
//            System.out.println("\tId: " + returnStruct.getMessaveV1());
//            System.out.println("\tId: " + returnStruct.getMessaveV2());
//            System.out.println("\tId: " + returnStruct.getMessaveV3());
//            System.out.println("\tId: " + returnStruct.getMessaveV4());
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally {
//            session.close();
//        }
//    }
//
//}
