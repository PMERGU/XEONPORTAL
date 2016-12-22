package za.co.xeon.service;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.xeon.domain.Authority;
import za.co.xeon.domain.User;
import za.co.xeon.repository.AuthorityRepository;
import za.co.xeon.repository.UserRepository;
import za.co.xeon.security.SecurityUtils;
import za.co.xeon.service.util.RandomUtil;
import za.co.xeon.web.rest.dto.ManagedUserDTO;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

	private final Logger log = LoggerFactory.getLogger(UserService.class);

	@Inject
	private PasswordEncoder passwordEncoder;

	@Inject
	private UserRepository userRepository;

	@Inject
	private AuthorityRepository authorityRepository;

	public Optional<User> activateRegistration(String key) {
		log.debug("Activating user for activation key {}", key);
		userRepository.findOneByActivationKey(key).map(user -> {
			// activate given user for the registration key.
			user.setActivated(true);
			user.setActivationKey(null);
			userRepository.save(user);
			log.debug("Activated user: {}", user);
			return user;
		});
		return Optional.empty();
	}

	public Optional<User> completePasswordReset(String newPassword, String key) {
		log.debug("Reset user password for reset key {}", key);

		return userRepository.findOneByResetKey(key).filter(user -> {
			ZonedDateTime oneDayAgo = ZonedDateTime.now().minusHours(24);
			return user.getResetDate().isAfter(oneDayAgo);
		}).map(user -> {
			user.setPassword(passwordEncoder.encode(newPassword));
			user.setResetKey(null);
			user.setResetDate(null);
			userRepository.save(user);
			return user;
		});
	}

	public Optional<User> requestPasswordReset(String mail) {
		return userRepository.findOneByEmail(mail).filter(User::getActivated).map(user -> {
			user.setResetKey(RandomUtil.generateResetKey());
			user.setResetDate(ZonedDateTime.now());
			userRepository.save(user);
			return user;
		});
	}

	public User createUserInformation(String login, String password, String firstName, String lastName, String email,
			String langKey) {

		User newUser = new User();
		Authority authority = authorityRepository.findOne("ROLE_USER");
		Set<Authority> authorities = new HashSet<>();
		String encryptedPassword = passwordEncoder.encode(password);
		newUser.setLogin(login);
		// new user gets initially a generated password
		newUser.setPassword(encryptedPassword);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmail(email);
		newUser.setLangKey(langKey);
		// new user is not active
		newUser.setActivated(false);
		// new user gets registration key
		newUser.setActivationKey(RandomUtil.generateActivationKey());
		authorities.add(authority);
		newUser.setAuthorities(authorities);
		userRepository.save(newUser);
		log.debug("Created Information for User: {}", newUser);
		return newUser;
	}

	public User createUser(ManagedUserDTO managedUserDTO) {
		User user = new User();
		user.setLogin(managedUserDTO.getLogin().toLowerCase().trim());
		user.setFirstName(managedUserDTO.getFirstName());
		user.setLastName(managedUserDTO.getLastName());
		user.setEmail(managedUserDTO.getEmail().toLowerCase().trim());
		user.setCompany(managedUserDTO.getCompany());
		user.setCsu(managedUserDTO.getCsu());
		user.setFcSapId(managedUserDTO.getFcSapId().toUpperCase().trim());
		if (managedUserDTO.getLangKey() == null) {
			user.setLangKey("en"); // default language is English
		} else {
			user.setLangKey(managedUserDTO.getLangKey());
		}
		if (managedUserDTO.getAuthorities() != null) {
			Set<Authority> authorities = new HashSet<>();
			managedUserDTO.getAuthorities().stream()
					.forEach(authority -> authorities.add(authorityRepository.findOne(authority)));
			user.setAuthorities(authorities);
		}
		String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
		user.setPassword(encryptedPassword);
		user.setResetKey(RandomUtil.generateResetKey());
		user.setResetDate(ZonedDateTime.now());
		user.setActivated(true);
		user.setEnabled(true);
		userRepository.save(user);
		log.debug("Created Information for User: {}", user);
		return user;
	}

	public void updateUserInformation(String firstName, String lastName, String email, String langKey) {
		userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).ifPresent(u -> {
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setEmail(email);
			u.setLangKey(langKey);
			userRepository.save(u);
			log.debug("Changed Information for User: {}", u);
		});
	}

	@Transactional
	public void deleteUserInformation(String login) {
		userRepository.findOneByLogin(login).ifPresent(u -> {
			u.setEnabled(false);
			log.debug("Marked user as enabled false: {}", u);
		});
	}

	public void changePassword(String password) {
		userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).ifPresent(u -> {
			String encryptedPassword = passwordEncoder.encode(password);
			u.setPassword(encryptedPassword);
			userRepository.save(u);
			log.debug("Changed password for User: {}", u);
		});
	}

	@Transactional(readOnly = true)
	public Optional<User> getUserWithAuthoritiesByLogin(String login) {
		return userRepository.findOneByLogin(login).map(u -> {
			u.getAuthorities().size();
			return u;
		});
	}

	@Transactional(readOnly = true)
	public Optional<User> getUserWithFcSapId(String fcSapId) {
		return userRepository.findOneByFcSapId(fcSapId);
	}

	@Transactional(readOnly = true)
	public User getUserWithAuthorities(Long id) {
		User user = userRepository.findOne(id);
		user.getAuthorities().size(); // eagerly load the association
		return user;
	}

	@Transactional(readOnly = true)
	public User getUserWithAuthorities() {
		User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
		user.getAuthorities().size(); // eagerly load the association
		return user;
	}

	/**
	 * Not activated users should be automatically deleted after 3 days.
	 * <p/>
	 * <p>
	 * This is scheduled to get fired everyday, at 01:00 (am).
	 * </p>
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void removeNotActivatedUsers() {
		ZonedDateTime now = ZonedDateTime.now();
		List<User> users = userRepository
				.findAllByEnabledIsTrueAndActivatedIsFalseAndCreatedDateBefore(now.minusDays(3));
		for (User user : users) {
			log.debug("Deleting not activated user {}", user.getLogin());
			userRepository.delete(user);
		}
	}
}
