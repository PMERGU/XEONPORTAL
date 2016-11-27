package za.co.xeon.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.xeon.config.audit.AuditEventConverter;
import za.co.xeon.domain.PersistentAuditEvent;
import za.co.xeon.repository.PersistenceAuditEventRepository;

/**
 * Service for managing audit events.
 * <p/>
 * <p>
 * This is the default implementation to support SpringBoot Actuator
 * AuditEventRepository
 * </p>
 */
@Service
@Transactional
public class AuditEventService {

	private PersistenceAuditEventRepository persistenceAuditEventRepository;

	private AuditEventConverter auditEventConverter;

	@Inject
	public AuditEventService(PersistenceAuditEventRepository persistenceAuditEventRepository, AuditEventConverter auditEventConverter) {

		this.persistenceAuditEventRepository = persistenceAuditEventRepository;
		this.auditEventConverter = auditEventConverter;
	}

	public List<AuditEvent> findAll() {
		return auditEventConverter.convertToAuditEvent(persistenceAuditEventRepository.findAll());
	}

	public List<AuditEvent> findByDates(LocalDateTime fromDate, LocalDateTime toDate) {
		List<PersistentAuditEvent> persistentAuditEvents = persistenceAuditEventRepository.findAllByAuditEventDateBetween(fromDate, toDate);

		return auditEventConverter.convertToAuditEvent(persistentAuditEvents);
	}

	public Optional<AuditEvent> find(Long id) {
		return Optional.ofNullable(persistenceAuditEventRepository.findOne(id)).map(auditEventConverter::convertToAuditEvent);
	}
}
