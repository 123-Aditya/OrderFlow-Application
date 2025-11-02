package com.microservices.emailservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.microservices.basedomains.dto.OrderEvent;

@Service
public class OrderConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(OrderConsumer.class);
	
	@KafkaListener(topics="${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(OrderEvent event) {
		
		LOG.info(String.format("Order event received in Stock service: ", event));
		
		// send an email to the customers
	}
}
