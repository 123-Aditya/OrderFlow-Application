package com.microservices.stockservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.microservices.stockservice.entity.OrderEventEntity;
import com.microservices.stockservice.repository.OrderEventRepository;

@RestController
@RequestMapping("/api/orders")
public class OrderEventController {

    @Autowired
    private OrderEventRepository orderEventRepository;

    @GetMapping
    public List<OrderEventEntity> getAllOrderEvents() {
        return orderEventRepository.findAll();
    }

    @GetMapping("/{id}")
    public OrderEventEntity getOrderById(@PathVariable Long id) {
        return orderEventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    @DeleteMapping
    public String deleteAllOrders() {
        orderEventRepository.deleteAll();
        return "All order events deleted successfully.";
    }
}
