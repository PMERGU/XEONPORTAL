package za.co.xeon.repository;

import za.co.xeon.domain.Party;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Party entity.
 */
public interface PartyRepository extends JpaRepository<Party,Long> {

}
