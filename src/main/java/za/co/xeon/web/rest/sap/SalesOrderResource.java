package za.co.xeon.web.rest.sap;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import za.co.xeon.domain.Attachment;
import za.co.xeon.domain.Comment;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.User;
import za.co.xeon.external.sap.hibersap.forge.hu.rfc.ZGetHandlingUnits;
import za.co.xeon.external.sap.hibersap.forge.od.dto.GtCustOrdersDetail;
import za.co.xeon.external.sap.hibersap.forge.so.dto.EtCustOrders;
import za.co.xeon.repository.AttachmentRepository;
import za.co.xeon.repository.CommentRepository;
import za.co.xeon.repository.CompanyRepository;
import za.co.xeon.repository.PurchaseOrderRepository;
import za.co.xeon.repository.UserRepository;
import za.co.xeon.security.SecurityUtils;
import za.co.xeon.service.SalesOrderService;
import za.co.xeon.web.rest.MobileResource;
import za.co.xeon.web.rest.dto.HandlingUnitDetails;
import za.co.xeon.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class SalesOrderResource {
	private final static Logger log = LoggerFactory.getLogger(MobileResource.class);
	@Inject
	private CompanyRepository companyRepository;
	@Inject
	private SalesOrderService salesOrderService;
	@Inject
	private PurchaseOrderRepository purchaseOrderRepository;
	@Inject
	private UserRepository userRepository;
	@Inject
	private CommentRepository commentRepository;
	@Inject
	private AttachmentRepository attachmentRepository;

	@SuppressWarnings("unused")
	@RequestMapping(value = "/so/{customerNumber}/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Callable<List<EtCustOrders>> getCustomerOrdersNew(@RequestParam(value = "type") String type, @PathVariable(value = "customerNumber") String customerNumber, @RequestParam(value = "from") String from, @RequestParam(value = "to") String to, Pageable pageable) throws Exception {
		log.debug("Service [GET] /mobile/customer/" + customerNumber + "/orders (new one)");

		return () -> {
			User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
			Future<List<EtCustOrders>> future;
			if (SecurityUtils.isUserCustomer()) {
				log.debug("Restricting CustomerOrders lookup by user[" + user.getLogin() + "].company.sapId : " + user.getCompany().getSapId());
				future = salesOrderService.getCustomerOrdersNew(type, user.getCompany().getSapId(), new SimpleDateFormat("yyyy-MM-dd").parse(from), new SimpleDateFormat("yyyy-MM-dd").parse(to));

				if (SecurityUtils.isUserCustomerCSU()) {
					Map<String, String> poMap = purchaseOrderRepository.findByUserId_Company(user.getCompany()).stream().filter(po -> po.getPoNumber() != null).collect(Collectors.toMap(PurchaseOrder::getPoNumber, PurchaseOrder::getPoNumber));
					return future.get().stream().collect(Collectors.toList());
				} else {
					Map<String, String> poMap = purchaseOrderRepository.findByUser(user).stream().filter(po -> po.getPoNumber() != null).collect(Collectors.toMap(PurchaseOrder::getPoNumber, PurchaseOrder::getPoNumber));
					return future.get().stream().collect(Collectors.toList());
				}

			} else {
				future = salesOrderService.getCustomerOrdersNew(type, customerNumber, new SimpleDateFormat("yyyy-MM-dd").parse(from), new SimpleDateFormat("yyyy-MM-dd").parse(to));
				Map<String, String> poMap = purchaseOrderRepository.findByUserId_Company(companyRepository.findBySapId(customerNumber)).stream().filter(po -> po.getPoNumber() != null).collect(Collectors.toMap(PurchaseOrder::getPoNumber, PurchaseOrder::getPoNumber));
				return future.get().stream().collect(Collectors.toList());
			}
		};
	}

	@RequestMapping(value = "/so/orders/{deliveryNo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Callable<ResponseEntity<List<GtCustOrdersDetail>>> getPoOrder(@PathVariable(value = "deliveryNo") String deliveryNo, Pageable pageable) throws Exception {
		log.debug("[PO:{}] - Service [GET] /purchaseOrders/{}/orders/{}", deliveryNo);
		return () -> {
			List<GtCustOrdersDetail> sapOrders = null;
			{
				sapOrders = salesOrderService.getCustomerOrderDetailNew(deliveryNo, new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-01"), new Date()).get();
				if (sapOrders.isEmpty()) {
					log.debug("[PO:{}] - Service [GET] /purchaseOrders/{}/orders/{} - could not find sap orders", deliveryNo);
				}
			}
			return Optional.ofNullable(sapOrders).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		};
	}

	@RequestMapping(value = "/so/huDetails/{deliveryNo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Callable<ResponseEntity<HandlingUnitDetails>> getHuDetails(@PathVariable(value = "deliveryNo") String deliveryNo, Pageable pageable) throws Exception {
		log.debug("[PO:{}] - Service [GET] /purchaseOrders/{}/orders/{}", deliveryNo);
		return () -> {
			if (SecurityUtils.isUserXeonOrAdmin()) {
				HandlingUnitDetails hud = null;
				{
					ZGetHandlingUnits rfc = salesOrderService.getHandlingUnitDetails(deliveryNo);
					hud = new HandlingUnitDetails(rfc.get_huheader(), rfc.get_huitem(), rfc.get_hunumbers());
					if (hud.getHuheader().isEmpty()) {
						log.debug("[PO:{}] - Service [GET] /purchaseOrders/{}/orders/{} - could not find sap orders", deliveryNo);
					}
				}
				return Optional.ofNullable(hud).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
			} else {
				log.debug("[PO:{}] - Limiting huDetails due to not CSU or Admin", deliveryNo);
				return new ResponseEntity<>(new HandlingUnitDetails(), HttpStatus.OK);
			}
		};
	}

	/**
	 * GET /poLines -> get all the poLines.
	 */
	@RequestMapping(value = "/so/{poNumber}/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<Comment>> getAllPoComments(@PathVariable String poNumber, Pageable pageable) throws URISyntaxException {
		log.debug("[PO:{}] - REST request to get a page of comments", poNumber);
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findFirstByPoNumber(poNumber);
		Page<Comment> page = null;
		if (!(SecurityUtils.isUserXeonOrAdmin())) {
			page = commentRepository.findByInternalIsFalseAndPurchaseOrder(purchaseOrder, pageable);
		} else {
			page = commentRepository.findByPurchaseOrder(purchaseOrder, pageable);
		}
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/so/comments");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /attachments -> get all the attachment.
	 */
	@RequestMapping(value = "/so/{poNumber}/attachments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<Attachment>> getAllPoAttachments(@PathVariable String poNumber, Pageable pageable) throws URISyntaxException {
		log.debug("[PO:{}] - REST request to get a page of attachments", poNumber);
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findFirstByPoNumber(poNumber);
		Page<Attachment> page = attachmentRepository.findByPurchaseOrder(purchaseOrder, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/so/attachments");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
	
	
	/**
	 * GET /purchaseOrders/:id -> get the "id" purchaseOrder.
	 */
	@RequestMapping(value = "/so/{poNumber}/po", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<PurchaseOrder> getPurchaseOrder(@PathVariable String poNumber) {
		log.debug("[PO:{}] - REST request to get PurchaseOrder",poNumber);
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findFirstByPoNumber(poNumber + "");
		if (!(SecurityUtils.isUserXeonOrAdmin())) {
			User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
//			if (purchaseOrder.getUser().getCompany().getId() != user.getCompany().getId()) {
//				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//			}
		}
		return Optional.ofNullable(purchaseOrder).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
