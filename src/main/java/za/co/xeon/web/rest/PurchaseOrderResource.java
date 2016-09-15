package za.co.xeon.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import za.co.xeon.domain.*;
import za.co.xeon.domain.enumeration.*;
import za.co.xeon.external.sap.hibersap.HiberSapService;
import za.co.xeon.external.sap.hibersap.SalesOrderCreateRFC;
import za.co.xeon.external.sap.hibersap.dto.GtCustOrdersDetail;
import za.co.xeon.external.sap.hibersap.dto.ImItemDetail;
import za.co.xeon.external.sap.hibersap.dto.ImOtcAdrCol;
import za.co.xeon.external.sap.hibersap.dto.ImOtcAdrShpto;
import za.co.xeon.repository.*;
import za.co.xeon.security.AuthoritiesConstants;
import za.co.xeon.security.SecurityUtils;
import za.co.xeon.service.MailService;
import za.co.xeon.service.MobileService;
import za.co.xeon.service.PurchaseOrderService;
import za.co.xeon.service.util.Pad;
import za.co.xeon.web.rest.dto.OrderDetails;
import za.co.xeon.web.rest.dto.SalesOrderCreatedDTO;
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
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Callable;
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
    private CommentRepository commentRepository;

    @Inject
    private PartyRepository partyRepository;

    @Inject
    private AttachmentRepository attachmentRepository;

    @Inject
    private MailService mailService;

    @Inject
    private HiberSapService hiberSapService;

    @Autowired
    private MobileService mobileService;
    /**
     * POST  /purchaseOrders -> Create a new purchaseOrder.
     */
    @RequestMapping(value = "/purchaseOrders",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@Valid @RequestBody PurchaseOrder purchaseOrder, HttpServletRequest request) throws URISyntaxException {
        log.debug("=============================================== create PO ===================================================");
        log.debug("REST request to save PurchaseOrder");
        //validations
        purchaseOrder.setPoNumber(purchaseOrder.getPoNumber().trim().toUpperCase());
        if (purchaseOrder.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("purchaseOrder", "id", "A new purchaseOrder cannot already have an ID")).body(null);
        }

        //captured by logic
        User user;
        User capturedBy = null; //when a xeon user captures a order on behalf of a customer
        if (SecurityUtils.isUserCustomer()) {
            user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
        } else {
            log.debug("Capturing as user {}", purchaseOrder.getUser());
            user = userRepository.findOneByLogin(purchaseOrder.getUser().getLogin()).get();
            capturedBy = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
            log.debug("PO captured by %s against customer %s", capturedBy.getLogin(), user.getLogin());
            purchaseOrder.setCapturedBy(capturedBy);
        }

        //logic
        purchaseOrder.setCaptureDate(ZonedDateTime.now());
        purchaseOrder.setUser(user);

        if (purchaseOrderRepository.findByUserId_CompanyAndPoNumber(purchaseOrder.getUser().getCompany(), purchaseOrder.getPoNumber()) != null) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert("purchaseOrder", "poNumber",
                    String.format("A PO with the number #%s already exists in the system. Please double check poNumber or double check the dashboard", purchaseOrder.getPoNumber())))
                .body(purchaseOrder);
        }


        //set PO as parent to PO.poLines
        purchaseOrder.getPoLines().stream().forEach(line -> line.setPurchaseOrder(purchaseOrder));
        log.debug(" purchaseOrder.getPoLines().size() : {}", purchaseOrder.getPoLines().size());
        log.debug(purchaseOrder.getPoLines().toString());

        try {
            log.debug(purchaseOrder.toStringFull());
            String imVkorg = null;
            String imVtweg = null;
            String imSpart = null;
            String imSerlvl = "L1";
            switch (purchaseOrder.getServiceType()){
                case INBOUND:
                    imVkorg = "3000";
                    imVtweg = "M1";
                    imSpart = "13";
                    break;
                case OUTBOUND:
                    imVkorg = "3000";
                    imVtweg = "M1";
                    imSpart = "13";
                    break;
                case CROSS_HAUL:
                case FULL_CONTAINER_LOAD:
                case FULL_TRUCK_LOAD:
                    imVkorg = "3000";
                    imVtweg = "L1";
                    imSpart = "L1";
                    break;
                case BREAKBULK_TRANSPORT:
                    imVkorg = "3000";
                    imVtweg = "L1";
                    imSpart = "L1";
                    imSerlvl = determineBreakBulkSapType(purchaseOrder).getSapCode();
                    break;
                default:
                    throw new RuntimeException("Need to map new service type here");
            }
            if(purchaseOrder.getPickUpParty() != null) purchaseOrder.setPickUpParty(partyRepository.findOne(purchaseOrder.getPickUpParty().getId()));
            if(purchaseOrder.getShipToParty() != null) purchaseOrder.setShipToParty(partyRepository.findOne(purchaseOrder.getShipToParty().getId()));
            if(purchaseOrder.getSoldToParty() != null) purchaseOrder.setSoldToParty(partyRepository.findOne(purchaseOrder.getSoldToParty().getId()));
            Party pup = purchaseOrder.getPickUpParty();
            Party stp = purchaseOrder.getShipToParty();
            SalesOrderCreateRFC rfc = new SalesOrderCreateRFC(
                purchaseOrder.getAccountReference(), safeEnum(purchaseOrder.getServiceType()), Pad.left(purchaseOrder.getUser().getCompany().getSapId(), 10),
                purchaseOrder.getCollectionReference(), Pad.left(purchaseOrder.getPickUpParty().getSapId(), 10), safeEnum(purchaseOrder.getCargoType()), purchaseOrder.getCvConsol(),
                purchaseOrder.getCvContainerNo(), safeDate(purchaseOrder.getDropOffDate()), safeEnum(purchaseOrder.getShipToType()),
                purchaseOrder.getCvDestination(), safeDate(purchaseOrder.getCvEta()), safeDate(purchaseOrder.getCvEtd()),
                purchaseOrder.getUser().getFcSapId(), purchaseOrder.getCvHouseWaybill(), safeDate(purchaseOrder.getCvHouseWaybillIssue()),
                convertItems(purchaseOrder, purchaseOrder.getPoLines()), safeEnum(purchaseOrder.getModeOfTransport()), purchaseOrder.getCvWaybill(), safeDate(purchaseOrder.getCvWaybillIssue()),
                purchaseOrder.getSpecialInstruction(), purchaseOrder.getCvOrigin(), purchaseOrder.getCvCarrierRef(), safeEnum(purchaseOrder.getPickUpType()), safeDate(purchaseOrder.getCaptureDate().toLocalDate()),
                purchaseOrder.getPoNumber(), purchaseOrder.getPickUpParty().getArea().getHub(), imSerlvl, safeEnum(purchaseOrder.getServiceLevel()), purchaseOrder.getCvShipper(),
                purchaseOrder.getCvCarrierRef(), Pad.left(purchaseOrder.getShipToParty().getSapId(), 10), purchaseOrder.getSoldToParty().getReference(),
                Pad.left(purchaseOrder.getSoldToParty().getSapId(), 10), imSpart,
                (purchaseOrder.getTradeType().equals(TradeType.DOMESTIC)? "1" : ""), (purchaseOrder.getTradeType().equals(TradeType.EXPORT)? "1" : ""), (purchaseOrder.getTradeType().equals(TradeType.IMPORT)? "1" : ""),
                purchaseOrder.getTelephone(), purchaseOrder.getCvName(), purchaseOrder.getCvNumber(), imVkorg, imVtweg,
                pup.getSapId().equals("100000") ? new ImOtcAdrCol(pup.getName(),pup.getName(),pup.getReference(),pup.getStreetName(),
                    pup.getArea().getCity(), pup.getArea().getSuburb(),pup.getArea().getProvince(),pup.getArea().getTrafficZone(),
                    pup.getArea().getCountry(),pup.getArea().getPostalCode().toString(),pup.getArea().getPostalCode().toString(),
                    pup.getArea().getCity(),"","") : null,
                pup.getSapId().equals("100000") ? new ImOtcAdrShpto(stp.getName(),stp.getName(),stp.getReference(),stp.getStreetName(),
                    stp.getArea().getCity(), stp.getArea().getSuburb(),stp.getArea().getProvince(),stp.getArea().getTrafficZone(),
                    stp.getArea().getCountry(),stp.getArea().getPostalCode().toString(),stp.getArea().getPostalCode().toString(),
                    stp.getArea().getCity(),"","") : null
            );
            log.debug(rfc.toStringFull());
            User whoDidIt = (capturedBy == null ? user : capturedBy);
            log.debug(" whoDidIt : " + whoDidIt);
            try {
                purchaseOrder.setState(PoState.PROCESSED);
                SalesOrderCreatedDTO so = hiberSapService.createSalesOrder(purchaseOrder.getPoNumber(), rfc);
                purchaseOrder.setSoNumber(so.getSoNumber());
                PurchaseOrder savedPo = purchaseOrderService.save(purchaseOrder);
                log.debug(" PO saved as ID : {} - SO created as ID : {}", savedPo.getId(), savedPo.getSoNumber());
                if(whoDidIt.getId() != user.getId()){
                    log.debug(" whoDidIt [1]");
                    mailService.sendCSUMail(whoDidIt,
                        String.format("Xeon Portal: New SO created for %s as %s", user.getCompany().getName(), so.getSoNumber()),
                        String.format("A new Purchase Order #%s has been created by %s %s for controller %s %s for client %s and SAP SO auto created as %s.",
                            savedPo.getPoNumber(), whoDidIt.getFirstName(), whoDidIt.getLastName(), user.getFirstName(), user.getLastName(), user.getCompany().getName(), so.getSoNumber())
                        , null, null, getBaseUrl(request));
                }else{
                    log.debug(" whoDidIt [2]");
                    mailService.sendCSUMail(whoDidIt,
                        String.format("Xeon Portal: New SO created for %s as %s", user.getCompany().getName(), so.getSoNumber()),
                        String.format("A new Purchase Order #%s has been created by controller %s %s for client %s and SAP SO auto created as %s.",
                            savedPo.getPoNumber(), user.getFirstName(), user.getLastName(), user.getCompany().getName(), so.getSoNumber())
                        , null, null, getBaseUrl(request));
                }
                if(capturedBy != null){
                    mailService.sendPoProcessedMail(user, purchaseOrder, getBaseUrl(request), true);
                }
                return ResponseEntity.created(new URI("/api/purchaseOrders/" + savedPo.getId()))
                    .headers(HeaderUtil.createAlert(
                        String.format("New purchase order [%s] created and sales order [%s] auto captured in SAP.", savedPo.getId(), savedPo.getSoNumber()),
                        savedPo.getId().toString()
                    )).body(savedPo);
            }catch (Exception e){
                log.warn("Could not create SO in SAP, doing fallback and creating manual entry for CSU to capture.");
                purchaseOrder.setState(PoState.UNPROCESSED);
                PurchaseOrder savedPo = purchaseOrderService.save(purchaseOrder);
                if(whoDidIt.getId() != user.getId()) {
                    log.debug(" whoDidIt [3]");
                    mailService.sendCSUMail(whoDidIt,
                        String.format("Xeon Portal: New PO created for %s", user.getCompany().getName()),
                        String.format("A new Purchase Order #%s has been created by %s %s for controller %s %s for client %s. Please action and capture in SAP as soon as possible.",
                            savedPo.getPoNumber(), whoDidIt.getFirstName(), whoDidIt.getLastName(), user.getFirstName(), user.getLastName(), user.getCompany().getName())
                        , null, null, getBaseUrl(request));
                }else{
                    log.debug(" whoDidIt [4]");
                    mailService.sendCSUMail(whoDidIt,
                        String.format("Xeon Portal: New PO created for %s", user.getCompany().getName()),
                        String.format("A new Purchase Order #%s has been created by controller %s %s for client %s. Please action and capture in SAP as soon as possible.",
                            savedPo.getPoNumber(), user.getFirstName(), user.getLastName(), user.getCompany().getName())
                        , null, null, getBaseUrl(request));
                }
                return ResponseEntity.created(new URI("/api/purchaseOrders/" + savedPo.getId()))
                    .headers(HeaderUtil.createEntityCreationAlert("purchase order", savedPo.getId().toString()))
                    .body(savedPo);
            }finally {
                log.debug("============================================= END create PO ==================================================");
            }

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            ex.printStackTrace();
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert("Purchase order could not be saved: " + ex.getMessage()))
                .body(null);
        }
    }

    private ShippingType determineBreakBulkSapType(PurchaseOrder po){
        ShippingType shippingType = null;
        log.debug("  determineBreakBulkSapType - [{}]", 1);
        if(po.getPickUpParty().getCompany().getType().equals(CompanyType.XEON)){
            log.debug("  determineBreakBulkSapType - [{}]", 2);
            if(po.getShipToParty().getCompany().getType().equals(CompanyType.XEON)){
                log.debug("  determineBreakBulkSapType - [{}]", 3);
                //PICKUP IS XEON & DROP_OFF IS XEON
                shippingType = ShippingType.HUB_TO_HUB_LINE;
            }else{
                log.debug("  determineBreakBulkSapType - [{}]", 4);
                if(po.getPickUpParty().getArea().getHub().equals(po.getShipToParty().getArea().getHub())){
                    log.debug("  determineBreakBulkSapType - [{}]", 5);
                    //PICKUP IS XEON & AREA HUB'S MATCH
                    shippingType = ShippingType.HUB_TO_DOOR_LOCAL;
                }else{
                    log.debug("  determineBreakBulkSapType - [{}]", 6);
                    //PICKUP IS XEON & AREA HUB'S DON'T MATCH
                    shippingType = ShippingType.HUB_TO_DOOR_LINE;
                }
            }
        }else if(po.getShipToParty().getCompany().getType().equals(CompanyType.XEON)){
            log.debug("  determineBreakBulkSapType - [{}]", 7);
            if(po.getPickUpParty().getArea().getHub().equals(po.getShipToParty().getArea().getHub())){
                log.debug("  determineBreakBulkSapType - [{}]", 8);
                //DROPOFF IS XEON & AREA HUB'S MATCH
                shippingType = ShippingType.DOOR_TO_HUB_LOCAL;
            }else{
                log.debug("  determineBreakBulkSapType - [{}]", 9);
                //DROPOFF IS XEON & AREA HUB'S DON'T MATCH
                shippingType = ShippingType.DOOR_TO_HUB_LINE;
            }
        }else if(po.getPickUpParty().getArea().getHub().equals(po.getShipToParty().getArea().getHub())){
            log.debug("  determineBreakBulkSapType - [{}]", 10);
            //AREA HUB' MATCH
            shippingType = ShippingType.DOOR_TO_DOOR_LOCAL;
        }else {
            log.debug("  determineBreakBulkSapType - [{}]", 11);
            //AREA HUB'S DON'T MATCH
            shippingType = ShippingType.DOOR_TO_DOOR_LINE;
        }

        return shippingType;
    }

    private List<ImItemDetail> convertItems(PurchaseOrder savedPo, List<PoLine> poLines){
//        log.debug("line.getMaterialType().getSapCode() : {}",poLines.get(0).getMaterialType().getSapCode());
//        log.debug("line.getOrderQuantity() : {}",new BigDecimal(poLines.get(0).getOrderQuantity()));
//        log.debug("savedPo.getPickUpParty().getArea().getPlant() : {}",savedPo.getPickUpParty().getArea().getPlant());
//        log.debug("line.getBatchNumber() : {}",poLines.get(0).getBatchNumber());
//        log.debug("savedPo.getPickUpParty().getArea().getPlant() : {}",savedPo.getPickUpParty().getArea().getPlant());
//        log.debug("new BigDecimal(line.getLength()) : {}",new BigDecimal(poLines.get(0).getLength()));
//        log.debug("new BigDecimal(line.getWidth()) : {}",new BigDecimal(poLines.get(0).getWidth()));
//        log.debug("new BigDecimal(line.getLength()) : {}",new BigDecimal(poLines.get(0).getLength()));
//        log.debug("new BigDecimal(line.getGrossWeight()) : {}",new BigDecimal(poLines.get(0).getGrossWeight()));
//        log.debug("new BigDecimal(line.getNetWeight()) : {}",new BigDecimal(poLines.get(0).getNetWeight()));
        return poLines.stream().map(line -> new ImItemDetail(
            line.getMaterialType().getSapCode(), new BigDecimal(line.getOrderQuantity()), null, savedPo.getPickUpParty().getArea().getPlant(),
            line.getBatchNumber(), null, savedPo.getPickUpParty().getArea().getPlant(),
            (line.getLength() == null ? null : new BigDecimal(line.getLength())),
            (line.getWidth() == null ? null : new BigDecimal(line.getWidth())),
            (line.getHeight() == null ? null : new BigDecimal(line.getHeight())),
            "cm", "cm", "cm",
            (line.getGrossWeight() == null ? null : new BigDecimal(line.getGrossWeight())),
            (line.getNetWeight() == null ? null : new BigDecimal(line.getNetWeight())), "KG", "KG"
        )).collect(Collectors.toList());
    }

    private <T> String safeEnum(T t) {
        if (t == null) {
            log.debug("enum is null");
            return "";
        } else if (t.getClass().isEnum() && t instanceof SapCode) {
            return ((SapCode)t).getSapCode();
        } else if (t.getClass().isEnum()) {
            return t.toString();
        }
        log.debug("Could`nt figure out what the hell this type is : {}\n{}", t.getClass().getName());
        return "";
    }

    private <T> Date safeDate(T t) {
        if (t == null) {
            log.debug("date is null");
            return null;
        } else if (t instanceof LocalDate) {
            return java.sql.Date.valueOf((LocalDate)t);
        }
        log.debug("Could`nt figure out what the hell this type is : {}\n{}", t.getClass().getName());
        return new Date();
    }

