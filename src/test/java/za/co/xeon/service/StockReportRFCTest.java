package za.co.xeon.service;

import java.util.ArrayList;
import java.util.List;

import org.hibersap.configuration.AnnotationConfiguration;
import org.hibersap.configuration.xml.SessionManagerConfig;
import org.hibersap.execution.jco.JCoContext;
import org.hibersap.session.Session;
import org.hibersap.session.SessionManager;
import org.junit.Before;
import org.junit.Test;

import com.sap.conn.jco.ext.DestinationDataProvider;

import za.co.xeon.external.sap.hibersap.StockReportRFC;
import za.co.xeon.external.sap.hibersap.dto.SMatkl;
import za.co.xeon.external.sap.hibersap.dto.StockData;

public class StockReportRFCTest {
	SessionManager sessionManager;

	@Before
	public void init() {
		SessionManagerConfig cfg = new SessionManagerConfig("A12").setContext(JCoContext.class.getName())
				.setProperty(DestinationDataProvider.JCO_ASHOST,
						"/H/197.234.173.1/W/FunkyChicken99/H/128.87.245.163/S/3200")
				.setProperty(DestinationDataProvider.JCO_SYSNR, "00")
				.setProperty(DestinationDataProvider.JCO_CLIENT, "700")
				.setProperty(DestinationDataProvider.JCO_USER, "dpotgieter")
				.setProperty(DestinationDataProvider.JCO_PASSWD, "mobile")
				.setProperty(DestinationDataProvider.JCO_LANG, "EN")
				.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "3")
				.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, "15");

		AnnotationConfiguration configuration = new AnnotationConfiguration(cfg);
		configuration.addBapiClasses(StockReportRFC.class);
		sessionManager = configuration.buildSessionManager();
	}

	@Test
	public void testStockReport() {
		SMatkl obj = new SMatkl();
		obj.set_sign("I");
		obj.set_option("EQ");
		obj.set_low("VERBATIM");
		StockReportRFC rfc = new StockReportRFC();
		List<SMatkl> vals = new ArrayList<SMatkl>();
		vals.add(obj);
		rfc.set_sMatkl(vals);
		Session session = sessionManager.openSession();
		session.execute(rfc);
		List<StockData> ret = rfc.get_stockData();
		for (StockData data : ret)
			System.out.println(data);
	}
}
