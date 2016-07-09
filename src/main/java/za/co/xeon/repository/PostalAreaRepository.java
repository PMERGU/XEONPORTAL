package za.co.xeon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import za.co.xeon.domain.Company;
import za.co.xeon.domain.Party;
import za.co.xeon.domain.PostalArea;

/**
 * Spring Data JPA repository for the Party entity.
 */
public interface PostalAreaRepository extends JpaRepository<PostalArea,Long>, QueryDslPredicateExecutor<PostalArea> {

}
