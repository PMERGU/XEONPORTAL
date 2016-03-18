package za.co.xeon.repository;

import za.co.xeon.domain.PoLine;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PoLine entity.
 */
public interface PoLineRepository extends JpaRepository<PoLine,Long> {

}
