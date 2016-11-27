package za.co.xeon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import za.co.xeon.domain.PoLine;
import za.co.xeon.domain.PurchaseOrder;

/**
 * Spring Data JPA repository for the PoLine entity.
 */
public interface PoLineRepository extends JpaRepository<PoLine, Long> {
	Page<PoLine> findByPurchaseOrder(PurchaseOrder purchaseOrder, Pageable pageable);
}
