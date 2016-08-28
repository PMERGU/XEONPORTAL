package za.co.xeon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.parser.Part;
import za.co.xeon.domain.Company;
import za.co.xeon.domain.Party;

import org.springframework.data.jpa.repository.*;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.User;
import za.co.xeon.domain.enumeration.PartyType;

import java.util.List;

/**
 * Spring Data JPA repository for the Party entity.
 */
public interface PartyRepository extends JpaRepository<Party,Long> {
    Page<Party> findByCompany(Company company, Pageable pageable);
    Party findFirstByName(String name);
    Page<Party> findByType(PartyType type, Pageable pageable);
    Page<Party> findByCompanyAndType(Company company, PartyType type, Pageable pageable);
}
