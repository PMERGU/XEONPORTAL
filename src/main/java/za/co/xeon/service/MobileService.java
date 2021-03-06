package za.co.xeon.service;

import static java.lang.String.format;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import org.hibersap.bapi.BapiRet2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import za.co.xeon.config.MobileConfiguration;
import za.co.xeon.domain.Attachment;
import za.co.xeon.domain.User;
import za.co.xeon.external.as3.S3Service;
import za.co.xeon.external.as3.S3Settings;
import za.co.xeon.external.ocr.OcrService;
import za.co.xeon.external.ocr.OcrSettings;
import za.co.xeon.external.ocr.dto.Task;
import za.co.xeon.external.sap.hibersap.HiberSapService;
import za.co.xeon.external.sap.hibersap.dto.GtCustOrders;
import za.co.xeon.external.sap.hibersap.dto.GtCustOrdersDetail;
import za.co.xeon.external.sap.hibersap.dto.Hunumbers;
import za.co.xeon.external.sap.hibersap.dto.ImHuupdate;
import za.co.xeon.external.sap.hibersap.forge.hu.rfc.ZGetHandlingUnits;
import za.co.xeon.external.sap.hibersap.forge.so.dto.EtCustOrders;
import za.co.xeon.web.rest.dto.HandlingUnitDetails;
import za.co.xeon.web.rest.dto.HandlingUnitUpdateDto;

/**
 * Created by derick on 2016/02/07.
 */
@Service
@SuppressWarnings("unused")
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

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private MobileConfiguration mobileConfiguration;

	@Async
	public void submitPOD(String barcode, File podFile, String contentType, String originalExtension, User user) {
		// first long running task - Submit document for processing
		Task task = ocrService.scanDocument(podFile.getPath()).getTask();

		// second long running task - Poll document for completion
		while (task.getStatus() != OcrSettings.TaskStatus.Completed) {
			log.debug("\tsleeping for " + task.getEstimatedProcessingTime() + "s");
			try {
				Thread.sleep(Integer.parseInt(task.getEstimatedProcessingTime()) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			task = ocrService.getStatus(task.getId()).getTask();
		}

		// third long running task - get the barcode from the document sent in
		// step 1
		String ocrBarcode = null;
		try {
			ocrBarcode = ocrService.getCompletedBarcodeResult(task.getResultUrl());

			ocrBarcode = org.apache.commons.lang.StringUtils.leftPad(ocrBarcode, 10, "0");
			barcode = org.apache.commons.lang.StringUtils.leftPad(barcode, 10, "0");
			log.debug("Padding both barcodes to 10 length [ocrBarcode:" + ocrBarcode + "] - [barcode:" + barcode + "]");

			if (ocrBarcode == null || barcode.equals(ocrBarcode)) {
				log.debug("\t====> Found barcode : " + ocrBarcode);
				if (ocrBarcode == null) {
					log.error("\t Failed to get OCR barcode from POD, using barcode passed in : " + barcode);
				}

				// fourth long running task - upload POD to amazon S3
				Attachment podAttachment = attachmentService.createAttachment(barcode, "POD scanned via mobile scanner", "POD", contentType, true, podFile, user);
				String url = mobileConfiguration.getHttpServerName() + format("api/attachments/%s", podAttachment.getUuid());
				log.debug("\t[" + barcode + "] - Submitted pod to S3 successfully : " + podAttachment.getFileName() + ". Now updating SAP with URL [" + url + "]");

				try {
					hiberSapService.updatePod(barcode, url);
					log.debug("\tSap update successful for pod " + barcode);
					podFile.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				log.error("Invalid barcode match received. Called with [barcode:" + barcode + "] and ocr'ed [barcode:" + ocrBarcode + "]. Storing to S3 a mismached POD...");
				String s3Path = s3Settings.getFolderPod() + "/mismatched/" + ocrBarcode + "-vs-" + barcode + "." + originalExtension;
				s3Service.uploadFile(s3Path, podFile);
				log.error("\tSubmitted mismatched pod [barcode:" + ocrBarcode + " to S3 successfully : ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Hunumbers> getHandlingUnits(String barcode) throws Exception {
		return hiberSapService.getHandelingUnits(barcode).getHunumbers();
	}

	public ZGetHandlingUnits getHandlingUnitDetails(String barcode) throws Exception {
		return hiberSapService.getHandlingUnitDetails(barcode);
	}

	@Async
	public Future<List<GtCustOrders>> getCustomerOrders(String customerNumber, Date from, Date to) throws Exception {
		return new AsyncResult<List<GtCustOrders>>(hiberSapService.getCustomerOrdersByDate(customerNumber, from, to, true));
	}

	@Async
	public Future<List<EtCustOrders>> getCustomerOrdersNew(String type, String customerNumber, Date from, Date to) throws Exception {
		return new AsyncResult<List<EtCustOrders>>(hiberSapService.getCustomerOrdersByDateNew(type, customerNumber, from, to, true));
	}

	@Async
	public Future<List<GtCustOrdersDetail>> getCustomerOrderDetails(String deliveryNo, Date from, Date to) throws Exception {
		return new AsyncResult<List<GtCustOrdersDetail>>(hiberSapService.getCustomerOrderDetails(deliveryNo, from, to));
	}

	public List<BapiRet2> updateDeliveredHandelingUnits(String barcode, HandlingUnitUpdateDto handlingUnitUpdateDto) throws Exception {
		return hiberSapService.updateDeliveredHandelingUnits(barcode, handlingUnitUpdateDto);
	}

	public List<BapiRet2> pickupHandelingUnits(String barcode, List<ImHuupdate> imHuupdates) throws Exception {
		return hiberSapService.pickupHandelingUnits(barcode, imHuupdates);
	}

	public List<BapiRet2> updatePod(String barcode, String url) throws Exception {
		return hiberSapService.updatePod(barcode, url);
	}

	public byte[] getPod(String barcode) throws Exception {
		barcode = org.apache.commons.lang.StringUtils.leftPad(barcode, 10, "0");
		String s3Path = s3Settings.getFolderPod() + "/" + barcode + ".jpg";
		log.debug("Trying to retrieve pod " + s3Path);
		return s3Service.retrieveFile(s3Path);
	}
}
