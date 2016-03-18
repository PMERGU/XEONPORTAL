package za.co.xeon.web.rest;

import com.codahale.metrics.annotation.Timed;
import za.co.xeon.domain.Party;
import za.co.xeon.repository.PartyRepository;
import za.co.xeon.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Party.
 */
@RestController
@RequestMapping("/api")
public class PartyResource {

    private final Logger log = LoggerFactory.getLogger(PartyResource.class);
        
    @Inject
    private PartyRepository partyRepository;
    
    /**
     * POST  /partys -> Create a new party.
     */
    @RequestMapping(value = "/partys",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Party> createParty(@Valid @RequestBody Party party) throws URISyntaxException {
        log.debug("REST request to save Party : {}", party);
        if (party.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("party", "idexists", "A new party cannot already have an ID")).body(null);
        }
        Party result = partyRepository.save(party);
        return ResponseEntity.created(new URI("/api/partys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("party", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /partys -> Updates an existing party.
     */
    @RequestMapping(value = "/partys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Party> updateParty(@Valid @RequestBody Party party) throws URISyntaxException {
        log.debug("REST request to update Party : {}", party);
        if (party.getId() == null) {
            return createParty(party);
        }
        Party result = partyRepository.save(party);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("party", party.getId().toString()))
            .body(result);
    }

    /**
     * GET  /partys -> get all the partys.
     */
    @RequestMapping(value = "/partys",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Party> getAllPartys() {
        log.debug("REST request to get all Partys");
        return partyRepository.findAll();
            }

    /**
     * GET  /partys/:id -> get the "id" party.
     */
    @RequestMapping(value = "/partys/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Party> getParty(@PathVariable Long id) {
        log.debug("REST request to get Party : {}", id);
        Party party = partyRepository.findOne(id);
        return Optional.ofNullable(party)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /partys/:id -> delete the "id" party.
     */
    @RequestMapping(value = "/partys/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteParty(@PathVariable Long id) {
        log.debug("REST request to delete Party : {}", id);
        partyRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("party", id.toString())).build();
    }
}
