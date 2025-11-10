package com.microservices.stockservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.microservices.basedomains.dto.OrderEvent;
import com.microservices.stockservice.entity.OrderEventEntity;
import com.microservices.stockservice.repository.OrderEventRepository;

import java.time.LocalDateTime;

@Service
public class OrderConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(OrderConsumer.class);

    @Autowired
    private OrderEventRepository orderEventRepository;

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent event) {
        LOG.info("Order event received in Stock service: {}", event);

        try {
            OrderEventEntity entity = new OrderEventEntity(
                    event.getOrder().getOrderId(),
                    event.getOrder().getName(),
                    event.getOrder().getQty(),
                    event.getOrder().getPrice(),
                    event.getStatus(),
                    LocalDateTime.now()
            );

            orderEventRepository.save(entity);
            LOG.info("Order event saved to database: {}", event.getOrder().getOrderId());
        } catch (Exception e) {
            LOG.error("Failed to save order event: {}", e.getMessage(), e);
        }
    }
}
