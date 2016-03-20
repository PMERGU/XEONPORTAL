package za.co.xeon.web.rest;

import za.co.xeon.external.ocr.Converters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import za.co.xeon.service.MobileService;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.util.concurrent.Callable;

/**
 * Created by derick on 2016/02/08.
 */

@RestController
//Max uploaded file size (here it is 20 MB)
//@MultipartConfig(fileSizeThreshold = 5971520)
public class MobileResource {
    private final static Logger log = LoggerFactory.getLogger(MobileResource.class);

    @Autowired
    private MobileService mobileService;

    @RequestMapping(value = "/mobile/pod", method = RequestMethod.POST)
    public Callable<String> scanDocument(@RequestParam("podDocument") MultipartFile podDocument) throws Exception {
        log.debug("Service : /mobile/pod - uploadPOD");
        File podFile = Converters.multipartToFile(podDocument);
        String podFileExtension = podFile.getName().substring(podFile.getName().indexOf("."), podFile.getName().indexOf(".")+4);
        log.debug("  received file " + podFileExtension);

        //create callable to continue long running process in different thread
        Callable<String> task = () -> {
            try {
                mobileService.submitPOD(podFile, podFileExtension);
                return "Document submitted for processing";
            }
            catch (Exception e) {
                //TODO Deal with edge case for failed POD creation, might have to store for later processing.
                throw new IllegalStateException("task interrupted", e);
            }
        };
        return task;
    }


}
