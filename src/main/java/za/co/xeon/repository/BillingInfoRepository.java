package za.co.xeon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import za.co.xeon.domain.BillingInfo;

/**
 * Spring Data JPA repository for the Party entity.
 */
public interface BillingInfoRepository extends JpaRepository<BillingInfo,Long>, QueryDslPredicateExecutor<BillingInfo> {
	
	@Query("select a from BillingInfo a where a.locationString like ?1 and a.ecoMin <= ?2 and a.ecoMax >= ?2")
    BillingInfo findByLocationString(String locationString, Double d);

}
