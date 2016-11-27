package za.co.xeon.service;

import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.xeon.domain.Attachment;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.User;
import za.co.xeon.domain.dto.ReadAttachmentDto;
import za.co.xeon.exception.NoAttachmentoundException;
import za.co.xeon.external.as3.S3Service;
import za.co.xeon.external.as3.S3Settings;
import za.co.xeon.repository.AttachmentRepository;
import za.co.xeon.repository.UserRepository;
import za.co.xeon.security.SecurityUtils;

@Service
public class AttachmentService {

	private Logger log = LoggerFactory.getLogger(AttachmentService.class);

	private UserRepository userRepository;
	private AttachmentRepository attachmentRepository;
	private S3Settings s3Settings;
	private S3Service s3Service;

	@Autowired
	public AttachmentService(UserRepository userRepository, AttachmentRepository attachmentRepository, S3Settings s3Settings, S3Service s3Service) {
		this.userRepository = userRepository;
		this.attachmentRepository = attachmentRepository;
		this.s3Settings = s3Settings;
		this.s3Service = s3Service;
	}

	@Transactional
	public Attachment createAttachment(String deliveryNumber, String description, String category, String contentType, Boolean visible, File attachmentFile, User user) {
		deliveryNumber = org.apache.commons.lang.StringUtils.leftPad(deliveryNumber, 10, "0");
		Attachment attachment = new Attachment(deliveryNumber, description, category, user, visible);
		log.debug("Creating attachment: [{}]", attachment);

		s3Service.uploadFile(s3Settings.getAttachmentPath(deliveryNumber + "-" + attachmentFile.getName()), attachmentFile);
		attachment.setFileName(s3Settings.getAttachmentPath(deliveryNumber + "-" + attachmentFile.getName()));
		attachment.setMimeType(contentType);

		return attachmentRepository.save(attachment);
	}

	@Transactional
	public Attachment createAttachmentAgainstPO(PurchaseOrder po, String description, String category, String contentType, Boolean visible, File attachmentFile) {
		User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).orElse(new User());
		Attachment attachment = new Attachment(po, description, category, user, visible);
		log.debug("Creating attachment: [{}]", attachment);

		s3Service.uploadFile(s3Settings.getAttachmentPath(po.getPoNumber() + "-" + attachmentFile.getName()), attachmentFile);
		attachment.setFileName(s3Settings.getAttachmentPath(po.getPoNumber() + "-" + attachmentFile.getName()));
		attachment.setMimeType(contentType);

		return attachmentRepository.save(attachment);
	}

	public ReadAttachmentDto readAttachment(final String uuid, final Boolean activated) {
		log.debug("Reading attachment with UUID: [{}]", uuid);
		return attachmentRepository.findByUuidAndActivated(uuid, activated).map(attachment -> {
			ReadAttachmentDto readAttachment = null;
			try {
				readAttachment = new ReadAttachmentDto(attachment.getMimeType(), s3Service.retrieveFile(attachment.getFileName()));
				log.debug("Read attachment: [{}]", readAttachment);
			} catch (IOException e) {
				log.error(format("Could not retrieve attachment: [%s]", attachment), e);
			}

			return readAttachment;
		}).orElseThrow(NoAttachmentoundException::new);
	}

	@Transactional
	public void deleteAttachment(final String uuid) {
		attachmentRepository.findByUuidAndActivated(uuid).ifPresent(attachment -> attachment.setActivated(false));
	}

	public List<Attachment> listAttachments(String deliveryNumber, final Boolean visible) {
		List<Attachment> attachments;

		if (visible != null) {
			attachments = attachmentRepository.findByDeliveryNumberAndVisibleAndActivated(deliveryNumber, visible);
		} else {
			attachments = attachmentRepository.findByDeliveryNumberAndActivated(deliveryNumber);
		}

		return attachments;
	}
}
