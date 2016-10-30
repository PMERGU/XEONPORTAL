package za.co.xeon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import za.co.xeon.domain.Company;
/**
 * Spring Data JPA repository for the Company entity.
 */
public interface CompanyRepository extends JpaRepository<Company,Long> {
    List<Company> findByNameStartingWithOrderByName(String name);
    Company findBySapId(String sapId);

    @Query("SELECT c FROM Company c WHERE c.type = 'XEON'")
    Company findXeon();

}
