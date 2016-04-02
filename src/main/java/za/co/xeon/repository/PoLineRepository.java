package za.co.xeon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import za.co.xeon.domain.PoLine;

import org.springframework.data.jpa.repository.*;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.User;

import java.util.List;

/**
 * Spring Data JPA repository for the PoLine entity.
 */
public interface PoLineRepository extends JpaRepository<PoLine,Long> {
    Page<PoLine> findByPurchaseOrder(PurchaseOrder purchaseOrder, Pageable pageable);
}
