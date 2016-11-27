package za.co.xeon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import za.co.xeon.domain.PostalArea;

/**
 * Spring Data JPA repository for the Party entity.
 */
public interface PostalAreaRepository extends JpaRepository<PostalArea, Long>, QueryDslPredicateExecutor<PostalArea> {

}
