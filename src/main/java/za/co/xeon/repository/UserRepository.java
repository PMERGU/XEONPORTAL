package za.co.xeon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import za.co.xeon.domain.Authority;
import za.co.xeon.domain.Company;
import za.co.xeon.domain.User;

import java.time.ZonedDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByActivationKey(String activationKey);

    Page<User> findAllByEnabledIsTrue(Pageable pageable);

    List<User> findAllByEnabledIsTrueAndAuthorities(Authority authority);

    List<User> findAllByEnabledIsTrueAndCompany(Company company);

    List<User> findAllByEnabledIsTrueAndActivatedIsFalseAndCreatedDateBefore(ZonedDateTime dateTime);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByLogin(String login);

    Optional<User> findOneByEnabledIsTrueAndEmail(String email);

    Optional<User> findOneByEnabledIsTrueAndLogin(String login);

    Optional<User> findOneById(Long userId);

    @Override
    void delete(User t);

}
