package za.co.xeon.repository;

import org.springframework.data.domain.Pageable;
import za.co.xeon.domain.Company;

import org.springframework.data.jpa.repository.*;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.User;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 * Spring Data JPA repository for the Company entity.
 */
public interface CompanyRepository extends JpaRepository<Company,Long> {
    List<Company> findByNameStartingWithOrderByName(String name);
    Company findBySapId(String sapId);

    @Query("SELECT c FROM Company c WHERE c.type = 'XEON'")
    Company findXeon();

}
