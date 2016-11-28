package za.co.xeon.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import za.co.xeon.Application;
import za.co.xeon.external.sap.hibersap.HiberSapService;
import za.co.xeon.repository.AttachmentRepository;
import za.co.xeon.repository.PurchaseOrderRepository;
import za.co.xeon.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ReportResourceTest {
	@Inject
	private HiberSapService hiberSapService;
	@Inject
	private UserRepository userRepository;
	@Inject
	private PurchaseOrderRepository purchaseOrderRepository;
	@Inject
	private AttachmentRepository attachmentRepository;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		ReportResource rep = new ReportResource();
		ReflectionTestUtils.setField(rep, "userRepository", userRepository);
		ReflectionTestUtils.setField(rep, "purchaseOrderRepository", purchaseOrderRepository);
		ReflectionTestUtils.setField(rep, "attachmentRepository", attachmentRepository);
		ReflectionTestUtils.setField(rep, "hiberSapService", hiberSapService);
		this.mockMvc = MockMvcBuilders.standaloneSetup(rep).build();
	}

	@Test
	public void testStockReport() throws Exception {
		mockMvc.perform(get("/api/stockReport/admin").accept(MediaType.APPLICATION_JSON));
	}

}
