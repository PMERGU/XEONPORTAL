package za.co.xeon.external.sap.hibersap;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.hibersap.bapi.BapiRet2;
import org.hibersap.configuration.AnnotationConfiguration;
import org.hibersap.configuration.xml.SessionManagerConfig;
import org.hibersap.execution.jco.JCoContext;
import org.hibersap.session.Session;
import org.hibersap.session.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.conn.jco.ext.DestinationDataProvider;

import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.User;
import za.co.xeon.domain.enumeration.PoState;
import za.co.xeon.domain.enumeration.ServiceType;
import za.co.xeon.external.sap.SapSettings;
import za.co.xeon.external.sap.hibersap.dto.ExReturn;
import za.co.xeon.external.sap.hibersap.dto.GtCustOrders;
import za.co.xeon.external.sap.hibersap.dto.GtCustOrdersDetail;
import za.co.xeon.external.sap.hibersap.dto.ImHuitem;
import za.co.xeon.external.sap.hibersap.dto.ImHuupdate;
import za.co.xeon.external.sap.hibersap.errors.ValidSapException;
import za.co.xeon.external.sap.hibersap.forge.hu.rfc.ZGetHandlingUnits;
import za.co.xeon.external.sap.hibersap.forge.ior.rfc.ZOrdersReport;
import za.co.xeon.external.sap.hibersap.forge.od.rfc.ZGetCustOrdersDetail;
import za.co.xeon.external.sap.hibersap.forge.so.dto.EtCustOrders;
import za.co.xeon.external.sap.hibersap.forge.so.dto.ImAuart;
import za.co.xeon.external.sap.hibersap.forge.so.dto.ImDateR;
import za.co.xeon.external.sap.hibersap.forge.so.rfc.ZGetCustOrdersByDateNew;
import za.co.xeon.external.sap.hibersap.forge.sr.dto.SLgtyp;
import za.co.xeon.external.sap.hibersap.forge.sr.dto.SMatkl;
import za.co.xeon.external.sap.hibersap.forge.sr.dto.SMatnr;
import za.co.xeon.external.sap.hibersap.forge.sr.dto.SWerks;
import za.co.xeon.external.sap.hibersap.forge.sr.dto.StockInventory;
import za.co.xeon.external.sap.hibersap.forge.sr.rfc.ZStockInventory;
import za.co.xeon.repository.PurchaseOrderRepository;
import za.co.xeon.repository.UserRepository;
import za.co.xeon.service.util.Pad;
import za.co.xeon.web.rest.dto.HandlingUnitDto;
import za.co.xeon.web.rest.dto.HandlingUnitUpdateDto;
import za.co.xeon.web.rest.dto.SalesOrderCreatedDTO;
import za.co.xeon.web.rest.dto.StockReportDTO;

/**
 * Created by derick on 2016/02/07.
 */
@Service
public class HiberSapService {
	private final static Logger log = LoggerFactory.getLogger(HiberSapService.class);

	@Autowired
	private SapSettings s3Settings;

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	private UserRepository userRepository;

	private SessionManager sessionManager;

