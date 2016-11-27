package za.co.xeon.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import za.co.xeon.domain.Company;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.User;
import za.co.xeon.domain.dto.CompanyDto;
import za.co.xeon.repository.CompanyRepository;
import za.co.xeon.repository.PurchaseOrderRepository;
import za.co.xeon.repository.UserRepository;
import za.co.xeon.web.rest.util.HeaderUtil;
import za.co.xeon.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Company.
 */
@RestController
@RequestMapping("/api")
public class CompanyResource {

	private final Logger log = LoggerFactory.getLogger(CompanyResource.class);

	@Inject
	private CompanyRepository companyRepository;

	@Inject
	private PurchaseOrderRepository purchaseRepository;

	@Inject
	private UserRepository userRepository;

	/**
	 * POST /companies -> Create a new company.
	 */
	@RequestMapping(value = "/companies", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company) throws URISyntaxException {
		log.debug("REST request to save Company : {}", company);
		if (company.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("company", "idexists", "A new company cannot already have an ID")).body(null);
		}
		company.setSapId(company.getSapId().trim());
		Company result = companyRepository.save(company);
		return ResponseEntity.created(new URI("/api/companies/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert("company", result.getId().toString())).body(result);
	}

	/**
	 * PUT /companies -> Updates an existing company.
	 */
	@RequestMapping(value = "/companies", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Company> updateCompany(@Valid @RequestBody Company company) throws URISyntaxException {
		log.debug("REST request to update Company : {}", company);
		if (company.getId() == null) {
			return createCompany(company);
		}
		Company result = companyRepository.save(company);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert("company", company.getId().toString())).body(result);
	}

	/**
	 * GET /companies -> get all the companys.
	 */
	@RequestMapping(value = "/companies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<Company>> getAllCompanies(Pageable pageable) throws URISyntaxException {
		log.debug("REST request to get a page of Companys");
		Page<Company> page = companyRepository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/companies");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /typeahead/companies/:name -> get all the companys for typeahead.
	 */
	@RequestMapping(value = "/typeahead/companies/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<Company> getAllCompaniesStartingWith(@PathVariable String name) throws URISyntaxException {
		log.debug("REST request to get a page of Companys");
		return companyRepository.findByNameStartingWithOrderByName(name);
	}

	/**
	 * GET /companies/:id -> get the "id" company.
	 */
	@RequestMapping(value = "/companies/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Company> getCompany(@PathVariable Long id) {
		log.debug("REST request to get Company : {}", id);
		Company company = companyRepository.findOne(id);
		return Optional.ofNullable(company).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * GET /companies/:id/image -> get the "id" company.
	 */
	@RequestMapping(value = "/companies/{id}/image", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<CompanyDto> getCompanyLogo(@PathVariable Long id) {
		log.debug("REST request to get Company : {}", id);
		return Optional.ofNullable(CompanyDto.map(companyRepository.findOne(id))).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * GET /companies/:id/users -> get all users in company.
	 */
	@RequestMapping(value = "/companies/{id}/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@Transactional(readOnly = true)
	public List<User> getAllUsersByCompany(@PathVariable Long id) throws URISyntaxException {
		return userRepository.findAllByEnabledIsTrueAndCompany(companyRepository.findOne(id));
	}

	/**
	 * GET /companies/:id/purchaseOrders -> get all users in company.
	 */
	@RequestMapping(value = "/companies/{id}/purchaseOrders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@Transactional(readOnly = true)
	public List<PurchaseOrder> getAllPurchaseOrdersByCompany(@PathVariable Long id) throws URISyntaxException {
		return purchaseRepository.findByUserId_Company(companyRepository.findOne(id));
	}

	/**
	 * DELETE /companies/:id -> delete the "id" company.
	 */
	@RequestMapping(value = "/companies/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
		log.debug("REST request to delete Company : {}", id);
		companyRepository.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("company", id.toString())).build();
	}
}
