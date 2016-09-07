package za.co.xeon.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.hibersap.bapi.BapiRet2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.wiring.ClassNameBeanWiringInfoResolver;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.co.xeon.config.MobileConfiguration;
import za.co.xeon.domain.Company;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.User;
import za.co.xeon.domain.enumeration.ServiceType;
import za.co.xeon.external.as3.S3Service;
import za.co.xeon.external.as3.S3Settings;
import za.co.xeon.external.ftp.FtpService;
import za.co.xeon.external.ocr.Converters;
import za.co.xeon.external.sap.hibersap.dto.GtCustOrders;
import za.co.xeon.external.sap.hibersap.dto.GtCustOrdersDetail;
import za.co.xeon.external.sap.hibersap.dto.Hunumbers;
import za.co.xeon.external.sap.hibersap.dto.ImHuupdate;
import za.co.xeon.repository.CompanyRepository;
import za.co.xeon.repository.PurchaseOrderRepository;
import za.co.xeon.repository.UserRepository;
import za.co.xeon.security.AuthoritiesConstants;
import za.co.xeon.security.SecurityUtils;
import za.co.xeon.service.MobileService;
import za.co.xeon.web.rest.dto.HandlingUnitUpdateDto;
import za.co.xeon.web.rest.util.HeaderUtil;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by derick on 2016/02/08.
 */

@RestController
@RequestMapping("/api")
//Max uploaded file size (here it is 20 MB)
@MultipartConfig(fileSizeThreshold = 15971520)
public class OpenResource {
    private final static Logger log = LoggerFactory.getLogger(OpenResource.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private CompanyRepository companyRepository;

    @Inject
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private MobileService mobileService;

    @Autowired
    private MobileConfiguration mobileConf;

    @Autowired
    private FtpService ftpService;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private S3Settings s3Settings;

    private static File tmpDir = null;

    @RequestMapping(value = "/open/orders/{deliveryNo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public Callable<OrderDetails> getCustomerOrderDetail(@PathVariable(value="deliveryNo") String deliveryNo, Pageable pageable) throws Exception {
        log.debug("Service [GET] /mobile/orders/" + deliveryNo);
        return () -> {
            List<GtCustOrdersDetail> sapOrders = mobileService.getCustomerOrderDetails(deliveryNo, new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-01"), new Date()).get();
            if(sapOrders.size()>1) {
                PurchaseOrder purchaseOrder = purchaseOrderRepository.findFirstByPoNumber(sapOrders.get(0).getBstkd());

                OrderDetails order = new OrderDetails(purchaseOrder, sapOrders);

                return order;
            }else{
                return null;
            }
        };
    }

    public class OrderDetails{
        private PurchaseOrder purchaseOrder;
        private List<GtCustOrdersDetail> sapOrders;

        public OrderDetails(PurchaseOrder purchaseOrder, List<GtCustOrdersDetail> sapOrders) {
            this.purchaseOrder = purchaseOrder;
            this.sapOrders = sapOrders;
        }

        public PurchaseOrder getPurchaseOrder() {
            return purchaseOrder;
        }

        public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
            this.purchaseOrder = purchaseOrder;
        }

        public List<GtCustOrdersDetail> getSapOrders() {
            return sapOrders;
        }

        public void setSapOrders(List<GtCustOrdersDetail> sapOrders) {
            this.sapOrders = sapOrders;
        }
    }

}
