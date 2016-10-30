package za.co.xeon.service;

import java.util.Locale;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import za.co.xeon.config.JHipsterProperties;
import za.co.xeon.domain.Comment;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.User;
import za.co.xeon.domain.enumeration.CompanyType;
import za.co.xeon.repository.UserRepository;

/**
 * Service for sending e-mails.
 * <p/>
 * <p>
 * We use the @Async annotation to send e-mails asynchronously.
 * </p>
 */
@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    @Inject
    private JHipsterProperties jHipsterProperties;

    @Inject
    private JavaMailSenderImpl javaMailSender;

    @Inject
    private MessageSource messageSource;

    @Inject
    private SpringTemplateEngine templateEngine;

    @Inject
    private UserRepository userRepository;

    /**
     * System default email address that sends the e-mails.
     */
    private String from;

    @Async
    private void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send e-mail[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={} from '{}'",
            isMultipart, isHtml, to, subject, content, jHipsterProperties.getMail().getFrom());

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setTo(to);
            message.setFrom(jHipsterProperties.getMail().getFrom());
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Sent e-mail to User '{}'", to);
        } catch (Exception e) {
            log.warn("E-mail could not be sent to user '{}', exception is: {}", to, e.getMessage());
        }
    }

    @Async
    public void sendActivationEmail(User user, String baseUrl) {
        log.debug("Sending activation e-mail to '{}'", user.getEmail());
        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable("user", user);
        context.setVariable("baseUrl", baseUrl);
        String content = templateEngine.process("activationEmail", context);
        String subject = messageSource.getMessage("email.activation.title", null, locale);
        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendCreationEmail(User user, String baseUrl) {
        log.debug("Sending creation e-mail to '{}'", user.getEmail());
        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable("user", user);
        context.setVariable("baseUrl", baseUrl);
        String content = templateEngine.process("creationEmail", context);
        String subject = messageSource.getMessage("email.activation.title", null, locale);
        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendPasswordResetMail(User user, String baseUrl) {
        log.debug("Sending password reset e-mail to '{}'", user.getEmail());
        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable("user", user);
        context.setVariable("baseUrl", baseUrl);
        String content = templateEngine.process("passwordResetEmail", context);
        String subject = messageSource.getMessage("email.reset.title", null, locale);
        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendPoProcessedMail(User processedBy, PurchaseOrder purchaseOrder, String baseUrl, boolean doNotCopyCsu) {
        log.debug("Sending PO processed e-mail to '{}'", purchaseOrder.getUser().getEmail());
        Locale locale = Locale.forLanguageTag(purchaseOrder.getUser().getLangKey());
        Context context = new Context(locale);
        context.setVariable("user", purchaseOrder.getUser());
        context.setVariable("processedBy", processedBy);
        context.setVariable("baseUrl", baseUrl);
        context.setVariable("purchaseOrder", purchaseOrder);
        String content = templateEngine.process("poProcessedEmail", context);
        String subject = messageSource.getMessage("email.poProcessed.title", null, locale);
        sendEmail(purchaseOrder.getUser().getEmail(), subject, content, false, true);
        if(!doNotCopyCsu && purchaseOrder.getUser().getCsu() != null) {
            sendEmail(purchaseOrder.getUser().getCsu().getEmail(), "CSU agent : " + subject, content, false, true);
        }
    }

    @Async
    public void sendPoCommentMail(User processedBy, PurchaseOrder purchaseOrder, String baseUrl) {
        log.debug("Sending PO comment e-mail to '{}'", purchaseOrder.getUser().getEmail());
        Locale locale = Locale.forLanguageTag(purchaseOrder.getUser().getLangKey());
        Context context = new Context(locale);
        context.setVariable("user", purchaseOrder.getUser());
        context.setVariable("processedBy", processedBy);
        context.setVariable("baseUrl", baseUrl);
        context.setVariable("purchaseOrder", purchaseOrder);
        String content = templateEngine.process("poCommentEmail", context);
        String subject = messageSource.getMessage("email.poComment.title", null, locale);
        sendEmail(purchaseOrder.getUser().getEmail(), subject, content, false, true);
        if(purchaseOrder.getUser().getCsu() != null) {
            sendEmail(purchaseOrder.getUser().getCsu().getEmail(), "CSU agent : " + subject, content, false, true);
        }
    }

    @Async
    public void sendOrderCommentMail(Comment comment) {
        log.debug("Sending order comment e-mail from '{}'", comment.getPurchaseOrder().getUser().getEmail());
        Locale locale = Locale.forLanguageTag(comment.getPurchaseOrder().getUser().getLangKey());
        Context context = new Context(locale);
        User fromUser = comment.getUser();
        boolean isFromXeon = fromUser.getCompany().getType().equals(CompanyType.XEON);

        context.setVariable("to", isFromXeon ? comment.getPurchaseOrder().getUser().getFirstName() : "CSU Team");
        context.setVariable("from", comment.getUser());
        context.setVariable("comment", comment.getMessage());
        context.setVariable("purchaseOrder", comment.getPurchaseOrder());
        String content = templateEngine.process("orderCommentEmail", context);
        String subject = "Xeon portal : Comment has been added to PO [" + comment.getPurchaseOrder().getPoNumber() + "] by " + comment.getUser().getFirstName() + " " + comment.getUser().getLastName();
        if(isFromXeon){
            sendEmail(comment.getPurchaseOrder().getUser().getEmail(), subject, content, false, true);
            if(comment.getPurchaseOrder().getUser().getCsu() != null) {
                sendEmail(comment.getPurchaseOrder().getUser().getCsu().getEmail(), "CSU agent : " + subject, content, false, true);
            }
        }else {
            subject= subject + " from " + comment.getUser().getCompany().getName();
            sendEmail(messageSource.getMessage("email.csu.emailAddress", null, locale), subject, content, false, true);
        }
    }

    @Async
    public void sendCSUMail(User processedBy, String title, String message, String link, String linkText, String baseUrl) {
        log.debug("Sending csu e-mail");
        Locale locale = Locale.forLanguageTag(processedBy.getLangKey());
        Context context = new Context(locale);
        context.setVariable("processedBy", processedBy);
        context.setVariable("title", title);
        context.setVariable("baseUrl", baseUrl);
        context.setVariable("message", message);
        if(link != null) {
            context.setVariable("link", link);
            context.setVariable("linkText", linkText);
        }
        String content = templateEngine.process("csuEmail", context);
        sendEmail(messageSource.getMessage("email.csu.emailAddress", null, locale), title, content, false, true);
    }

}
