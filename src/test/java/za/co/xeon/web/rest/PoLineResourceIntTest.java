package za.co.xeon.web.rest;

import za.co.xeon.Application;
import za.co.xeon.domain.PoLine;
import za.co.xeon.repository.PoLineRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the PoLineResource REST controller.
 *
 * @see PoLineResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PoLineResourceIntTest {

    private static final String DEFAULT_MATERIAL_NUMBER = "AAAAA";
    private static final String UPDATED_MATERIAL_NUMBER = "BBBBB";

    private static final Integer DEFAULT_ORDER_QUANTITY = 1;
    private static final Integer UPDATED_ORDER_QUANTITY = 2;
    private static final String DEFAULT_UNIT_OF_MEASURE = "AAAAA";
    private static final String UPDATED_UNIT_OF_MEASURE = "BBBBB";
    private static final String DEFAULT_WAREHOUSE = "AAAAA";
    private static final String UPDATED_WAREHOUSE = "BBBBB";

    private static final Integer DEFAULT_LENGTH = 1;
    private static final Integer UPDATED_LENGTH = 2;

    private static final Integer DEFAULT_WIDTH = 1;
    private static final Integer UPDATED_WIDTH = 2;

    private static final Integer DEFAULT_HEIGHT = 1;
    private static final Integer UPDATED_HEIGHT = 2;

    private static final Integer DEFAULT_GROSS_WEIGHT = 1;
    private static final Integer UPDATED_GROSS_WEIGHT = 2;

    private static final Integer DEFAULT_NET_WEIGHT = 1;
    private static final Integer UPDATED_NET_WEIGHT = 2;
    private static final String DEFAULT_BATCH_NUMBER = "AAAAA";
    private static final String UPDATED_BATCH_NUMBER = "BBBBB";

    @Inject
    private PoLineRepository poLineRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restPoLineMockMvc;

    private PoLine poLine;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PoLineResource poLineResource = new PoLineResource();
        ReflectionTestUtils.setField(poLineResource, "poLineRepository", poLineRepository);
        this.restPoLineMockMvc = MockMvcBuilders.standaloneSetup(poLineResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        poLine = new PoLine();
        poLine.setMaterialNumber(DEFAULT_MATERIAL_NUMBER);
        poLine.setOrderQuantity(DEFAULT_ORDER_QUANTITY);
        poLine.setWarehouse(DEFAULT_WAREHOUSE);
        poLine.setLength(DEFAULT_LENGTH);
        poLine.setWidth(DEFAULT_WIDTH);
        poLine.setHeight(DEFAULT_HEIGHT);
        poLine.setGrossWeight(DEFAULT_GROSS_WEIGHT);
        poLine.setNetWeight(DEFAULT_NET_WEIGHT);
        poLine.setBatchNumber(DEFAULT_BATCH_NUMBER);
    }

    @Test
    @Transactional
    public void createPoLine() throws Exception {
        int databaseSizeBeforeCreate = poLineRepository.findAll().size();

        // Create the PoLine

        restPoLineMockMvc.perform(post("/api/poLines")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(poLine)))
                .andExpect(status().isCreated());

        // Validate the PoLine in the database
        List<PoLine> poLines = poLineRepository.findAll();
        assertThat(poLines).hasSize(databaseSizeBeforeCreate + 1);
        PoLine testPoLine = poLines.get(poLines.size() - 1);
        assertThat(testPoLine.getMaterialNumber()).isEqualTo(DEFAULT_MATERIAL_NUMBER);
        assertThat(testPoLine.getOrderQuantity()).isEqualTo(DEFAULT_ORDER_QUANTITY);
        assertThat(testPoLine.getWarehouse()).isEqualTo(DEFAULT_WAREHOUSE);
        assertThat(testPoLine.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testPoLine.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testPoLine.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testPoLine.getGrossWeight()).isEqualTo(DEFAULT_GROSS_WEIGHT);
        assertThat(testPoLine.getNetWeight()).isEqualTo(DEFAULT_NET_WEIGHT);
        assertThat(testPoLine.getBatchNumber()).isEqualTo(DEFAULT_BATCH_NUMBER);
    }

    @Test
    @Transactional
    public void checkMaterialNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = poLineRepository.findAll().size();
        // set the field null
        poLine.setMaterialNumber(null);

        // Create the PoLine, which fails.

        restPoLineMockMvc.perform(post("/api/poLines")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(poLine)))
                .andExpect(status().isBadRequest());

        List<PoLine> poLines = poLineRepository.findAll();
        assertThat(poLines).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = poLineRepository.findAll().size();
        // set the field null
        poLine.setOrderQuantity(null);

        // Create the PoLine, which fails.

        restPoLineMockMvc.perform(post("/api/poLines")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(poLine)))
                .andExpect(status().isBadRequest());

        List<PoLine> poLines = poLineRepository.findAll();
        assertThat(poLines).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWarehouseIsRequired() throws Exception {
        int databaseSizeBeforeTest = poLineRepository.findAll().size();
        // set the field null
        poLine.setWarehouse(null);

        // Create the PoLine, which fails.

        restPoLineMockMvc.perform(post("/api/poLines")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(poLine)))
                .andExpect(status().isBadRequest());

        List<PoLine> poLines = poLineRepository.findAll();
        assertThat(poLines).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLengthIsRequired() throws Exception {
        int databaseSizeBeforeTest = poLineRepository.findAll().size();
        // set the field null
        poLine.setLength(null);

        // Create the PoLine, which fails.

        restPoLineMockMvc.perform(post("/api/poLines")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(poLine)))
                .andExpect(status().isBadRequest());

        List<PoLine> poLines = poLineRepository.findAll();
        assertThat(poLines).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWidthIsRequired() throws Exception {
        int databaseSizeBeforeTest = poLineRepository.findAll().size();
        // set the field null
        poLine.setWidth(null);

        // Create the PoLine, which fails.

        restPoLineMockMvc.perform(post("/api/poLines")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(poLine)))
                .andExpect(status().isBadRequest());

        List<PoLine> poLines = poLineRepository.findAll();
        assertThat(poLines).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = poLineRepository.findAll().size();
        // set the field null
        poLine.setHeight(null);

        // Create the PoLine, which fails.

        restPoLineMockMvc.perform(post("/api/poLines")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(poLine)))
                .andExpect(status().isBadRequest());

        List<PoLine> poLines = poLineRepository.findAll();
        assertThat(poLines).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGrossWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = poLineRepository.findAll().size();
        // set the field null
        poLine.setGrossWeight(null);

        // Create the PoLine, which fails.

        restPoLineMockMvc.perform(post("/api/poLines")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(poLine)))
                .andExpect(status().isBadRequest());

        List<PoLine> poLines = poLineRepository.findAll();
        assertThat(poLines).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNetWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = poLineRepository.findAll().size();
        // set the field null
        poLine.setNetWeight(null);

        // Create the PoLine, which fails.

        restPoLineMockMvc.perform(post("/api/poLines")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(poLine)))
                .andExpect(status().isBadRequest());

        List<PoLine> poLines = poLineRepository.findAll();
        assertThat(poLines).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPoLines() throws Exception {
        // Initialize the database
        poLineRepository.saveAndFlush(poLine);

        // Get all the poLines
        restPoLineMockMvc.perform(get("/api/poLines?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(poLine.getId().intValue())))
                .andExpect(jsonPath("$.[*].materialNumber").value(hasItem(DEFAULT_MATERIAL_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].orderQuantity").value(hasItem(DEFAULT_ORDER_QUANTITY)))
                .andExpect(jsonPath("$.[*].unitOfMeasure").value(hasItem(DEFAULT_UNIT_OF_MEASURE.toString())))
                .andExpect(jsonPath("$.[*].warehouse").value(hasItem(DEFAULT_WAREHOUSE.toString())))
                .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)))
                .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)))
                .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
                .andExpect(jsonPath("$.[*].grossWeight").value(hasItem(DEFAULT_GROSS_WEIGHT)))
                .andExpect(jsonPath("$.[*].netWeight").value(hasItem(DEFAULT_NET_WEIGHT)))
                .andExpect(jsonPath("$.[*].batchNumber").value(hasItem(DEFAULT_BATCH_NUMBER.toString())));
    }

    @Test
    @Transactional
    public void getPoLine() throws Exception {
        // Initialize the database
        poLineRepository.saveAndFlush(poLine);

        // Get the poLine
        restPoLineMockMvc.perform(get("/api/poLines/{id}", poLine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(poLine.getId().intValue()))
            .andExpect(jsonPath("$.materialNumber").value(DEFAULT_MATERIAL_NUMBER.toString()))
            .andExpect(jsonPath("$.orderQuantity").value(DEFAULT_ORDER_QUANTITY))
            .andExpect(jsonPath("$.unitOfMeasure").value(DEFAULT_UNIT_OF_MEASURE.toString()))
            .andExpect(jsonPath("$.warehouse").value(DEFAULT_WAREHOUSE.toString()))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT))
            .andExpect(jsonPath("$.grossWeight").value(DEFAULT_GROSS_WEIGHT))
            .andExpect(jsonPath("$.netWeight").value(DEFAULT_NET_WEIGHT))
            .andExpect(jsonPath("$.batchNumber").value(DEFAULT_BATCH_NUMBER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPoLine() throws Exception {
        // Get the poLine
        restPoLineMockMvc.perform(get("/api/poLines/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePoLine() throws Exception {
        // Initialize the database
        poLineRepository.saveAndFlush(poLine);

		int databaseSizeBeforeUpdate = poLineRepository.findAll().size();

        // Update the poLine
        poLine.setMaterialNumber(UPDATED_MATERIAL_NUMBER);
        poLine.setOrderQuantity(UPDATED_ORDER_QUANTITY);
        poLine.setWarehouse(UPDATED_WAREHOUSE);
        poLine.setLength(UPDATED_LENGTH);
        poLine.setWidth(UPDATED_WIDTH);
        poLine.setHeight(UPDATED_HEIGHT);
        poLine.setGrossWeight(UPDATED_GROSS_WEIGHT);
        poLine.setNetWeight(UPDATED_NET_WEIGHT);
        poLine.setBatchNumber(UPDATED_BATCH_NUMBER);

        restPoLineMockMvc.perform(put("/api/poLines")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(poLine)))
                .andExpect(status().isOk());

        // Validate the PoLine in the database
        List<PoLine> poLines = poLineRepository.findAll();
        assertThat(poLines).hasSize(databaseSizeBeforeUpdate);
        PoLine testPoLine = poLines.get(poLines.size() - 1);
        assertThat(testPoLine.getMaterialNumber()).isEqualTo(UPDATED_MATERIAL_NUMBER);
        assertThat(testPoLine.getOrderQuantity()).isEqualTo(UPDATED_ORDER_QUANTITY);;
        assertThat(testPoLine.getWarehouse()).isEqualTo(UPDATED_WAREHOUSE);
        assertThat(testPoLine.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testPoLine.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testPoLine.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testPoLine.getGrossWeight()).isEqualTo(UPDATED_GROSS_WEIGHT);
        assertThat(testPoLine.getNetWeight()).isEqualTo(UPDATED_NET_WEIGHT);
        assertThat(testPoLine.getBatchNumber()).isEqualTo(UPDATED_BATCH_NUMBER);
    }

    @Test
    @Transactional
    public void deletePoLine() throws Exception {
        // Initialize the database
        poLineRepository.saveAndFlush(poLine);

		int databaseSizeBeforeDelete = poLineRepository.findAll().size();

        // Get the poLine
        restPoLineMockMvc.perform(delete("/api/poLines/{id}", poLine.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PoLine> poLines = poLineRepository.findAll();
        assertThat(poLines).hasSize(databaseSizeBeforeDelete - 1);
    }
}
