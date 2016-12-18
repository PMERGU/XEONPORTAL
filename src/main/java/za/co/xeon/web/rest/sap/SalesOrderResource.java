package za.co.xeon.web.rest.sap;

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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.User;
import za.co.xeon.external.sap.hibersap.forge.od.dto.GtCustOrdersDetail;
import za.co.xeon.external.sap.hibersap.forge.so.dto.EtCustOrders;
import za.co.xeon.repository.CompanyRepository;
import za.co.xeon.repository.PurchaseOrderRepository;
import za.co.xeon.repository.UserRepository;
import za.co.xeon.security.SecurityUtils;
import za.co.xeon.service.SalesOrderService;
import za.co.xeon.web.rest.MobileResource;

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
}
