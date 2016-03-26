package za.co.xeon.service;

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

import java.io.File;
import java.util.List;

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
    public void submitPOD(File podFile, String extension) throws Exception {
        //first long running task - Submit document for processing
        Task task = ocrService.scanDocument(podFile.getPath()).getTask();

        //second long running task - Poll document for completion
        while(task.getStatus() != OcrSettings.TaskStatus.Completed){
            log.debug("\tsleeping for " + task.getEstimatedProcessingTime() + "s");
            Thread.sleep(Integer.parseInt(task.getEstimatedProcessingTime())*100);
            task = ocrService.getStatus(task.getId()).getTask();
        }

        //third long running task - get the barcode from the document sent in step 1
        String barcode = ocrService.getCompletedBarcodeResult(task.getResultUrl());
        log.debug("\t====> Found barcode : " + barcode);

        String s3Path = s3Settings.getFolderPod() + "/" + barcode + "." + extension;

        //fourth long running task - upload POD to amazon S3
        s3Service.uploadFile(s3Path, podFile);
        log.debug("\tSubmitted pod " + barcode + " to S3 successfully : " + s3Path);
        podFile.delete();
    }

    public List<Hunumbers> getHandlingUnits(String barcode) throws Exception{
        return hiberSapService.getHandelingUnits(barcode).getHunumbers();
    }

    public List<PurchaseOrderDto> getCustomerOrders(String customerNumber) throws Exception{
        return hiberSapService.getCustomerOrdersByDate(customerNumber);
    }

    public void updateDeliveredHandelingUnits(String barcode, List<Hunumbers> handlingUnits) throws Exception{
//        return hiberSapService.updateDeliveredHandelingUnits(barcode, handlingUnits);
    }

}
