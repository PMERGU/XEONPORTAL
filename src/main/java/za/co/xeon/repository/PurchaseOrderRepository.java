package za.co.xeon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import za.co.xeon.domain.PurchaseOrder;

import org.springframework.data.jpa.repository.*;
import za.co.xeon.domain.User;

import java.util.List;

/**
 * Spring Data JPA repository for the PurchaseOrder entity.
 */
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long> {
    Page<PurchaseOrder> findByUser(User user, Pageable pageable);
}
