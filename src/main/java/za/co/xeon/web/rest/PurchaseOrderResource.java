package za.co.xeon.web.rest;

import com.codahale.metrics.annotation.Timed;
import za.co.xeon.domain.Company;
import za.co.xeon.domain.PoLine;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.User;
import za.co.xeon.domain.enumeration.PoState;
import za.co.xeon.repository.PoLineRepository;
import za.co.xeon.repository.PurchaseOrderRepository;
import za.co.xeon.repository.UserRepository;
import za.co.xeon.security.AuthoritiesConstants;
import za.co.xeon.security.SecurityUtils;
import za.co.xeon.service.MailService;
import za.co.xeon.service.PurchaseOrderService;
import za.co.xeon.web.rest.util.HeaderUtil;
import za.co.xeon.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

/**
 * REST controller for managing PurchaseOrder.
 */
@RestController
@RequestMapping("/api")
public class PurchaseOrderResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderResource.class);

    @Inject
    private PurchaseOrderService purchaseOrderService;

    @Inject
    private PurchaseOrderRepository purchaseOrderRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private PoLineRepository poLineRepository;

    @Inject
    private MailService mailService;

    /**
     * POST  /purchaseOrders -> Create a new purchaseOrder.
     */
    @RequestMapping(value = "/purchaseOrders",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@Valid @RequestBody PurchaseOrder purchaseOrder, HttpServletRequest request) throws URISyntaxException {
        log.debug("REST request to save PurchaseOrder : {}", purchaseOrder);
        if(purchaseOrderRepository.findFirstByPoNumber(purchaseOrder.getPoNumber()) != null){
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert("purchaseOrder","poNumber",
                    String.format("A PO with the number #%s already exists in the system. Please double check poNumber or double check the dashboard", purchaseOrder.getPoNumber())))
                .body(purchaseOrder);
        }
        if (purchaseOrder.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("purchaseOrder", "idexists", "A new purchaseOrder cannot already have an ID")).body(null);
        }
        User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
        purchaseOrder.setUser(user);
        purchaseOrder.setCaptureDate(ZonedDateTime.now());
        purchaseOrder.setState(PoState.UNPROCESSED);
        //set PO as parent to PO.poLines
        purchaseOrder.getPoLines().stream().forEach(line -> line.setPurchaseOrder(purchaseOrder));
        log.debug(" ------------------------------------------------------- : " + purchaseOrder.getPoLines().size());
        log.debug(purchaseOrder.getPoLines().toString());
        PurchaseOrder result = purchaseOrderService.save(purchaseOrder);
        mailService.sendCSUMail(user,
            String.format("New PO created for %s", user.getCompany().getName()),
            String.format("A new Purchase Order #%s has been created by %s %s for client %s. Please action and capture as soon as possible.", result.getPoNumber(), user.getFirstName(), user.getLastName(), user.getCompany().getName())
            , null, null, getBaseUrl(request));
        return ResponseEntity.created(new URI("/api/purchaseOrders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("purchase order", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /purchaseOrders -> Updates an existing purchaseOrder.
     */
    @RequestMapping(value = "/purchaseOrders",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@Valid @RequestBody PurchaseOrder purchaseOrder, HttpServletRequest request) throws URISyntaxException {
        log.debug("REST request to update PurchaseOrder : {}", purchaseOrder);
        PurchaseOrder previous = purchaseOrderService.findOne(purchaseOrder.getId());
        if(previous.getState().equals(PoState.PROCESSED)) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert("purchaseOrder","poNumber",
                    String.format("This purchase order has already been processed and cant be updated.", purchaseOrder.getPoNumber())))
                .body(purchaseOrder);
        }else if(!previous.getPoNumber().equals(purchaseOrder.getPoNumber())){
            if(purchaseOrderRepository.findFirstByPoNumber(purchaseOrder.getPoNumber()) != null){
                return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert("purchaseOrder","poNumber",
                        String.format("A PO with the number #%s already exists in the system. Please double check poNumber or double check the dashboard", purchaseOrder.getPoNumber())))
                    .body(purchaseOrder);
            }
        }
        if(purchaseOrder.getState().equals(PoState.PROCESSED)){
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert("purchase order", purchaseOrder.getId().toString(), "Cant update an already processed order. Please contact Xeon customer team"))
                .body(purchaseOrder);
        }
        if (purchaseOrder.getId() == null) {
            return createPurchaseOrder(purchaseOrder, request);
        }
        purchaseOrder.getPoLines().stream().forEach(line ->
            line.setPurchaseOrder(purchaseOrder)
        );
        log.debug(" ------------------------------------------------------- : " + purchaseOrder.getPoLines().size());
        log.debug(purchaseOrder.getPoLines().toString());
        PurchaseOrder result = purchaseOrderService.save(purchaseOrder);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("purchaseOrder", purchaseOrder.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /purchaseOrders -> Updates an existing purchaseOrder.
     */
    @RequestMapping(value = "/purchaseOrders/{id}/state",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PurchaseOrder> updatePurchaseOrderState(@PathVariable Long id, @Valid @RequestBody PurchaseOrder statePO, HttpServletRequest request) throws URISyntaxException {
        log.debug("REST request to update PurchaseOrder state : [id:{}] to {}", id, statePO.getState());
        PurchaseOrder purchaseOrder = purchaseOrderService.findOne(id);
        User xeonUser = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get();
        purchaseOrder.setState(statePO.getState());
        purchaseOrder.setXeonUser(xeonUser);
        purchaseOrder.setSoNumber(statePO.getSoNumber());
        purchaseOrder.setSoComment(statePO.getSoComment());
        PurchaseOrder result = purchaseOrderService.save(purchaseOrder);
        if(statePO.getState().equals(PoState.PROCESSED)) {
            mailService.sendPoProcessedMail(xeonUser, purchaseOrder, getBaseUrl(request));
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createAlert("Marked as Processed and email sent to " + purchaseOrder.getUser().getFirstName() + " " + purchaseOrder.getUser().getLastName() + " from " + purchaseOrder.getUser().getCompany().getName() + " succesfully.", ""))
            .body(result);
    }

    public String getBaseUrl(HttpServletRequest request){
        return request.getScheme() + // "http"
            "://" +                                // "://"
            request.getServerName() +              // "myhost"
            ":" +                                  // ":"
            request.getServerPort() +              // "80"
            request.getContextPath();              // "/myContextPath" or "" if deployed in root context
    }

    /**
     * PUT  /purchaseOrders -> Updates an existing purchaseOrder.
     */
    @RequestMapping(value = "/purchaseOrders/{id}/comment",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PurchaseOrder> updatePurchaseOrderComment(@PathVariable Long id, @Valid @RequestBody String comment, HttpServletRequest request) throws URISyntaxException {
        log.debug("REST request to update PurchaseOrder comment : [id:{}] to {}", id, comment);
        PurchaseOrder purchaseOrder = purchaseOrderService.findOne(id);
        purchaseOrder.setComment(comment);
        PurchaseOrder result = purchaseOrderService.save(purchaseOrder);
        String baseUrl = request.getScheme() + // "http"
            "://" +                                // "://"
            request.getServerName() +              // "myhost"
            ":" +                                  // ":"
            request.getServerPort() +              // "80"
            request.getContextPath();              // "/myContextPath" or "" if deployed in root context
        log.debug("baseurl: " + baseUrl);
        mailService.sendPoCommentMail(userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get(), purchaseOrder, baseUrl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createAlert("Comment email sent to " + purchaseOrder.getUser().getFirstName() + " " + purchaseOrder.getUser().getLastName() + " successfully.", ""))
            .body(result);
    }

    /**
     * GET  /purchaseOrders -> get all the purchaseOrders.
     */
    @RequestMapping(value = "/purchaseOrders",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders(Pageable pageable) throws URISyntaxException {
        log.debug("REST request to get a page of PurchaseOrders");
        Page<PurchaseOrder> page = null;
        if(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER) || SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)){
            page = purchaseOrderService.findAll(pageable);
        }else {
            User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
            log.debug("Restricting PurchaseOrders lookup by username " + user.getLogin());
            page = purchaseOrderService.findAllByUser(user, pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/companys");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /purchaseOrders -> get all the purchaseOrders.
     */
    @RequestMapping(value = "/purchaseOrders/state/{state}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrdersByState(@PathVariable PoState state, Pageable pageable) throws URISyntaxException {
        log.debug("REST request to get getAllPurchaseOrdersByState by state " + state.name());
        Page<PurchaseOrder> page = null;
        if(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER) || SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)){
            page = purchaseOrderRepository.findByState(state, pageable);
        }else {
            User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
            log.debug("Restricting getAllPurchaseOrdersByState lookup by username " + user.getLogin());
            page = purchaseOrderRepository.findByUserAndState(user, state, pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/companys");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /poLines -> get all the poLines.
     */
    @RequestMapping(value = "/purchaseOrders/{id}/lines",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<PoLine>> getAllPoLines(@PathVariable Long id, Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of PoLines");
        PurchaseOrder purchaseOrder = purchaseOrderService.findOne(id);
        if(!(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER) || SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN))){
            User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
            if(purchaseOrder.getUser().getCompany().getId() != user.getCompany().getId()){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        Page<PoLine> page = poLineRepository.findByPurchaseOrder(purchaseOrder, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/poLines");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /purchaseOrders/:id -> get the "id" purchaseOrder.
     */
    @RequestMapping(value = "/purchaseOrders/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PurchaseOrder> getPurchaseOrder(@PathVariable Long id) {
        log.debug("REST request to get PurchaseOrder : {}", id);
        PurchaseOrder purchaseOrder = purchaseOrderService.findOne(id);
        if(!(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER) || SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN))){
            User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
            if(purchaseOrder.getUser().getCompany().getId() != user.getCompany().getId()){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return Optional.ofNullable(purchaseOrder)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /purchaseOrders/:id -> delete the "id" purchaseOrder.
     */
    @RequestMapping(value = "/purchaseOrders/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseOrder : {}", id);
        PurchaseOrder purchaseOrder = purchaseOrderService.findOne(id);
        log.debug("PO state " + purchaseOrder.getState());

        if(!(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER) || SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN))) {
            User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
            if (purchaseOrder.getUser().getCompany().getId() != user.getCompany().getId()) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            if(purchaseOrder.getState().equals(PoState.PROCESSED)){
                return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert("purchaseOrder","poNumber",
                        String.format("This purchase order has already been processed and cant be deleted.")))
                    .body(null);
            }
        }

        purchaseOrderService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("purchaseOrder", id.toString())).build();
    }
}
