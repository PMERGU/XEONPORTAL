package za.co.xeon.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mysema.query.types.expr.BooleanExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import za.co.xeon.domain.PostalArea;
import za.co.xeon.repository.PostalAreaRepository;
import za.co.xeon.repository.UserRepository;
import za.co.xeon.repository.querydsl.PredicatesBuilder;
import za.co.xeon.security.AuthoritiesConstants;
import za.co.xeon.web.rest.util.HeaderUtil;
import za.co.xeon.web.rest.util.PaginationUtil;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * REST controller for managing Area.
 */
@RestController
@RequestMapping("/api")
public class PostalAreaResource {

    private final Logger log = LoggerFactory.getLogger(PostalAreaResource.class);

    @Inject
    private PostalAreaRepository areaRepository;

    @Inject
    private UserRepository userRepository;
    /**
     * POST  /areas -> Create a new area.
     */
    @RequestMapping(value = "/areas",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<PostalArea> createArea(@Valid @RequestBody PostalArea area) throws URISyntaxException {
        log.debug("REST request to save Area : {}", area);
        if (area.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("area", "idexists", "A new area cannot already have an ID")).body(null);
        }
        PostalArea result = areaRepository.save(area);
        return ResponseEntity.created(new URI("/api/areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("area", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /areas -> Updates an existing area.
     */
    @RequestMapping(value = "/areas",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<PostalArea> updateArea(@Valid @RequestBody PostalArea area) throws URISyntaxException {
        log.debug("REST request to update Area : {}", area);
        if (area.getId() == null) {
            return createArea(area);
        }
        PostalArea result = areaRepository.save(area);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("area", area.getId().toString()))
            .body(result);
    }

    /**
     * GET  /areas -> get all the areas.
     */
    @RequestMapping(value = "/areas",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<PostalArea>> getAllAreas(@RequestParam(value = "search", defaultValue = "") String search, Pageable pageable) throws URISyntaxException {
        log.debug("REST request to get all Areas - [search] : " + search);
        PredicatesBuilder builder = new PredicatesBuilder(PostalArea.class, "postalArea");
        Page<PostalArea> page = null;
        HttpHeaders headers = null;

        if(search != null){
            BooleanExpression exp = builder.search(search).build();
            page = areaRepository.findAll(exp, pageable);
            headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/areas");
        }else {
            page = areaRepository.findAll(pageable);
            headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/areas");
        }
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /areas/:id -> get the "id" area.
     */
    @RequestMapping(value = "/areas/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PostalArea> getArea(@PathVariable Long id) {
        log.debug("REST request to get Area : {}", id);
        PostalArea area = areaRepository.findOne(id);
        return Optional.ofNullable(area)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /areas/:id -> delete the "id" area.
     */
    @RequestMapping(value = "/areas/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteArea(@PathVariable Long id) {
        log.debug("REST request to delete Area : {}", id);
        PostalArea area = areaRepository.findOne(id);
        areaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("area", id.toString())).build();
    }
}
