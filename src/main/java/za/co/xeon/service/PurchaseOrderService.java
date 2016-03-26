package za.co.xeon.service;

import org.springframework.security.access.annotation.Secured;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.User;
import za.co.xeon.repository.PurchaseOrderRepository;
import za.co.xeon.security.AuthoritiesConstants;
import za.co.xeon.web.rest.dto.PurchaseOrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing PurchaseOrder.
 */
@Service
@Transactional
public class PurchaseOrderService {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderService.class);

    @Inject
    private PurchaseOrderRepository purchaseOrderRepository;

    /**
     * Save a purchaseOrder.
     * @return the persisted entity
     */
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        log.debug("Request to save PurchaseOrder : {}", purchaseOrder);
        return purchaseOrderRepository.save(purchaseOrder);
    }

    /**
     *  get all the purchaseOrders.
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    @Secured(AuthoritiesConstants.USER)
    public Page<PurchaseOrder> findAll(Pageable pageable) {
        log.debug("Request to get all PurchaseOrders");
        Page<PurchaseOrder> result = purchaseOrderRepository.findAll(pageable);
        return result;
    }

    /**
     *  get all the purchaseOrders.
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<PurchaseOrder> findAllByUser(User user, Pageable pageable) {
        log.debug("Request to get all PurchaseOrders");
        Page<PurchaseOrder> result = purchaseOrderRepository.findByUser(user, pageable);
        return result;
    }

    /**
     *  get one purchaseOrder by id.
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public PurchaseOrder findOne(Long id) {
        log.debug("Request to get PurchaseOrder : {}", id);
        return purchaseOrderRepository.findOne(id);
    }

    /**
     *  delete the  purchaseOrder by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete PurchaseOrder : {}", id);
        purchaseOrderRepository.delete(id);
    }
}
