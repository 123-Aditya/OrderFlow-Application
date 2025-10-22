package com.project.javakafka;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	private final KafkaProducerService producerService;
	
	
	public HelloWorldController(KafkaProducerService producerService) {
		this.producerService = producerService;
	}
	

	@RequestMapping("/send")
	public String sendMessage(@RequestParam("message") String message) {
		producerService.sendMessage(message);
		return "Message sent to Kafka topic: " + message;
	}
}
