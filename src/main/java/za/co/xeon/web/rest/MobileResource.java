package za.co.xeon.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.springframework.web.bind.annotation.*;
import za.co.xeon.config.MobileConfiguration;
import za.co.xeon.external.ocr.Converters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import za.co.xeon.external.sap.hibersap.CustomerOrdersByDateRFC;
import za.co.xeon.external.sap.hibersap.EvResult;
import za.co.xeon.external.sap.hibersap.Huitem;
import za.co.xeon.service.MobileService;

import javax.annotation.PostConstruct;
import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by derick on 2016/02/08.
 */

@RestController
//Max uploaded file size (here it is 20 MB)
@MultipartConfig(fileSizeThreshold = 5971520)
public class MobileResource {
    private final static Logger log = LoggerFactory.getLogger(MobileResource.class);

    @Autowired
    private MobileService mobileService;

    @Autowired
    private MobileConfiguration mobileConf;

    private static File tmpDir = null;

    @PostConstruct
    public void init() {
        tmpDir = new File(mobileConf.getPodDirectory());
    }

    @RequestMapping(value = "/mobile/pods", method = RequestMethod.POST)
    @Timed
    public Callable<String> scanDocument(@RequestParam("podDocument") MultipartFile podDocument) throws Exception {
        log.debug("Service : /mobile/pod - uploadPOD " + tmpDir.getAbsolutePath());
        String originalFileName = podDocument.getOriginalFilename().substring(0, podDocument.getOriginalFilename().indexOf("."));;
        String originalExtension = podDocument.getOriginalFilename().substring(podDocument.getOriginalFilename().indexOf(".")+1);
        log.debug(originalFileName + "." + originalExtension);

        File podFile = Converters.multipartToFile(tmpDir, originalFileName, originalExtension, podDocument);
        log.debug("\t[" + originalFileName + "] - received file and saved to tmp folder : " + tmpDir.getAbsolutePath());

        //create callable to continue long running process in different thread
        Callable<String> task = () -> {
            try {
                mobileService.submitPOD(podFile, originalExtension);
                return "\t[" + podFile.getName() + "] - document submitted for processing";
            }
            catch (Exception e) {
                //TODO Deal with edge case for failed POD creation, might have to store for later processing.
                log.error("\t[" + podFile.getName() + "] - failed processing : " + e.getMessage(), e);
                throw new IllegalStateException("task interrupted", e);
            }
        };
        return task;
    }

    @RequestMapping(value = "/mobile/pods/{barcode}/handlingunits", method = RequestMethod.GET)
    @Timed
    public List<Huitem> getHandlingUnits(@PathVariable(value="barcode") String barcode) throws Exception {
        log.debug("Service /mobile/pod/" + barcode + "/handlingunits");
        return mobileService.getHandlingUnits(barcode);
    }

    @RequestMapping(value = "/mobile/customers/{customerNumber}/orders", method = RequestMethod.GET)
    @Timed
    public List<EvResult> getCustomerOrders(@PathVariable(value="customerNumber") String customerNumber) throws Exception {
        log.debug("Service /mobile/customer/" + customerNumber + "/orders");
        return mobileService.getCustomerOrders(customerNumber);
    }

}