/**
 * PUT  /purchaseOrders -> Updates an existing purchaseOrder.
 */
//    @RequestMapping(value = "/purchaseOrders",
//        method = RequestMethod.PUT,
//        produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed
//    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@Valid @RequestBody PurchaseOrder purchaseOrder, HttpServletRequest request) throws URISyntaxException {
//        log.debug("REST request to update PurchaseOrder : {}", purchaseOrder);
//        PurchaseOrder previous = purchaseOrderService.findOne(purchaseOrder.getId());
//        if(previous.getState().equals(PoState.PROCESSED)) {
//            return ResponseEntity.badRequest()
//                .headers(HeaderUtil.createFailureAlert("purchaseOrder","poNumber",
//                    String.format("This purchase order has already been processed and cant be updated.", purchaseOrder.getPoNumber())))
//                .body(purchaseOrder);
//        }else if(!previous.getPoNumber().equals(purchaseOrder.getPoNumber())){
//            if(purchaseOrderRepository.findFirstByPoNumber(purchaseOrder.getPoNumber()) != null){
//                return ResponseEntity.badRequest()
//                    .headers(HeaderUtil.createFailureAlert("purchaseOrder","poNumber",
//                        String.format("A PO with the number #%s already exists in the system. Please double check poNumber or double check the dashboard", purchaseOrder.getPoNumber())))
//                    .body(purchaseOrder);
//            }
//        }
//        if(purchaseOrder.getState().equals(PoState.PROCESSED)){
//            return ResponseEntity.badRequest()
//                .headers(HeaderUtil.createFailureAlert("purchase order", purchaseOrder.getId().toString(), "Cant update an already processed order. Please contact Xeon customer team"))
//                .body(purchaseOrder);
//        }
//        if (purchaseOrder.getId() == null) {
//            return createPurchaseOrder(purchaseOrder, request);
//        }
//        purchaseOrder.getPoLines().stream().forEach(line -> line.setPurchaseOrder(purchaseOrder));
//        log.debug(" purchaseOrder.getPoLines().size() : " + purchaseOrder.getPoLines().size());
//        log.debug(purchaseOrder.getPoLines().toString());
//
//        PurchaseOrder result = purchaseOrderService.save(purchaseOrder);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert("purchaseOrder", purchaseOrder.getId().toString()))
//            .body(result);
//    }

    /**
     * PUT  /purchaseOrders -> Updates an existing purchaseOrder.
     */
    @RequestMapping(value = "/purchaseOrders/{id}/state",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PurchaseOrder> updatePurchaseOrderState(@PathVariable Long
                                                                      id, @Valid @RequestBody PurchaseOrder statePO, HttpServletRequest request) throws URISyntaxException {
        log.debug("REST request to update PurchaseOrder state : [id:{}] to {}", id, statePO.getState());
        PurchaseOrder purchaseOrder = purchaseOrderService.findOne(id);
        User xeonUser = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get();
        purchaseOrder.setState(statePO.getState());
        purchaseOrder.setXeonUser(xeonUser);
        purchaseOrder.setUpdatedDate(ZonedDateTime.now());
        purchaseOrder.setSoNumber(statePO.getSoNumber());
        purchaseOrder.setSoComment(statePO.getSoComment());
        PurchaseOrder result = purchaseOrderService.save(purchaseOrder);
        if (statePO.getState().equals(PoState.PROCESSED)) {
            mailService.sendPoProcessedMail(purchaseOrder.getUser(), purchaseOrder, getBaseUrl(request), true);
            mailService.sendCSUMail(xeonUser,
                String.format("Xeon Portal: PO #%s has been processed by %s", purchaseOrder.getPoNumber(), xeonUser.getFirstName()),
                String.format("Please note that this PO was been captured and processed in SAP. %s %s from client %s has been informed via Email already.", purchaseOrder.getUser().getFirstName(), purchaseOrder.getUser().getLastName(), purchaseOrder.getUser().getCompany().getName()),
                null, null, getBaseUrl(request));
            return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert("Marked as Processed and email sent to " + purchaseOrder.getUser().getFirstName() + " " + purchaseOrder.getUser().getLastName() + " from " + purchaseOrder.getUser().getCompany().getName() + " succesfully.", ""))
                .body(result);
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createAlert("Order grabbed by " + purchaseOrder.getXeonUser().getFirstName() + " " + purchaseOrder.getXeonUser().getLastName() + ". Please complete SAP captured ASAP.", ""))
            .body(result);

    }

    public String getBaseUrl(HttpServletRequest request) {
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
        User xeonUser = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get();
        purchaseOrder.setComment(comment);
        purchaseOrder.setXeonUser(xeonUser);
        purchaseOrder.setUpdatedDate(ZonedDateTime.now());
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
        if (SecurityUtils.isUserXeonOrAdmin()) {
            page = purchaseOrderService.findAll(pageable);
        } else {
            User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
            if(SecurityUtils.isUserCustomerCSU()){
                log.debug("Restricting PurchaseOrders lookup by company (CSU) " + user.getCompany().getName());
                page = purchaseOrderRepository.findByUserId_Company(user.getCompany(), pageable);
            }else {
                log.debug("Restricting PurchaseOrders lookup by username " + user.getLogin());
                page = purchaseOrderService.findAllByUser(user, pageable);
            }
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchaseOrders");
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
        if (SecurityUtils.isUserXeonOrAdmin()) {
            page = purchaseOrderRepository.findByState(state, pageable);
        } else {
            User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
            if(SecurityUtils.isUserCustomerCSU()){
                log.debug("Restricting getAllPurchaseOrdersByState lookup by company (CSU) " + user.getCompany().getName());
                page = purchaseOrderRepository.findByUserId_CompanyAndState(user.getCompany(), state, pageable);
            }else {
                log.debug("Restricting getAllPurchaseOrdersByState lookup by username " + user.getLogin());
                page = purchaseOrderRepository.findByUserAndState(user, state, pageable);
            }
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchaseOrders");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /purchaseOrders -> get all the purchaseOrders.
     */
    @RequestMapping(value = "/purchaseOrders/poNumber/{poNumber}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<PurchaseOrder> getAllPurchaseOrdersByPoNumber(@PathVariable String poNumber, Pageable pageable) throws URISyntaxException {
        log.debug("REST request to get getAllPurchaseOrdersByPoNumber by poNumber " + poNumber);
        PurchaseOrder purchaseOrder;
        if (SecurityUtils.isUserXeonOrAdmin()) {
            purchaseOrder = purchaseOrderRepository.findFirstByPoNumber(poNumber);
        } else {
            User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
            if(SecurityUtils.isUserCustomerCSU()){
                log.debug("Restricting getAllPurchaseOrdersByState lookup by company (CSU) " + user.getCompany().getName());
                purchaseOrder = purchaseOrderRepository.findByUserId_CompanyAndPoNumber(user.getCompany(), poNumber);
            }else {
                log.debug("Restricting getAllPurchaseOrdersByState lookup by username " + user.getLogin());
                purchaseOrder = purchaseOrderRepository.findFirstByUserAndPoNumber(user, poNumber);
            }
        }
        return Optional.ofNullable(purchaseOrder)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/purchaseOrders/{id}/orders/{deliveryNo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public Callable<ResponseEntity<List<GtCustOrdersDetail>>> getPoOrder(@PathVariable Long id, @PathVariable(value="deliveryNo") String deliveryNo, Pageable pageable) throws Exception {
        log.debug("Service [GET] /purchaseOrders/{}/orders/{}", id, deliveryNo);
        return () -> {
            List<GtCustOrdersDetail> sapOrders = null;
            PurchaseOrder purchaseOrder;
            purchaseOrder = purchaseOrderRepository.findOne(id);
            User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();

            if(purchaseOrder != null){
                sapOrders = mobileService.getCustomerOrderDetails(deliveryNo, new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-01"), new Date()).get();
                if(sapOrders.isEmpty()) {
                    log.debug("Service [GET] /purchaseOrders/{}/orders/{} - could not find sap orders", id, deliveryNo);
                }
            }else{
                log.debug("Service [GET] /purchaseOrders/{}/orders/{} - could not find po against user's company [{}]", id, deliveryNo, user.getCompany().getName());
            }

            return Optional.ofNullable(sapOrders)
                .map(result -> new ResponseEntity<>(
                    result,
                    HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        };
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
        if (!(SecurityUtils.isUserXeonOrAdmin())) {
            User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
            if (purchaseOrder.getUser().getCompany().getId() != user.getCompany().getId()) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        Page<PoLine> page = poLineRepository.findByPurchaseOrder(purchaseOrder, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/poLines");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /poLines -> get all the poLines.
     */
    @RequestMapping(value = "/purchaseOrders/{id}/comments",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Comment>> getAllPoComments(@PathVariable Long id, Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of comments");
        PurchaseOrder purchaseOrder = purchaseOrderService.findOne(id);
        Page<Comment> page = null;
        if (!(SecurityUtils.isUserXeonOrAdmin())) {
            page = commentRepository.findByInternalIsFalseAndPurchaseOrder(purchaseOrder, pageable);
        }else {
            page = commentRepository.findByPurchaseOrder(purchaseOrder, pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comments");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /attachments -> get all the attachment.
     */
    @RequestMapping(value = "/purchaseOrders/{id}/all/attachments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Attachment>> getAllPoAttachments(@PathVariable Long id, Pageable pageable) throws URISyntaxException {
        log.debug("REST request to get a page of attachments");
        PurchaseOrder purchaseOrder = purchaseOrderService.findOne(id);
        Page<Attachment> page = attachmentRepository.findByPurchaseOrder(purchaseOrder, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/purchaseOrders/" + id + "/all/attachments");
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
        if (!(SecurityUtils.isUserXeonOrAdmin())) {
            User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
            if (purchaseOrder.getUser().getCompany().getId() != user.getCompany().getId()) {
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

        if (!(SecurityUtils.isUserXeonOrAdmin())) {
            User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
            if (purchaseOrder.getUser().getCompany().getId() != user.getCompany().getId()) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            if (purchaseOrder.getState().equals(PoState.PROCESSED)) {
                return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert("purchaseOrder", "poNumber",
                        String.format("This purchase order has already been process and can’t be edited. Please contact Xeon CSU.")))
                    .body(null);
            }
        }

        purchaseOrderService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("purchaseOrder", id.toString())).build();
    }
}
