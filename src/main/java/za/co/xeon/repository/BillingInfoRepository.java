package za.co.xeon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import za.co.xeon.domain.BillingInfo;
import za.co.xeon.domain.Comment;

/**
 * Spring Data JPA repository for the Party entity.
 */
public interface BillingInfoRepository extends JpaRepository<Comment, Long> {

	@Query("select a from BillingInfo a where a.locationString like ?1 and a.ecoMin <= ?2 and a.ecoMax >= ?2")
	BillingInfo findByLocationString(String locationString, Double d);

	@Query("select a from BillingInfo a where a.locationString like ?1 and a.ecoMin <= ?2 and a.ecoMax >= ?2 and a.modeOfTrans = ?3")
	BillingInfo findByLocationStringAndMOT(String locationString, Double d, String modeOfTrans);

}
