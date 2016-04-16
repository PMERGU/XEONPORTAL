package za.co.xeon.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.hibersap.bapi.BapiRet2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import za.co.xeon.config.MobileConfiguration;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.User;
import za.co.xeon.domain.dto.PurchaseOrderDto;
import za.co.xeon.external.ocr.Converters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import za.co.xeon.external.sap.hibersap.dto.EvResult;
import za.co.xeon.external.sap.hibersap.dto.Huitem;
import za.co.xeon.external.sap.hibersap.dto.Hunumbers;
import za.co.xeon.external.sap.hibersap.dto.ImHuitem;
import za.co.xeon.repository.CompanyRepository;
import za.co.xeon.repository.UserRepository;
import za.co.xeon.security.AuthoritiesConstants;
import za.co.xeon.security.SecurityUtils;
import za.co.xeon.service.MobileService;
import za.co.xeon.web.rest.dto.HandlingUnitUpdateDto;
import za.co.xeon.web.rest.util.PaginationUtil;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by derick on 2016/02/08.
 */

@RestController
@RequestMapping("/api")
//Max uploaded file size (here it is 20 MB)
@MultipartConfig(fileSizeThreshold = 5971520)
public class MobileResource {
    private final static Logger log = LoggerFactory.getLogger(MobileResource.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private CompanyRepository companyRepository;

    @Autowired
    private MobileService mobileService;

    @Autowired
    private MobileConfiguration mobileConf;

    private static File tmpDir = null;

    @PostConstruct
    public void init() {
        tmpDir = new File(mobileConf.getPodDirectory());
    }

    @RequestMapping(value = "/mobile/pods/{barcode}", method = RequestMethod.POST)
    @Timed
    public Callable<String> scanDocument(@PathVariable(value="barcode") String barcode, @RequestParam("podDocument") MultipartFile podDocument) throws Exception {
        log.debug("Service : [POST} /mobile/pod - uploadPOD " + tmpDir.getAbsolutePath());
        String originalFileName = podDocument.getOriginalFilename().substring(0, podDocument.getOriginalFilename().indexOf("."));;
        String originalExtension = podDocument.getOriginalFilename().substring(podDocument.getOriginalFilename().indexOf(".")+1);
        log.debug(originalFileName + "." + originalExtension);

        File podFile = Converters.multipartToFile(tmpDir, originalFileName, originalExtension, podDocument);
        log.debug("\t[" + originalFileName + "] - received file and saved to tmp folder : " + tmpDir.getAbsolutePath());

        //create callable to continue long running process in different thread
        Callable<String> task = () -> {
            mobileService.submitPOD(barcode, podFile, originalExtension);
            return "\t[" + podFile.getName() + "] - document submitted for processing";
        };
        return task;
    }

    @RequestMapping(value = "/mobile/customers/{customerNumber}/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<EvResult> getCustomerOrders(@PathVariable(value="customerNumber") String customerNumber,
                                            @RequestParam(value = "from") String from, @RequestParam(value = "to") String to,Pageable pageable) throws Exception {
        log.debug("Service [GET] /mobile/customer/" + customerNumber + "/orders");
        Future<List<EvResult>> future;
        if(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.CUSTOMER)){
            User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
            log.debug("Restricting CustomerOrders lookup by user[" + user.getLogin() + "].company.sapId : " + user.getCompany().getSapId());
             future = mobileService.getCustomerOrders(user.getCompany().getSapId(),
                new SimpleDateFormat("yyyy-MM-dd").parse(from), new SimpleDateFormat("yyyy-MM-dd").parse(to));
        }else {
            future = mobileService.getCustomerOrders(customerNumber,
                new SimpleDateFormat("yyyy-MM-dd").parse(from), new SimpleDateFormat("yyyy-MM-dd").parse(to));
        }
        while (!future.isDone()) {
            Thread.sleep(500);
        }
        return future.get();
    }

    @RequestMapping(value = "/mobile/pods/{barcode}/handlingunits", method = RequestMethod.GET)
    @Timed
    public List<Hunumbers> getHandlingUnits(@PathVariable(value="barcode") String barcode) throws Exception {
        log.debug("Service [GET] /mobile/pod/" + barcode + "/handlingunits");
        return mobileService.getHandlingUnits(barcode);
    }

    @RequestMapping(value = "/mobile/pods/{barcode}/handlingunits", method = RequestMethod.PUT)
    @Timed
    public List<BapiRet2> updateHandelingUnits(@PathVariable(value="barcode") String barcode, @RequestBody HandlingUnitUpdateDto handlingUnitUpdateDto) throws Exception {
        log.debug("Service [PUT] /mobile/pods/" + barcode + "/handlingunits");
        return mobileService.updateDeliveredHandelingUnits(barcode, handlingUnitUpdateDto);
    }

    @RequestMapping(value = "/mobile/pods/{barcode}/url", method = RequestMethod.PUT)
    @Timed
    public List<BapiRet2> updatePod(@PathVariable(value="barcode") String barcode, @RequestBody String url) throws Exception {
        return mobileService.updatePod(barcode, url);
    }

}
