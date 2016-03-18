package za.co.xeon.service;

import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.repository.PurchaseOrderRepository;
import za.co.xeon.web.rest.dto.PurchaseOrderDTO;
import za.co.xeon.web.rest.mapper.PurchaseOrderMapper;
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
    
    @Inject
    private PurchaseOrderMapper purchaseOrderMapper;
    
    /**
     * Save a purchaseOrder.
     * @return the persisted entity
     */
    public PurchaseOrderDTO save(PurchaseOrderDTO purchaseOrderDTO) {
        log.debug("Request to save PurchaseOrder : {}", purchaseOrderDTO);
        PurchaseOrder purchaseOrder = purchaseOrderMapper.purchaseOrderDTOToPurchaseOrder(purchaseOrderDTO);
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        PurchaseOrderDTO result = purchaseOrderMapper.purchaseOrderToPurchaseOrderDTO(purchaseOrder);
        return result;
    }

    /**
     *  get all the purchaseOrders.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<PurchaseOrder> findAll(Pageable pageable) {
        log.debug("Request to get all PurchaseOrders");
        Page<PurchaseOrder> result = purchaseOrderRepository.findAll(pageable); 
        return result;
    }

    /**
     *  get one purchaseOrder by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public PurchaseOrderDTO findOne(Long id) {
        log.debug("Request to get PurchaseOrder : {}", id);
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findOne(id);
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.purchaseOrderToPurchaseOrderDTO(purchaseOrder);
        return purchaseOrderDTO;
    }

    /**
     *  delete the  purchaseOrder by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete PurchaseOrder : {}", id);
        purchaseOrderRepository.delete(id);
    }
}
