package za.co.xeon.repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import za.co.xeon.domain.Authority;
import za.co.xeon.domain.Company;
import za.co.xeon.domain.User;

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

	Optional<User> findOneByFcSapId(String fcSapId);

	Optional<User> findOneByEmail(String email);

	Optional<User> findOneByLogin(String login);

	Optional<User> findOneByEnabledIsTrueAndEmail(String email);

	Optional<User> findOneByEnabledIsTrueAndLogin(String login);

	Optional<User> findOneById(Long userId);

	@Override
	void delete(User t);

}