	@PostConstruct
	public void init() {
		log.debug("SapService, post construct " + s3Settings.getAshost());
		SessionManagerConfig cfg = new SessionManagerConfig("A12").setContext(JCoContext.class.getName())
				.setProperty(DestinationDataProvider.JCO_ASHOST, s3Settings.getAshost())
				.setProperty(DestinationDataProvider.JCO_SYSNR, s3Settings.getSysnr())
				.setProperty(DestinationDataProvider.JCO_CLIENT, s3Settings.getClient())
				.setProperty(DestinationDataProvider.JCO_USER, s3Settings.getUser())
				.setProperty(DestinationDataProvider.JCO_PASSWD, s3Settings.getPasswd())
				.setProperty(DestinationDataProvider.JCO_LANG, s3Settings.getLang())
				.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, s3Settings.getPoolCapacity())
				.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, s3Settings.getPeakLimit());

		AnnotationConfiguration configuration = new AnnotationConfiguration(cfg);
		configuration.addBapiClasses(CustOrdersRFC.class, CustOrdersDetailRFC.class, CustomerOrdersByDateRFC.class,
				HandlingUnitsRFC.class, ReceivedHandlingUnitsRFC.class, SalesOrderCreateRFC.class,
				UpdateHandlingUnitsRFC.class, ZStockInventory.class, UpdatePodRFC.class, ZGetCustOrdersByDateNew.class,
				ZOrdersReport.class, ZGetCustOrdersDetail.class, ZGetHandlingUnits.class);
		sessionManager = configuration.buildSessionManager();
	}

	public List<GtCustOrders> getCustomerOrdersByDate(String customerNumber, Date from, Date to, boolean evict)
			throws ParseException {
		customerNumber = Pad.left(customerNumber, 10);
		Session session = sessionManager.openSession();
		try {
			List<za.co.xeon.external.sap.hibersap.dto.ImDateR> dateRange = new ArrayList<>();
			dateRange.add(new za.co.xeon.external.sap.hibersap.dto.ImDateR("I", "BT", from, to));
			CustOrdersRFC rfc = new CustOrdersRFC(customerNumber, dateRange, "B");
			session.execute(rfc);

			return rfc.getGtCustOrders();
		} catch (Exception e) {
			log.error("Couldnt complete getCustomerOrdersByDateNew : + " + e.getMessage(), e);
			throw e;
		} finally {
			session.close();
		}
	}

	public List<za.co.xeon.external.sap.hibersap.forge.od.dto.GtCustOrdersDetail> getCustomerOrderDetailNew(
			String orderNumber, Date from, Date to) throws ParseException {
		Session session = sessionManager.openSession();
		try {
			List<za.co.xeon.external.sap.hibersap.forge.od.dto.ImDateR> dateRange = new ArrayList<za.co.xeon.external.sap.hibersap.forge.od.dto.ImDateR>();
			dateRange.add(new za.co.xeon.external.sap.hibersap.forge.od.dto.ImDateR("I", from, to, "BT"));
			ZGetCustOrdersDetail rfc = new ZGetCustOrdersDetail(dateRange, orderNumber);
			session.execute(rfc);
			List<za.co.xeon.external.sap.hibersap.forge.od.dto.GtCustOrdersDetail> ret = rfc.get_gtCustOrdersDetail();
			for (za.co.xeon.external.sap.hibersap.forge.od.dto.GtCustOrdersDetail det : ret) {
				PurchaseOrder po = new PurchaseOrder();
				po.setPoNumber(det.get_bstkd());
				po.setState(PoState.PROCESSED);
				po.setReference(StringUtils.left(det.get_bstkd(), 10));
				po.setCollective(StringUtils.left(det.get_bstkd(), 10));
				po.setAccountReference(StringUtils.left(det.get_bstkd(), 10));
				po.setSoNumber(det.get_vbeln());
				po.setServiceType(getBySapCode(det.get_auart()));
				Optional<User> user = userRepository.findOneByFcSapId(det.get_parvwYe());
				po.setUser((user.isPresent() ? user.get() : null));
				if (purchaseOrderRepository.findFirstByPoNumber(det.get_bstkd()) == null)
					purchaseOrderRepository.save(po);
			}
			return ret;
		} catch (Exception e) {
			log.error("Couldnt complete getCustOrderDetailNew : + " + e.getMessage(), e);
			throw e;
		} finally {
			session.close();
		}
	}

	private ServiceType getBySapCode(String auart) {
		ServiceType ret = null;
		if (StringUtils.equalsIgnoreCase(ServiceType.INBOUND.getSapCode(), auart))
			ret = ServiceType.INBOUND;
		else if (StringUtils.equalsIgnoreCase(ServiceType.OUTBOUND.getSapCode(), auart))
			ret = ServiceType.OUTBOUND;
		else if (StringUtils.equalsIgnoreCase(ServiceType.FULL_CONTAINER_LOAD.getSapCode(), auart))
			ret = ServiceType.FULL_CONTAINER_LOAD;
		else if (StringUtils.equalsIgnoreCase(ServiceType.FULL_TRUCK_LOAD.getSapCode(), auart))
			ret = ServiceType.FULL_TRUCK_LOAD;
		else if (StringUtils.equalsIgnoreCase(ServiceType.CROSS_HAUL.getSapCode(), auart))
			ret = ServiceType.CROSS_HAUL;
		else if (StringUtils.equalsIgnoreCase(ServiceType.BREAKBULK_TRANSPORT.getSapCode(), auart))
			ret = ServiceType.BREAKBULK_TRANSPORT;
		return ret;
	}

	public List<GtCustOrdersDetail> getCustomerOrderDetails(String orderNumber, Date from, Date to)
			throws ParseException {
		orderNumber = Pad.left(orderNumber, 10);
		Session session = sessionManager.openSession();
		try {
			List<za.co.xeon.external.sap.hibersap.dto.ImDateR> dateRange = new ArrayList<>();
			dateRange.add(new za.co.xeon.external.sap.hibersap.dto.ImDateR("I", "BT", from, to));
			CustOrdersDetailRFC rfc = new CustOrdersDetailRFC(dateRange, orderNumber);
			session.execute(rfc);

			return rfc.getGtCustOrdersDetail();
		} catch (Exception e) {
			log.error("Couldnt complete getCustomerOrderDetails : + " + e.getMessage(), e);
			throw e;
		} finally {
			session.close();
		}
	}

	public HandlingUnitsRFC getHandelingUnits(String barcode) {
		barcode = Pad.left(barcode, 10);
		Session session = sessionManager.openSession();
		try {
			HandlingUnitsRFC rfc = new HandlingUnitsRFC(barcode);
			session.execute(rfc);
			rfc.getHunumbers().stream()
					.forEach(hunumbers -> hunumbers.setHuExid(Long.valueOf(hunumbers.getHuExid()).toString()));
			return rfc;
		} catch (Exception e) {
			log.error("Couldnt complete getHandelingUnits : + " + e.getMessage(), e);
			throw e;
		} finally {
			session.close();
		}
	}

	public ZGetHandlingUnits getHandlingUnitDetails(String barcode) {
		barcode = Pad.left(barcode, 10);
		Session session = sessionManager.openSession();
		try {
			ZGetHandlingUnits rfc = new ZGetHandlingUnits(barcode);
			session.execute(rfc);
			return rfc;
		} catch (Exception e) {
			log.error("Couldnt complete getHandelingUnits : + " + e.getMessage(), e);
			throw e;
		} finally {
			session.close();
		}
	}

	public List<BapiRet2> updateDeliveredHandelingUnits(String barcode, HandlingUnitUpdateDto handlingUnitUpdateDto)
			throws Exception {
		barcode = Pad.left(barcode, 10);
		log.debug(String.format("[%s] - updateDeliveredHandelingUnits", barcode));
		Session session = sessionManager.openSession();
		try {
			List<ImHuitem> imHuitems = new ArrayList<>();
			log.debug(String.format("[%s] - hu count returned : %s", barcode,
					handlingUnitUpdateDto.getHandlingUnits().size()));
			for (HandlingUnitDto dto : handlingUnitUpdateDto.getHandlingUnits()) {
				log.debug(
						String.format("[%s] - ImHuitem.getHuExid(): %s", barcode, Pad.left(dto.getHandlingUnit(), 20)));
				imHuitems.add(new ImHuitem(barcode, Pad.left(dto.getHandlingUnit(), 20),
						handlingUnitUpdateDto.getDate(), handlingUnitUpdateDto.getDate()));
			}
			;
			if (imHuitems.size() == 0) {
				log.debug(String.format(
						"[%s] - hu count 0, so making a new imHuitem record with just barcode....nothing else...damn you fairwell!!!",
						barcode));
				imHuitems.add(new ImHuitem(barcode, "", new Date(), new Date()));
			}
			UpdateHandlingUnitsRFC rfc = new UpdateHandlingUnitsRFC(imHuitems);
			session.execute(rfc);

			if (rfc.getReturn().isEmpty()) {
				return new ArrayList<>();
			} else if (rfc.getReturn().get(0).getType() == 'E') {
				throw new Exception("Request [barcode:" + barcode + "] failed with SAP status code "
						+ rfc.getReturn().get(0).getType() + " : " + rfc.getReturn().get(0).getMessage());
			} else {
				return rfc.getReturn();
			}
		} catch (Exception e) {
			log.error("Couldnt complete updateDeliveredHandelingUnits : + " + e.getMessage(), e);
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}

	public List<BapiRet2> pickupHandelingUnits(String barcode, List<ImHuupdate> imHuitems) throws Exception {
		barcode = Pad.left(barcode, 10);
		log.debug(String.format("[%s] - pickupHandelingUnits called with %s hu`s", barcode, imHuitems.size()));
		Session session = sessionManager.openSession();
		try {
			imHuitems.stream().forEach(imHuupdate -> imHuupdate.setExidv(Pad.left(imHuupdate.getExidv(), 20)));
			if (log.isDebugEnabled()) {
				for (ImHuupdate tmp : imHuitems) {
					log.debug(String.format("[%s] - imHuupdate.getExidv(): %s, imHuupdate.getExtIdHu2(): %s", barcode,
							tmp.getExidv(), tmp.getExtIdHu2()));
				}
			}
			ReceivedHandlingUnitsRFC rfc = new ReceivedHandlingUnitsRFC(imHuitems);
			session.execute(rfc);

			if (rfc.getReturn().isEmpty()) {
				return new ArrayList<>();
			} else if (rfc.getReturn().get(0).getType() == 'E') {
				throw new Exception("Request [barcode:" + barcode + "] failed with SAP status code "
						+ rfc.getReturn().get(0).getType() + " : " + rfc.getReturn().get(0).getMessage());
			} else {
				return rfc.getReturn();
			}
		} catch (Exception e) {
			log.error("Couldnt complete pickupHandelingUnits : + " + e.getMessage(), e);
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}

	public List<BapiRet2> updatePod(String barcode, String url) throws Exception {
		barcode = Pad.left(barcode, 10);
		Session session = sessionManager.openSession();
		try {
			UpdatePodRFC rfc = new UpdatePodRFC(barcode, url);
			session.execute(rfc);
			if (rfc.getReturn().size() > 0 && rfc.getReturn().get(0).getType() == 'E') {
				throw new Exception("Request [barcode:" + barcode + "] failed with SAP status code "
						+ rfc.getReturn().get(0).getType() + " : " + rfc.getReturn().get(0).getMessage());
			} else {
				return rfc.getReturn();
			}
		} catch (Exception e) {
			log.error("Couldn't complete updatePod : " + e.getMessage(), e);
			throw e;
		} finally {
			session.close();
		}
	}

	public SalesOrderCreatedDTO createSalesOrder(String po, SalesOrderCreateRFC rfc) throws Exception {
		log.debug("[{}] - createSalesOrder", po);
		Session session = sessionManager.openSession();
		try {
			session.execute(rfc);
			log.debug("[{}] - SO created : {}", po, rfc.getExSalesorder());

			if (rfc.getExReturn().size() > 0 && rfc.getExReturn().get(0).getType().equals("E")) {
				throw new Exception("Request [po:" + po + "] failed with SAP status code "
						+ rfc.getExReturn().get(0).getType() + " : " + rfc.getExReturn().get(0).getMessage());
			} else {
				log.debug("[{}] - Return count : [{}]", po, rfc.getExReturn().size());
				log.debug("[{}] - return", po);
				rfc.getExReturn().stream()
						.forEach(exReturn -> log.debug("[{}] - return : \n{}", po, exReturn.toString()));
				for (ExReturn exReturn : rfc.getExReturn()) {
					if (exReturn.getType().equals("E") || exReturn.getType().equals("I")) {
						if (exReturn.getType().equals("I") && exReturn.getMessage()
								.startsWith("Purchase order number " + po + " already exists")) {
							throw new ValidSapException("Duplicate PO [" + po + "], please fix", exReturn);
						} else if (exReturn.getType().equals("I") && exReturn.getMessage()
								.startsWith("Mode of transport does not match Finance Controller")) {
							throw new ValidSapException("Invalid mode of transport selected, please fix", exReturn);
						} else {
							throw new Exception("Request [po:" + po + "] failed with SAP status code "
									+ exReturn.getType() + " : " + exReturn.getMessage());
						}
					}
				}
				return new SalesOrderCreatedDTO(rfc.getExSalesorder(), rfc.getExReturn());
			}
		} catch (Exception e) {
			log.error("Couldn't complete createSalesOrder : " + e.getMessage(), e);
			throw e;
		} finally {
			session.close();
		}
	}

	public List<StockInventory> fetchStockData(StockReportDTO dto) throws Exception {
		log.debug("[{}] - fetchStockData", dto);
		Session session = sessionManager.openSession();
		try {
			ZStockInventory rfc = new ZStockInventory();
			if (dto != null) {
				if (dto.getCompany() != null && dto.getCompany().length() != 0) {
					SMatkl par = new SMatkl();
					par.set_sign("I");
					par.set_option("EQ");
					par.set_low(dto.getCompany().toUpperCase());
					List<SMatkl> parList = new ArrayList<SMatkl>();
					parList.add(par);
					rfc.set_sMatkl(parList);
				} else if (dto.getmName() != null && dto.getmName().length() == 0) {
					SMatnr par = new SMatnr();
					par.set_sign("I");
					par.set_option("EQ");
					par.set_low(dto.getmName().toUpperCase());
					List<SMatnr> parList = new ArrayList<SMatnr>();
					parList.add(par);
					rfc.set_sMatnr(parList);
				} else if (dto.getPlantNo() != null && dto.getPlantNo().length() == 0) {
					SWerks par = new SWerks();
					par.set_sign("I");
					par.set_option("EQ");
					par.set_low(dto.getPlantNo().toUpperCase());
					List<SWerks> parList = new ArrayList<SWerks>();
					parList.add(par);
					rfc.set_sWerks(parList);
				} else if (dto.getsType() != null && dto.getsType().length() == 0) {
					SLgtyp par = new SLgtyp();
					par.set_sign("I");
					par.set_option("EQ");
					par.set_low(dto.getsType().toUpperCase());
					List<SLgtyp> parList = new ArrayList<SLgtyp>();
					parList.add(par);
					rfc.set_sLgtyp(parList);
				} else
					throw new Exception("Cannot execute without any parameters");
				session.execute(rfc);
				return rfc.get_stockInventory();
			}
			throw new Exception("Cannot execute without any parameters");
		} catch (Exception e) {
			log.error("Couldn't complete createSalesOrder : " + e.getMessage(), e);
			throw e;
		} finally {
			session.close();
		}
	}

	public List<EtCustOrders> getCustomerOrdersByDateNew(String type, String customerNumber, Date from, Date to,
			boolean evict) throws ParseException {
		customerNumber = Pad.left(customerNumber, 10);
		Session session = sessionManager.openSession();
		try {
			List<ImDateR> dateRange = new ArrayList<ImDateR>();
			List<ImAuart> auart =  new ArrayList<ImAuart>();
			dateRange.add(new ImDateR("I", from, to, "BT"));
			if(type!=null && !type.equalsIgnoreCase("") )
			{
				auart= new ArrayList<ImAuart>();
				
				auart.add(new ImAuart("I", "EQ", type!="" ? type : null , null));
			}
			ZGetCustOrdersByDateNew rfc = new ZGetCustOrdersByDateNew(auart, dateRange, null, customerNumber);
			session.execute(rfc);

			return rfc.get_etCustOrders();
		} catch (Exception e) {
			log.error("Couldnt complete getCustomerOrdersByDateNew : + " + e.getMessage(), e);
			throw e;
		} finally {
			session.close();
		}
	}

	public List<EtCustOrders> getCustomerOrdersForPOD(String sapId, Date from, Date to, String podStatus, String type)
			throws ParseException {
		sapId = Pad.left(sapId, 10);
		Session session = sessionManager.openSession();
		try {
			List<ImDateR> dateRange = new ArrayList<ImDateR>();
			dateRange.add(new ImDateR("I", from, to, "BT"));
			List<ImAuart> auart =  new ArrayList<ImAuart>();
			if(type!=null && !type.equalsIgnoreCase("") )
			{
				auart= new ArrayList<ImAuart>();
				auart.add(new ImAuart("I", "EQ", type!="" ? type : null , null));
			}
			
			ZGetCustOrdersByDateNew rfc = new ZGetCustOrdersByDateNew(auart, dateRange, podStatus, sapId);
			session.execute(rfc);
			return rfc.get_etCustOrders();
		} catch (Exception e) {
			log.error("Couldnt complete getCustomerOrdersByDateNew : + " + e.getMessage(), e);
			throw e;
		} finally {
			session.close();
		}
	}

	public List<za.co.xeon.external.sap.hibersap.forge.ior.dto.EtCustOrders> getCustomerOrdersForIOR(String sapId,
			Date from, Date to, String podStatus) throws ParseException {
		Session session = sessionManager.openSession();
		try {
			List<za.co.xeon.external.sap.hibersap.forge.ior.dto.ImDateR> dateRange = new ArrayList<za.co.xeon.external.sap.hibersap.forge.ior.dto.ImDateR>();
			dateRange.add(new za.co.xeon.external.sap.hibersap.forge.ior.dto.ImDateR("I", from, to, "BT"));
			ZOrdersReport rfc = new ZOrdersReport(podStatus, dateRange, Pad.left(sapId, 10));
			session.execute(rfc);

			return rfc.get_etCustOrders();
		} catch (Exception e) {
			log.error("Couldnt complete getCustomerOrdersByDateNew : + " + e.getMessage(), e);
			throw e;
		} finally {
			session.close();
		}
	}

}
