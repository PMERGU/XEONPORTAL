package za.co.xeon.repository;

import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import za.co.xeon.domain.Company;
import za.co.xeon.domain.PostalArea;
import za.co.xeon.domain.PurchaseOrder;

import org.springframework.data.jpa.repository.*;
import za.co.xeon.domain.User;
import za.co.xeon.domain.enumeration.PoState;

import java.util.List;

/**
 * Spring Data JPA repository for the PurchaseOrder entity.
 */
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long>, QueryDslPredicateExecutor<PurchaseOrder> {
    Page<PurchaseOrder> findByUser(User user, Pageable pageable);
//    @Query("select po from PurchaseOrder po where po.user = ?1 and po.poNumber like ?2")
//    Page<PurchaseOrder> findByUserAndPoNumber(User user, String poNumber, Pageable pageable);
    List<PurchaseOrder> findByUser(User user);
    List<PurchaseOrder> findByUserId_Company(Company company);
    Page<PurchaseOrder> findByUserAndState(User user, PoState poState, Pageable pageable);
    Page<PurchaseOrder> findByState(PoState poState, Pageable pageable);
    PurchaseOrder findFirstByPoNumber(String poNumber);
    PurchaseOrder findFirstByUserAndPoNumber(User user, String poNumber);
}

