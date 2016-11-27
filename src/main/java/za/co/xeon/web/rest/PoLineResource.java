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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import za.co.xeon.domain.PoLine;
import za.co.xeon.repository.PoLineRepository;
import za.co.xeon.web.rest.util.HeaderUtil;
import za.co.xeon.web.rest.util.PaginationUtil;

/**
 * REST controller for managing PoLine.
 */
@RestController
@RequestMapping("/api")
public class PoLineResource {

	private final Logger log = LoggerFactory.getLogger(PoLineResource.class);

	@Inject
	private PoLineRepository poLineRepository;

	/**
	 * POST /poLines -> Create a new poLine.
	 */
	@RequestMapping(value = "/poLines", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<PoLine> createPoLine(@Valid @RequestBody PoLine poLine) throws URISyntaxException {
		log.debug("REST request to save PoLine : {}", poLine);
		if (poLine.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("poLine", "idexists", "A new poLine cannot already have an ID")).body(null);
		}
		PoLine result = poLineRepository.save(poLine);
		return ResponseEntity.created(new URI("/api/poLines/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert("poLine", result.getId().toString())).body(result);
	}

	/**
	 * PUT /poLines -> Updates an existing poLine.
	 */
	@RequestMapping(value = "/poLines", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<PoLine> updatePoLine(@Valid @RequestBody PoLine poLine) throws URISyntaxException {
		log.debug("REST request to update PoLine : {}", poLine);
		if (poLine.getId() == null) {
			return createPoLine(poLine);
		}
		PoLine result = poLineRepository.save(poLine);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert("poLine", poLine.getId().toString())).body(result);
	}

	/**
	 * GET /poLines -> get all the poLines.
	 */
	@RequestMapping(value = "/poLines", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<PoLine>> getAllPoLines(Pageable pageable) throws URISyntaxException {
		log.debug("REST request to get a page of PoLines");
		Page<PoLine> page = poLineRepository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/poLines");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /poLines/:id -> get the "id" poLine.
	 */
	@RequestMapping(value = "/poLines/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<PoLine> getPoLine(@PathVariable Long id) {
		log.debug("REST request to get PoLine : {}", id);
		PoLine poLine = poLineRepository.findOne(id);
		return Optional.ofNullable(poLine).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * DELETE /poLines/:id -> delete the "id" poLine.
	 */
	@RequestMapping(value = "/poLines/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> deletePoLine(@PathVariable Long id) {
		log.debug("REST request to delete PoLine : {}", id);
		poLineRepository.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("poLine", id.toString())).build();
	}
}
