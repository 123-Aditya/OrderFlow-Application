package com.microservices.emailservice.service;

import java.util.List;
import java.util.Map;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendOrderEmailToAll(List<String> recipients, String subject, Map<String, Object> model) {
        for (String email : recipients) {
            sendOrderEmail(email.trim(), subject, model);
        }
    }

    private void sendOrderEmail(String to, String subject, Map<String, Object> model) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);

            Context context = new Context();
            context.setVariables(model);

            String htmlContent = templateEngine.process("order-email.html", context);
            helper.setText(htmlContent, true); // true = HTML

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
