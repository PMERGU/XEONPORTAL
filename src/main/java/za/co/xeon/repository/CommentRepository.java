package za.co.xeon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import za.co.xeon.domain.Comment;
import za.co.xeon.domain.PurchaseOrder;

/**
 * Spring Data JPA repository for the PoLine entity.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
	Page<Comment> findByPurchaseOrder(PurchaseOrder purchaseOrder, Pageable pageable);

	Page<Comment> findByInternalIsFalseAndPurchaseOrder(PurchaseOrder purchaseOrder, Pageable pageable);
}
