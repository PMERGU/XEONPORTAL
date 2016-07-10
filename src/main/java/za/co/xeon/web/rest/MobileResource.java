package za.co.xeon.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.apache.commons.io.IOUtils;
import org.hibersap.bapi.BapiRet2;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import za.co.xeon.config.MobileConfiguration;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.User;
import za.co.xeon.domain.dto.PurchaseOrderDto;
import za.co.xeon.external.as3.S3Service;
import za.co.xeon.external.as3.S3Settings;
import za.co.xeon.external.ftp.FtpService;
import za.co.xeon.external.ocr.Converters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import za.co.xeon.external.sap.hibersap.dto.*;
import za.co.xeon.repository.CompanyRepository;
import za.co.xeon.repository.PurchaseOrderRepository;
import za.co.xeon.repository.UserRepository;
import za.co.xeon.security.AuthoritiesConstants;
import za.co.xeon.security.SecurityUtils;
import za.co.xeon.service.MobileService;
import za.co.xeon.web.rest.dto.HandlingUnitUpdateDto;
import za.co.xeon.web.rest.util.HeaderUtil;
import za.co.xeon.web.rest.util.PaginationUtil;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

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

    @PostConstruct
    public void init() {
        tmpDir = new File(mobileConf.getPodDirectory());
    }

    @RequestMapping(value = "/mobile/pods/{barcode}", method = RequestMethod.POST)
    @Timed
    public String scanDocument(@PathVariable(value="barcode") String barcode, @RequestParam("podDocument") MultipartFile podDocument) throws Exception {
        log.debug("Service : [POST} /mobile/pod - uploadPOD " + tmpDir.getAbsolutePath());
        String originalFileName = podDocument.getOriginalFilename().substring(0, podDocument.getOriginalFilename().indexOf("."));;
        String originalExtension = podDocument.getOriginalFilename().substring(podDocument.getOriginalFilename().indexOf(".")+1);
        log.debug(originalFileName + "." + originalExtension);

        File podFile = Converters.multipartToFile(tmpDir, originalFileName, originalExtension, podDocument);
        log.debug("\t[" + originalFileName + "] - received file and saved to tmp folder : " + tmpDir.getAbsolutePath());

        //create callable to continue long running process in different thread
        mobileService.submitPOD(barcode, podFile, originalExtension);
        return "\t[" + podFile.getName() + "] - document submitted for processing";
    }

    @RequestMapping(value = "/mobile/invoices/{deliveryNo}", method = RequestMethod.GET)
    @Timed
    public Callable<ResponseEntity<ByteArrayResource>> downloadInvoice(@PathVariable(value="deliveryNo") String deliveryNo) throws Exception {
        log.debug("Service : [GET} /mobile/invoices/" + deliveryNo);

        return new Callable<ResponseEntity<ByteArrayResource>>() {
            public ResponseEntity<ByteArrayResource> call() throws Exception {
                byte[] pdf = ftpService.download(deliveryNo);
                if(pdf != null) {
                    return ResponseEntity
                        .ok()
                        .contentLength(pdf.length)
                        .contentType(MediaType.parseMediaType("application/pdf"))
                        .body(new ByteArrayResource(pdf));
                }else{
                    String msg = String.format("Invoice for deliveryNo %s, could not be found on ftp server", deliveryNo);
                    log.info(msg);
                    return ResponseEntity
                        .badRequest()
                        .headers(HeaderUtil.createFailureAlert(msg))
                        .body(null);
                }
            }
        };

    }

    @RequestMapping(value = "/mobile/customers/{customerNumber}/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public Callable<List<EvResult>> getCustomerOrders(@PathVariable(value="customerNumber") String customerNumber,
                                            @RequestParam(value = "from") String from, @RequestParam(value = "to") String to,Pageable pageable) throws Exception {
        log.debug("Service [GET] /mobile/customer/" + customerNumber + "/orders");

        return () -> {
            User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
            Future<List<EvResult>> future;
            if(SecurityUtils.isUserCustomer()){
                log.debug("Restricting CustomerOrders lookup by user[" + user.getLogin() + "].company.sapId : " + user.getCompany().getSapId());
                future = mobileService.getCustomerOrders(user.getCompany().getSapId(),
                    new SimpleDateFormat("yyyy-MM-dd").parse(from), new SimpleDateFormat("yyyy-MM-dd").parse(to));

                Map<String, String> poMap = purchaseOrderRepository.findByUser(user).stream()
                    .filter(po -> po.getPoNumber() != null)
                    .collect(Collectors.toMap(PurchaseOrder::getPoNumber, PurchaseOrder::getPoNumber));

                return future.get().stream().filter(
                    ev -> poMap.containsKey(ev.getBstkd())
                ).collect(Collectors.toList());
            }else {
                future = mobileService.getCustomerOrders(customerNumber,
                    new SimpleDateFormat("yyyy-MM-dd").parse(from), new SimpleDateFormat("yyyy-MM-dd").parse(to));

                Map<String, String> poMap = purchaseOrderRepository.findByUserId_Company(companyRepository.findBySapId(customerNumber)).stream()
                    .filter(po -> po.getPoNumber() != null)
                    .collect(Collectors.toMap(PurchaseOrder::getPoNumber, PurchaseOrder::getPoNumber));

                return future.get().stream().filter(
                    ev -> poMap.containsKey(ev.getBstkd())
                ).collect(Collectors.toList());
            }
        };
    }

    @RequestMapping(value = "/mobile/pods/{barcode}/handlingunits", method = RequestMethod.GET)
    @Timed
    public Callable<List<Hunumbers>> getHandlingUnits(@PathVariable(value="barcode") String barcode) throws Exception {
        log.debug("Service [GET] /mobile/pod/" + barcode + "/handlingunits");
        return new Callable<List<Hunumbers>>() {
            public List<Hunumbers> call() throws Exception {
                return mobileService.getHandlingUnits(barcode);
            }
        };
    }

    @RequestMapping(value = "/mobile/pods/{barcode}/handlingunits", method = RequestMethod.PUT)
    @Timed
    public Callable<List<BapiRet2>> updateHandelingUnits(@PathVariable(value="barcode") String barcode, @RequestBody HandlingUnitUpdateDto handlingUnitUpdateDto) throws Exception {
        log.debug("Service [PUT] /mobile/pods/" + barcode + "/handlingunits");

        return new Callable<List<BapiRet2>>() {
            public List<BapiRet2> call() throws Exception {
                return mobileService.updateDeliveredHandelingUnits(barcode, handlingUnitUpdateDto);
            }
        };
    }

    @RequestMapping(value = "/mobile/pickups/{barcode}/handlingunits", method = RequestMethod.PUT)
    @Timed
    public Callable<List<BapiRet2>> pickupHandelingUnits(@PathVariable(value="barcode") String barcode, @RequestBody List<ImHuupdate> imHuupdates) throws Exception {
        log.debug("Service [PUT] /mobile/pickups/" + barcode + "/handlingunits");

        return new Callable<List<BapiRet2>>() {
            public List<BapiRet2> call() throws Exception {
                return mobileService.pickupHandelingUnits(barcode, imHuupdates);
            }
        };
    }

    @RequestMapping(value = "/mobile/pods/{barcode}/url", method = RequestMethod.PUT)
    @Timed
    public Callable<List<BapiRet2>> updatePod(@PathVariable(value="barcode") String barcode, @RequestBody String url) throws Exception {
        log.debug("Service [PUT] /mobile/pods/" + barcode + "/url - " + url);
        return new Callable<List<BapiRet2>>() {
            public List<BapiRet2> call() throws Exception {
                return mobileService.updatePod(barcode, url);
            }
        };
    }

    @RequestMapping(value = "/mobile/pods/{barcode}", method = RequestMethod.GET)
    @Timed
    public Callable<ResponseEntity<ByteArrayResource>> getPod(@PathVariable(value="barcode") String barcode) throws Exception {
        return new Callable<ResponseEntity<ByteArrayResource>>() {
            public ResponseEntity<ByteArrayResource> call() throws Exception {
                try {
                    // get your file as InputStream
                    byte[] img = mobileService.getPod(barcode);
                    if(img != null) {
                        return ResponseEntity
                            .ok()
                            .contentLength(img.length)
                            .contentType(MediaType.IMAGE_JPEG)
                            .body(new ByteArrayResource(img));
                    }else{
                        log.info(String.format("POD for barcode %s, could not be found on S3 bucket", barcode));
                        return ResponseEntity
                            .badRequest()
                            .body(null);
                    }
                } catch (IOException ex) {
                    log.error(String.format("Error writing POD to output stream. barcode was %s", barcode), ex);
                    return ResponseEntity
                        .badRequest()
                        .body(null);
                }
            }
        };
    }



}
