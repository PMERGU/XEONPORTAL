package za.co.xeon.service;

public class ReportRFCTest {
//	SessionManager sessionManager;
//
//	@Before
//	public void init() {
//		SessionManagerConfig cfg = new SessionManagerConfig("A12").setContext(JCoContext.class.getName()).setProperty(DestinationDataProvider.JCO_ASHOST, "/H/197.234.173.1/W/FunkyChicken99/H/128.87.245.163/S/3200").setProperty(DestinationDataProvider.JCO_SYSNR, "00").setProperty(DestinationDataProvider.JCO_CLIENT, "700").setProperty(DestinationDataProvider.JCO_USER, "dpotgieter").setProperty(DestinationDataProvider.JCO_PASSWD, "mobile").setProperty(DestinationDataProvider.JCO_LANG, "EN").setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "3").setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, "15");
//
//		AnnotationConfiguration configuration = new AnnotationConfiguration(cfg);
//		configuration.addBapiClasses(ZStockInventory.class, ZGetCustomerOrdersByDate.class, CustOrdersRFC.class);
//		sessionManager = configuration.buildSessionManager();
//	}
//
//	@Test
//	public void testStockReport() {
//		SMatkl obj = new SMatkl();
//		obj.set_sign("I");
//		obj.set_option("EQ");
//		obj.set_low("VERBATIM");
//		ZStockInventory rfc = new ZStockInventory();
//		List<SMatkl> vals = new ArrayList<SMatkl>();
//		vals.add(obj);
//		rfc.set_sMatkl(vals);
//		Session session = sessionManager.openSession();
//		session.execute(rfc);
//		List<StockInventory> ret = rfc.get_stockInventory();
//		for (StockInventory data : ret)
//			System.out.println(data);
//	}
//
//	@Test
//	public void testPODReport() throws ParseException {
//		String customerNumber = Pad.left("22", 10);
//		Date curr = new Date();
//		ImDateR date = new ImDateR("I", "BT", DateUtils.addDays(curr, -365), curr);
//		ArrayList<ImDateR> list = new ArrayList<ImDateR>();
//		list.add(date);
//		ZGetCustomerOrdersByDate rfc = new ZGetCustomerOrdersByDate(list, "A", customerNumber);
//		Session session = sessionManager.openSession();
//		session.execute(rfc);
//		for (EvResult res : rfc.get_evResult())
//			System.out.println(res);
//	}
//
//	@Test
//	public void testSalesOrder() throws ParseException {
//		Date curr = new Date();
//		String customerNumber = Pad.left("213", 10);
//		za.co.xeon.external.sap.hibersap.dto.ImDateR date = new za.co.xeon.external.sap.hibersap.dto.ImDateR("I", "BT", DateUtils.addDays(curr, -190), curr);
//		ArrayList<za.co.xeon.external.sap.hibersap.dto.ImDateR> list = new ArrayList<za.co.xeon.external.sap.hibersap.dto.ImDateR>();
//		list.add(date);
//		CustOrdersRFC rfc = new CustOrdersRFC(customerNumber, list, null);
//		Session session = sessionManager.openSession();
//		session.execute(rfc);
//		for (GtCustOrders res : rfc.getGtCustOrders())
//			System.out.println(res);
//	}
}
