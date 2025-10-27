package com.microservices.orderservice.contoller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.basedomains.dto.Order;
import com.microservices.basedomains.dto.OrderEvent;
import com.microservices.orderservice.kafka.OrderProducer;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	private OrderProducer orderProducer;

	public OrderController(OrderProducer orderProducer) {
		this.orderProducer = orderProducer;
	}
	
	@PostMapping
	public String placeOrder(@RequestBody Order order) {
		String msg = null;
		
		try {
			order.setOrderId(UUID.randomUUID().toString());
			
			OrderEvent orderEvent = new OrderEvent();
			orderEvent.setStatus("PENDING");
			orderEvent.setMessage("Order status is in pending state");
			orderEvent.setOrder(order);
			
			orderProducer.sendMessage(orderEvent);
			
			msg = "Order placed successfully!";
		}
		catch(Exception e) {
			msg = "Error occured: " + e.getMessage();
		}
		
		return msg;
	}
	
}
