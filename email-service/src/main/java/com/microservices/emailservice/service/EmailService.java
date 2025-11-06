package com.microservices.emailservice.service;

import java.util.List;
import java.util.Map;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.springframework.scheduling.annotation.Async;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

@Service
public class EmailService {
	
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * Send email asynchronously to all recipients with retry logic.
     * @throws Exception 
     */
    @Async("emailExecutor")
    public void sendOrderEmailToAll(List<String> recipients, String subject, Map<String, Object> model) throws Exception {
        for (String email : recipients) {
            sendOrderEmailWithRetry(email.trim(), subject, model);
        }
    }

    /**
     * Retry sending each email up to 3 times with 2-second delay between attempts.
     * @throws Exception 
     */
    @Retryable(
        value = { MailException.class, jakarta.mail.MessagingException.class },
        maxAttempts = 3,
        backoff = @Backoff(delay = 2000))
    public void sendOrderEmailWithRetry(String sendToRecipient, String subject, Map<String, Object> model) throws Exception {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(sendToRecipient);
            helper.setSubject(subject);

            Context context = new Context();
            context.setVariables(model);

            String htmlContent = templateEngine.process("order-email.html", context);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            LOG.info("Email sent successfully to: ", sendToRecipient);

        } catch (MailException | jakarta.mail.MessagingException e) {
            LOG.error("Email sending failed to: ",e.getMessage());
            throw e; // rethrow so Retryable catches it
        }
    }

    /**
     * Fallback (called after retries are exhausted)
     */
    @Recover
    public void recover(MailException e, String to, String subject, Map<String, Object> model) {
    	LOG.error("Email permanently failed after retries for: ", e.getMessage());
    }
}
