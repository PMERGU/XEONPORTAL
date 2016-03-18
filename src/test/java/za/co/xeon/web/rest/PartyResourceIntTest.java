package za.co.xeon.web.rest;

import za.co.xeon.Application;
import za.co.xeon.domain.Party;
import za.co.xeon.repository.PartyRepository;

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
 * Test class for the PartyResource REST controller.
 *
 * @see PartyResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PartyResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";
    private static final String DEFAULT_HOUSE_NUMBER = "AAAAA";
    private static final String UPDATED_HOUSE_NUMBER = "BBBBB";
    private static final String DEFAULT_STREET_NAME = "AAAAA";
    private static final String UPDATED_STREET_NAME = "BBBBB";
    private static final String DEFAULT_DISTRICT = "AAAAA";
    private static final String UPDATED_DISTRICT = "BBBBB";

    private static final Integer DEFAULT_POSTAL_CODE = 1;
    private static final Integer UPDATED_POSTAL_CODE = 2;
    private static final String DEFAULT_CITY = "AAAAA";
    private static final String UPDATED_CITY = "BBBBB";
    private static final String DEFAULT_COUNTRY = "AAAAA";
    private static final String UPDATED_COUNTRY = "BBBBB";
    private static final String DEFAULT_REFERENCE = "AAAAA";
    private static final String UPDATED_REFERENCE = "BBBBB";

    @Inject
    private PartyRepository partyRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restPartyMockMvc;

    private Party party;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PartyResource partyResource = new PartyResource();
        ReflectionTestUtils.setField(partyResource, "partyRepository", partyRepository);
        this.restPartyMockMvc = MockMvcBuilders.standaloneSetup(partyResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        party = new Party();
        party.setName(DEFAULT_NAME);
        party.setHouseNumber(DEFAULT_HOUSE_NUMBER);
        party.setStreetName(DEFAULT_STREET_NAME);
        party.setDistrict(DEFAULT_DISTRICT);
        party.setPostalCode(DEFAULT_POSTAL_CODE);
        party.setCity(DEFAULT_CITY);
        party.setCountry(DEFAULT_COUNTRY);
        party.setReference(DEFAULT_REFERENCE);
    }

    @Test
    @Transactional
    public void createParty() throws Exception {
        int databaseSizeBeforeCreate = partyRepository.findAll().size();

        // Create the Party

        restPartyMockMvc.perform(post("/api/partys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(party)))
                .andExpect(status().isCreated());

        // Validate the Party in the database
        List<Party> partys = partyRepository.findAll();
        assertThat(partys).hasSize(databaseSizeBeforeCreate + 1);
        Party testParty = partys.get(partys.size() - 1);
        assertThat(testParty.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testParty.getHouseNumber()).isEqualTo(DEFAULT_HOUSE_NUMBER);
        assertThat(testParty.getStreetName()).isEqualTo(DEFAULT_STREET_NAME);
        assertThat(testParty.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testParty.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testParty.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testParty.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testParty.getReference()).isEqualTo(DEFAULT_REFERENCE);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = partyRepository.findAll().size();
        // set the field null
        party.setName(null);

        // Create the Party, which fails.

        restPartyMockMvc.perform(post("/api/partys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(party)))
                .andExpect(status().isBadRequest());

        List<Party> partys = partyRepository.findAll();
        assertThat(partys).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPartys() throws Exception {
        // Initialize the database
        partyRepository.saveAndFlush(party);

        // Get all the partys
        restPartyMockMvc.perform(get("/api/partys?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(party.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].houseNumber").value(hasItem(DEFAULT_HOUSE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].streetName").value(hasItem(DEFAULT_STREET_NAME.toString())))
                .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT.toString())))
                .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
                .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
                .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
                .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())));
    }

    @Test
    @Transactional
    public void getParty() throws Exception {
        // Initialize the database
        partyRepository.saveAndFlush(party);

        // Get the party
        restPartyMockMvc.perform(get("/api/partys/{id}", party.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(party.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.houseNumber").value(DEFAULT_HOUSE_NUMBER.toString()))
            .andExpect(jsonPath("$.streetName").value(DEFAULT_STREET_NAME.toString()))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT.toString()))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingParty() throws Exception {
        // Get the party
        restPartyMockMvc.perform(get("/api/partys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParty() throws Exception {
        // Initialize the database
        partyRepository.saveAndFlush(party);

		int databaseSizeBeforeUpdate = partyRepository.findAll().size();

        // Update the party
        party.setName(UPDATED_NAME);
        party.setHouseNumber(UPDATED_HOUSE_NUMBER);
        party.setStreetName(UPDATED_STREET_NAME);
        party.setDistrict(UPDATED_DISTRICT);
        party.setPostalCode(UPDATED_POSTAL_CODE);
        party.setCity(UPDATED_CITY);
        party.setCountry(UPDATED_COUNTRY);
        party.setReference(UPDATED_REFERENCE);

        restPartyMockMvc.perform(put("/api/partys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(party)))
                .andExpect(status().isOk());

        // Validate the Party in the database
        List<Party> partys = partyRepository.findAll();
        assertThat(partys).hasSize(databaseSizeBeforeUpdate);
        Party testParty = partys.get(partys.size() - 1);
        assertThat(testParty.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testParty.getHouseNumber()).isEqualTo(UPDATED_HOUSE_NUMBER);
        assertThat(testParty.getStreetName()).isEqualTo(UPDATED_STREET_NAME);
        assertThat(testParty.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testParty.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testParty.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testParty.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testParty.getReference()).isEqualTo(UPDATED_REFERENCE);
    }

    @Test
    @Transactional
    public void deleteParty() throws Exception {
        // Initialize the database
        partyRepository.saveAndFlush(party);

		int databaseSizeBeforeDelete = partyRepository.findAll().size();

        // Get the party
        restPartyMockMvc.perform(delete("/api/partys/{id}", party.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Party> partys = partyRepository.findAll();
        assertThat(partys).hasSize(databaseSizeBeforeDelete - 1);
    }
}
