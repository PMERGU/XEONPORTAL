package za.co.xeon.web.rest;

import za.co.xeon.Application;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.repository.PurchaseOrderRepository;
import za.co.xeon.service.PurchaseOrderService;
import za.co.xeon.web.rest.dto.PurchaseOrderDTO;
import za.co.xeon.web.rest.mapper.PurchaseOrderMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import za.co.xeon.domain.enumeration.PoState;
import za.co.xeon.domain.enumeration.ServiceLevel;
import za.co.xeon.domain.enumeration.CustomerType;
import za.co.xeon.domain.enumeration.DeliveryType;
import za.co.xeon.domain.enumeration.ModeOfTransport;
import za.co.xeon.domain.enumeration.DeliveryType;

/**
 * Test class for the PurchaseOrderResource REST controller.
 *
 * @see PurchaseOrderResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PurchaseOrderResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    
    private static final PoState DEFAULT_STATE = PoState.PROCESSED;
    private static final PoState UPDATED_STATE = PoState.UNPROCESSED;
    
    private static final ServiceLevel DEFAULT_SERVICE_LEVEL = ServiceLevel.ECONOMY;
    private static final ServiceLevel UPDATED_SERVICE_LEVEL = ServiceLevel.EXPRESS_AM;

    private static final ZonedDateTime DEFAULT_CAPTURE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CAPTURE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CAPTURE_DATE_STR = dateTimeFormatter.format(DEFAULT_CAPTURE_DATE);

    private static final LocalDate DEFAULT_DELIVERY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DELIVERY_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_PO_NUMBER = "AAAAA";
    private static final String UPDATED_PO_NUMBER = "BBBBB";
    private static final String DEFAULT_REFERENCE = "AAAAA";
    private static final String UPDATED_REFERENCE = "BBBBB";
    
    private static final CustomerType DEFAULT_CUSTOMER_TYPE = CustomerType.REGULAR;
    private static final CustomerType UPDATED_CUSTOMER_TYPE = CustomerType.ONE_TIME;
    
    private static final DeliveryType DEFAULT_SHIP_TO_TYPE = DeliveryType.RETAIL;
    private static final DeliveryType UPDATED_SHIP_TO_TYPE = DeliveryType.STANDARD;
    private static final String DEFAULT_TELEPHONE = "AAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBB";
    private static final String DEFAULT_COLLECTIVE = "AAAAA";
    private static final String UPDATED_COLLECTIVE = "BBBBB";
    private static final String DEFAULT_ACCOUNT_REFERENCE = "AAAAA";
    private static final String UPDATED_ACCOUNT_REFERENCE = "BBBBB";
    
    private static final ModeOfTransport DEFAULT_MODE_OF_TRANSPORT = ModeOfTransport.AIR_DELIVERIES;
    private static final ModeOfTransport UPDATED_MODE_OF_TRANSPORT = ModeOfTransport.AIR_TRANSFERS;
    private static final String DEFAULT_CARRIER_VESSEL_NAME = "AAAAA";
    private static final String UPDATED_CARRIER_VESSEL_NAME = "BBBBB";
    private static final String DEFAULT_CARRIER_VESSEL_NUMBER = "AAAAA";
    private static final String UPDATED_CARRIER_VESSEL_NUMBER = "BBBBB";
    
    private static final DeliveryType DEFAULT_PICK_UP_TYPE = DeliveryType.RETAIL;
    private static final DeliveryType UPDATED_PICK_UP_TYPE = DeliveryType.STANDARD;

    @Inject
    private PurchaseOrderRepository purchaseOrderRepository;

    @Inject
    private PurchaseOrderMapper purchaseOrderMapper;

    @Inject
    private PurchaseOrderService purchaseOrderService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restPurchaseOrderMockMvc;

    private PurchaseOrder purchaseOrder;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PurchaseOrderResource purchaseOrderResource = new PurchaseOrderResource();
        ReflectionTestUtils.setField(purchaseOrderResource, "purchaseOrderService", purchaseOrderService);
        ReflectionTestUtils.setField(purchaseOrderResource, "purchaseOrderMapper", purchaseOrderMapper);
        this.restPurchaseOrderMockMvc = MockMvcBuilders.standaloneSetup(purchaseOrderResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        purchaseOrder = new PurchaseOrder();
        purchaseOrder.setState(DEFAULT_STATE);
        purchaseOrder.setServiceLevel(DEFAULT_SERVICE_LEVEL);
        purchaseOrder.setCaptureDate(DEFAULT_CAPTURE_DATE);
        purchaseOrder.setDeliveryDate(DEFAULT_DELIVERY_DATE);
        purchaseOrder.setPoNumber(DEFAULT_PO_NUMBER);
        purchaseOrder.setReference(DEFAULT_REFERENCE);
        purchaseOrder.setCustomerType(DEFAULT_CUSTOMER_TYPE);
        purchaseOrder.setShipToType(DEFAULT_SHIP_TO_TYPE);
        purchaseOrder.setTelephone(DEFAULT_TELEPHONE);
        purchaseOrder.setCollective(DEFAULT_COLLECTIVE);
        purchaseOrder.setAccountReference(DEFAULT_ACCOUNT_REFERENCE);
        purchaseOrder.setModeOfTransport(DEFAULT_MODE_OF_TRANSPORT);
        purchaseOrder.setCarrierVesselName(DEFAULT_CARRIER_VESSEL_NAME);
        purchaseOrder.setCarrierVesselNumber(DEFAULT_CARRIER_VESSEL_NUMBER);
        purchaseOrder.setPickUpType(DEFAULT_PICK_UP_TYPE);
    }

    @Test
    @Transactional
    public void createPurchaseOrder() throws Exception {
        int databaseSizeBeforeCreate = purchaseOrderRepository.findAll().size();

        // Create the PurchaseOrder
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.purchaseOrderToPurchaseOrderDTO(purchaseOrder);

        restPurchaseOrderMockMvc.perform(post("/api/purchaseOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO)))
                .andExpect(status().isCreated());

        // Validate the PurchaseOrder in the database
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        assertThat(purchaseOrders).hasSize(databaseSizeBeforeCreate + 1);
        PurchaseOrder testPurchaseOrder = purchaseOrders.get(purchaseOrders.size() - 1);
        assertThat(testPurchaseOrder.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testPurchaseOrder.getServiceLevel()).isEqualTo(DEFAULT_SERVICE_LEVEL);
        assertThat(testPurchaseOrder.getCaptureDate()).isEqualTo(DEFAULT_CAPTURE_DATE);
        assertThat(testPurchaseOrder.getDeliveryDate()).isEqualTo(DEFAULT_DELIVERY_DATE);
        assertThat(testPurchaseOrder.getPoNumber()).isEqualTo(DEFAULT_PO_NUMBER);
        assertThat(testPurchaseOrder.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testPurchaseOrder.getCustomerType()).isEqualTo(DEFAULT_CUSTOMER_TYPE);
        assertThat(testPurchaseOrder.getShipToType()).isEqualTo(DEFAULT_SHIP_TO_TYPE);
        assertThat(testPurchaseOrder.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testPurchaseOrder.getCollective()).isEqualTo(DEFAULT_COLLECTIVE);
        assertThat(testPurchaseOrder.getAccountReference()).isEqualTo(DEFAULT_ACCOUNT_REFERENCE);
        assertThat(testPurchaseOrder.getModeOfTransport()).isEqualTo(DEFAULT_MODE_OF_TRANSPORT);
        assertThat(testPurchaseOrder.getCarrierVesselName()).isEqualTo(DEFAULT_CARRIER_VESSEL_NAME);
        assertThat(testPurchaseOrder.getCarrierVesselNumber()).isEqualTo(DEFAULT_CARRIER_VESSEL_NUMBER);
        assertThat(testPurchaseOrder.getPickUpType()).isEqualTo(DEFAULT_PICK_UP_TYPE);
    }

    @Test
    @Transactional
    public void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = purchaseOrderRepository.findAll().size();
        // set the field null
        purchaseOrder.setState(null);

        // Create the PurchaseOrder, which fails.
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.purchaseOrderToPurchaseOrderDTO(purchaseOrder);

        restPurchaseOrderMockMvc.perform(post("/api/purchaseOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO)))
                .andExpect(status().isBadRequest());

        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        assertThat(purchaseOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDeliveryDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = purchaseOrderRepository.findAll().size();
        // set the field null
        purchaseOrder.setDeliveryDate(null);

        // Create the PurchaseOrder, which fails.
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.purchaseOrderToPurchaseOrderDTO(purchaseOrder);

        restPurchaseOrderMockMvc.perform(post("/api/purchaseOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO)))
                .andExpect(status().isBadRequest());

        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        assertThat(purchaseOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPoNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = purchaseOrderRepository.findAll().size();
        // set the field null
        purchaseOrder.setPoNumber(null);

        // Create the PurchaseOrder, which fails.
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.purchaseOrderToPurchaseOrderDTO(purchaseOrder);

        restPurchaseOrderMockMvc.perform(post("/api/purchaseOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO)))
                .andExpect(status().isBadRequest());

        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        assertThat(purchaseOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReferenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = purchaseOrderRepository.findAll().size();
        // set the field null
        purchaseOrder.setReference(null);

        // Create the PurchaseOrder, which fails.
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.purchaseOrderToPurchaseOrderDTO(purchaseOrder);

        restPurchaseOrderMockMvc.perform(post("/api/purchaseOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO)))
                .andExpect(status().isBadRequest());

        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        assertThat(purchaseOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelephoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = purchaseOrderRepository.findAll().size();
        // set the field null
        purchaseOrder.setTelephone(null);

        // Create the PurchaseOrder, which fails.
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.purchaseOrderToPurchaseOrderDTO(purchaseOrder);

        restPurchaseOrderMockMvc.perform(post("/api/purchaseOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO)))
                .andExpect(status().isBadRequest());

        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        assertThat(purchaseOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCollectiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = purchaseOrderRepository.findAll().size();
        // set the field null
        purchaseOrder.setCollective(null);

        // Create the PurchaseOrder, which fails.
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.purchaseOrderToPurchaseOrderDTO(purchaseOrder);

        restPurchaseOrderMockMvc.perform(post("/api/purchaseOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO)))
                .andExpect(status().isBadRequest());

        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        assertThat(purchaseOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAccountReferenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = purchaseOrderRepository.findAll().size();
        // set the field null
        purchaseOrder.setAccountReference(null);

        // Create the PurchaseOrder, which fails.
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.purchaseOrderToPurchaseOrderDTO(purchaseOrder);

        restPurchaseOrderMockMvc.perform(post("/api/purchaseOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO)))
                .andExpect(status().isBadRequest());

        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        assertThat(purchaseOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCarrierVesselNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = purchaseOrderRepository.findAll().size();
        // set the field null
        purchaseOrder.setCarrierVesselName(null);

        // Create the PurchaseOrder, which fails.
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.purchaseOrderToPurchaseOrderDTO(purchaseOrder);

        restPurchaseOrderMockMvc.perform(post("/api/purchaseOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO)))
                .andExpect(status().isBadRequest());

        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        assertThat(purchaseOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCarrierVesselNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = purchaseOrderRepository.findAll().size();
        // set the field null
        purchaseOrder.setCarrierVesselNumber(null);

        // Create the PurchaseOrder, which fails.
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.purchaseOrderToPurchaseOrderDTO(purchaseOrder);

        restPurchaseOrderMockMvc.perform(post("/api/purchaseOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO)))
                .andExpect(status().isBadRequest());

        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        assertThat(purchaseOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPurchaseOrders() throws Exception {
        // Initialize the database
        purchaseOrderRepository.saveAndFlush(purchaseOrder);

        // Get all the purchaseOrders
        restPurchaseOrderMockMvc.perform(get("/api/purchaseOrders?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(purchaseOrder.getId().intValue())))
                .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
                .andExpect(jsonPath("$.[*].serviceLevel").value(hasItem(DEFAULT_SERVICE_LEVEL.toString())))
                .andExpect(jsonPath("$.[*].captureDate").value(hasItem(DEFAULT_CAPTURE_DATE_STR)))
                .andExpect(jsonPath("$.[*].deliveryDate").value(hasItem(DEFAULT_DELIVERY_DATE.toString())))
                .andExpect(jsonPath("$.[*].poNumber").value(hasItem(DEFAULT_PO_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())))
                .andExpect(jsonPath("$.[*].customerType").value(hasItem(DEFAULT_CUSTOMER_TYPE.toString())))
                .andExpect(jsonPath("$.[*].shipToType").value(hasItem(DEFAULT_SHIP_TO_TYPE.toString())))
                .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE.toString())))
                .andExpect(jsonPath("$.[*].collective").value(hasItem(DEFAULT_COLLECTIVE.toString())))
                .andExpect(jsonPath("$.[*].accountReference").value(hasItem(DEFAULT_ACCOUNT_REFERENCE.toString())))
                .andExpect(jsonPath("$.[*].modeOfTransport").value(hasItem(DEFAULT_MODE_OF_TRANSPORT.toString())))
                .andExpect(jsonPath("$.[*].carrierVesselName").value(hasItem(DEFAULT_CARRIER_VESSEL_NAME.toString())))
                .andExpect(jsonPath("$.[*].carrierVesselNumber").value(hasItem(DEFAULT_CARRIER_VESSEL_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].pickUpType").value(hasItem(DEFAULT_PICK_UP_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getPurchaseOrder() throws Exception {
        // Initialize the database
        purchaseOrderRepository.saveAndFlush(purchaseOrder);

        // Get the purchaseOrder
        restPurchaseOrderMockMvc.perform(get("/api/purchaseOrders/{id}", purchaseOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(purchaseOrder.getId().intValue()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.serviceLevel").value(DEFAULT_SERVICE_LEVEL.toString()))
            .andExpect(jsonPath("$.captureDate").value(DEFAULT_CAPTURE_DATE_STR))
            .andExpect(jsonPath("$.deliveryDate").value(DEFAULT_DELIVERY_DATE.toString()))
            .andExpect(jsonPath("$.poNumber").value(DEFAULT_PO_NUMBER.toString()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()))
            .andExpect(jsonPath("$.customerType").value(DEFAULT_CUSTOMER_TYPE.toString()))
            .andExpect(jsonPath("$.shipToType").value(DEFAULT_SHIP_TO_TYPE.toString()))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE.toString()))
            .andExpect(jsonPath("$.collective").value(DEFAULT_COLLECTIVE.toString()))
            .andExpect(jsonPath("$.accountReference").value(DEFAULT_ACCOUNT_REFERENCE.toString()))
            .andExpect(jsonPath("$.modeOfTransport").value(DEFAULT_MODE_OF_TRANSPORT.toString()))
            .andExpect(jsonPath("$.carrierVesselName").value(DEFAULT_CARRIER_VESSEL_NAME.toString()))
            .andExpect(jsonPath("$.carrierVesselNumber").value(DEFAULT_CARRIER_VESSEL_NUMBER.toString()))
            .andExpect(jsonPath("$.pickUpType").value(DEFAULT_PICK_UP_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPurchaseOrder() throws Exception {
        // Get the purchaseOrder
        restPurchaseOrderMockMvc.perform(get("/api/purchaseOrders/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePurchaseOrder() throws Exception {
        // Initialize the database
        purchaseOrderRepository.saveAndFlush(purchaseOrder);

		int databaseSizeBeforeUpdate = purchaseOrderRepository.findAll().size();

        // Update the purchaseOrder
        purchaseOrder.setState(UPDATED_STATE);
        purchaseOrder.setServiceLevel(UPDATED_SERVICE_LEVEL);
        purchaseOrder.setCaptureDate(UPDATED_CAPTURE_DATE);
        purchaseOrder.setDeliveryDate(UPDATED_DELIVERY_DATE);
        purchaseOrder.setPoNumber(UPDATED_PO_NUMBER);
        purchaseOrder.setReference(UPDATED_REFERENCE);
        purchaseOrder.setCustomerType(UPDATED_CUSTOMER_TYPE);
        purchaseOrder.setShipToType(UPDATED_SHIP_TO_TYPE);
        purchaseOrder.setTelephone(UPDATED_TELEPHONE);
        purchaseOrder.setCollective(UPDATED_COLLECTIVE);
        purchaseOrder.setAccountReference(UPDATED_ACCOUNT_REFERENCE);
        purchaseOrder.setModeOfTransport(UPDATED_MODE_OF_TRANSPORT);
        purchaseOrder.setCarrierVesselName(UPDATED_CARRIER_VESSEL_NAME);
        purchaseOrder.setCarrierVesselNumber(UPDATED_CARRIER_VESSEL_NUMBER);
        purchaseOrder.setPickUpType(UPDATED_PICK_UP_TYPE);
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.purchaseOrderToPurchaseOrderDTO(purchaseOrder);

        restPurchaseOrderMockMvc.perform(put("/api/purchaseOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO)))
                .andExpect(status().isOk());

        // Validate the PurchaseOrder in the database
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        assertThat(purchaseOrders).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrder testPurchaseOrder = purchaseOrders.get(purchaseOrders.size() - 1);
        assertThat(testPurchaseOrder.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testPurchaseOrder.getServiceLevel()).isEqualTo(UPDATED_SERVICE_LEVEL);
        assertThat(testPurchaseOrder.getCaptureDate()).isEqualTo(UPDATED_CAPTURE_DATE);
        assertThat(testPurchaseOrder.getDeliveryDate()).isEqualTo(UPDATED_DELIVERY_DATE);
        assertThat(testPurchaseOrder.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testPurchaseOrder.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testPurchaseOrder.getCustomerType()).isEqualTo(UPDATED_CUSTOMER_TYPE);
        assertThat(testPurchaseOrder.getShipToType()).isEqualTo(UPDATED_SHIP_TO_TYPE);
        assertThat(testPurchaseOrder.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testPurchaseOrder.getCollective()).isEqualTo(UPDATED_COLLECTIVE);
        assertThat(testPurchaseOrder.getAccountReference()).isEqualTo(UPDATED_ACCOUNT_REFERENCE);
        assertThat(testPurchaseOrder.getModeOfTransport()).isEqualTo(UPDATED_MODE_OF_TRANSPORT);
        assertThat(testPurchaseOrder.getCarrierVesselName()).isEqualTo(UPDATED_CARRIER_VESSEL_NAME);
        assertThat(testPurchaseOrder.getCarrierVesselNumber()).isEqualTo(UPDATED_CARRIER_VESSEL_NUMBER);
        assertThat(testPurchaseOrder.getPickUpType()).isEqualTo(UPDATED_PICK_UP_TYPE);
    }

    @Test
    @Transactional
    public void deletePurchaseOrder() throws Exception {
        // Initialize the database
        purchaseOrderRepository.saveAndFlush(purchaseOrder);

		int databaseSizeBeforeDelete = purchaseOrderRepository.findAll().size();

        // Get the purchaseOrder
        restPurchaseOrderMockMvc.perform(delete("/api/purchaseOrders/{id}", purchaseOrder.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        assertThat(purchaseOrders).hasSize(databaseSizeBeforeDelete - 1);
    }
}
