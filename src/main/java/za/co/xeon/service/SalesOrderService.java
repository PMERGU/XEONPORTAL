package za.co.xeon.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import za.co.xeon.external.sap.hibersap.HiberSapService;
import za.co.xeon.external.sap.hibersap.forge.hu.rfc.ZGetHandlingUnits;
import za.co.xeon.external.sap.hibersap.forge.od.dto.GtCustOrdersDetail;
import za.co.xeon.external.sap.hibersap.forge.so.dto.EtCustOrders;

@Service
public class SalesOrderService {

	@Autowired
	private HiberSapService hiberSapService;

	@Async
	public Future<List<EtCustOrders>> getCustomerOrdersNew(String type, String customerNumber, Date from, Date to) throws Exception {
		return new AsyncResult<List<EtCustOrders>>(hiberSapService.getCustomerOrdersByDateNew(type, customerNumber, from, to, true));
	}

	@Async
	public Future<List<GtCustOrdersDetail>> getCustomerOrderDetailNew(String deliveryNo, Date from, Date to) throws Exception {
		return new AsyncResult<List<GtCustOrdersDetail>>(hiberSapService.getCustomerOrderDetailNew(deliveryNo, from, to));
	}

	public ZGetHandlingUnits getHandlingUnitDetails(String barcode) throws Exception {
		return hiberSapService.getHandlingUnitDetails(barcode);
	}

}
