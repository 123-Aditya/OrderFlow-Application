package com.microservices.emailservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.microservices.basedomains.dto.OrderEvent;
import com.microservices.emailservice.service.EmailService;
import com.microservices.emailservice.config.EmailRecipientsConfig;

@Service
public class OrderConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(OrderConsumer.class);

	@Autowired
	private EmailService emailService;

	@Autowired
	private EmailRecipientsConfig emailRecipientsConfig;

	@KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(OrderEvent event) {
		LOG.info("Order event received in Email service: {}", event);

		String subject = "New Order received";
		String message = "Order Details:\n" + event.toString();

		emailService.sendEmailToAll(emailRecipientsConfig.getEmails(), subject, message);

		LOG.info("Email sent to all recipients.");
	}
}
