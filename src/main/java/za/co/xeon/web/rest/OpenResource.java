package za.co.xeon.web.rest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import za.co.xeon.config.MobileConfiguration;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.external.as3.S3Service;
import za.co.xeon.external.ftp.FtpService;
import za.co.xeon.external.sap.hibersap.dto.GtCustOrdersDetail;
import za.co.xeon.repository.CompanyRepository;
import za.co.xeon.repository.PurchaseOrderRepository;
import za.co.xeon.repository.UserRepository;
import za.co.xeon.service.MailService;
import za.co.xeon.service.MobileService;
import za.co.xeon.web.rest.dto.Contact;
import za.co.xeon.web.rest.dto.OrderDetails;

/**
 * Created by derick on 2016/02/08.
 */

@RestController
@RequestMapping("/api")
// Max uploaded file size (here it is 20 MB)
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
	private MailService mailService;

	private static File tmpDir = null;

	@RequestMapping(value = "/open/orders/{deliveryNo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Callable<ResponseEntity<OrderDetails>> getCustomerOrderDetail(@PathVariable(value = "deliveryNo") String deliveryNo, Pageable pageable) throws Exception {
		log.debug("Service [GET] /mobile/orders/" + deliveryNo);
		return () -> {
			OrderDetails order = null;
			List<GtCustOrdersDetail> sapOrders = mobileService.getCustomerOrderDetails(deliveryNo, new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-01"), new Date()).get();
			if (!sapOrders.isEmpty()) {
				PurchaseOrder purchaseOrder = purchaseOrderRepository.findFirstByPoNumber(sapOrders.get(0).getBstkd());
				order = new OrderDetails(purchaseOrder, sapOrders);
			}
			return Optional.ofNullable(order).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		};
	}

	@RequestMapping(value = "/open/contact", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Callable<ResponseEntity> contact(@RequestBody Contact contact, HttpServletRequest request) throws Exception {
		log.debug("Service [POST] /mobile/contact");
		return () -> {
			mailService.sendCSUMail(userRepository.findAllByEnabledIsTrueAndCompany(companyRepository.findXeon()).get(0), String.format("Xeon Portal: New website enquiry for %s", contact.getAction().getDesc()), String.format("Enquiry from %s %s [email:%s] for %s : %s ", contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getAction().getDesc(), contact.getMessage()), null, null, getBaseUrl(request));
			return new ResponseEntity<>("", HttpStatus.OK);
		};
	}

	public String getBaseUrl(HttpServletRequest request) {
		return request.getScheme() + // "http"
				"://" + // "://"
				request.getServerName() + // "myhost"
				":" + // ":"
				request.getServerPort() + // "80"
				request.getContextPath(); // "/myContextPath" or "" if deployed
											// in root context
	}

}
