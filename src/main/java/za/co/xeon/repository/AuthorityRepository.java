package za.co.xeon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.xeon.domain.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
