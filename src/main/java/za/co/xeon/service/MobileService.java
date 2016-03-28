package za.co.xeon.service;

import org.hibersap.bapi.BapiRet2;
import za.co.xeon.domain.dto.PurchaseOrderDto;
import za.co.xeon.external.as3.S3Service;
import za.co.xeon.external.as3.S3Settings;
import za.co.xeon.external.ocr.OcrService;
import za.co.xeon.external.ocr.OcrSettings;
import za.co.xeon.external.ocr.dto.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import za.co.xeon.external.sap.hibersap.dto.EvResult;
import za.co.xeon.external.sap.hibersap.HiberSapService;
import za.co.xeon.external.sap.hibersap.dto.Huitem;
import za.co.xeon.external.sap.hibersap.dto.Hunumbers;
import za.co.xeon.external.sap.hibersap.dto.ImHuitem;
import za.co.xeon.web.rest.dto.HandlingUnitDto;
import za.co.xeon.web.rest.dto.HandlingUnitUpdateDto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by derick on 2016/02/07.
 */
@Service
public class MobileService {
    private final static Logger log = LoggerFactory.getLogger(MobileService.class);

    @Autowired
    private OcrSettings ocrSettings;

    @Autowired
    private OcrService ocrService;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private S3Settings s3Settings;

    @Autowired
    private HiberSapService hiberSapService;

    @Async
    public void submitPOD(String barcode, File podFile, String extension){
        //first long running task - Submit document for processing
        Task task = ocrService.scanDocument(podFile.getPath()).getTask();

        //second long running task - Poll document for completion
        while(task.getStatus() != OcrSettings.TaskStatus.Completed){
            log.debug("\tsleeping for " + task.getEstimatedProcessingTime() + "s");
            try {
                Thread.sleep(Integer.parseInt(task.getEstimatedProcessingTime())*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            task = ocrService.getStatus(task.getId()).getTask();
        }

        //third long running task - get the barcode from the document sent in step 1
        String ocrBarcode = null;
        try {
            ocrBarcode = ocrService.getCompletedBarcodeResult(task.getResultUrl());
            if(barcode.equals(ocrBarcode)) {
                log.debug("\t====> Found barcode : " + ocrBarcode);

                //fourth long running task - upload POD to amazon S3
                String s3Path = s3Settings.getFolderPod() + "/" + ocrBarcode + "." + extension;
                String url = s3Service.uploadFile(s3Path, podFile);
                log.debug("\tSubmitted pod [barcode:" + ocrBarcode + "] to S3 successfully : " + s3Path + ". Now updating SAP with URL....");

                try {
                    hiberSapService.updatePod(ocrBarcode, url);
                    log.debug("\tSap update successful for pod " + ocrBarcode);
                    podFile.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                log.error("Invalid barcode match received. Called with [barcode:" + barcode + "] and ocr'ed [barcode:" + ocrBarcode + "]. Storing to S3 a mismached POD...");
                String s3Path = s3Settings.getFolderPod() + "/mismatched/" + ocrBarcode + "-vs-" + barcode + "." + extension;
                String url = s3Service.uploadFile(s3Path, podFile);
                log.error("\tSubmitted mismatched pod [barcode:" + ocrBarcode + " to S3 successfully : " + s3Path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Hunumbers> getHandlingUnits(String barcode) throws Exception{
        return hiberSapService.getHandelingUnits(barcode).getHunumbers();
    }

    public List<EvResult> getCustomerOrders(String customerNumber) throws Exception{
        return hiberSapService.getCustomerOrdersByDate(customerNumber);
    }

    public List<BapiRet2> updateDeliveredHandelingUnits(String barcode, HandlingUnitUpdateDto handlingUnitUpdateDto) throws Exception{
        return hiberSapService.updateDeliveredHandelingUnits(barcode, handlingUnitUpdateDto);
    }

    public List<BapiRet2> updatePod(String barcode, String url) throws Exception {
        return hiberSapService.updatePod(barcode, url);
    }
}
