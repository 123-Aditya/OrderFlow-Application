package com.microservices.emailservice.kafka;

import java.util.HashMap;
import java.util.Map;

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

        Map<String, Object> model = new HashMap<>();
        model.put("orderId", event.getOrder().getOrderId());
        model.put("productName", event.getOrder().getName());
        model.put("quantity", event.getOrder().getQty());
        model.put("price", event.getOrder().getPrice());
        model.put("status", event.getStatus());

        String subject = "Order Update - ID: " + event.getOrder().getOrderId();

        try {
			emailService.sendOrderEmailToAll(emailRecipientsConfig.getEmails(), subject, model);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

        LOG.info("HTML Email sent to all recipients for order {}", event.getOrder().getOrderId());
    }
}
