package com.microservices.stockservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import com.microservices.stockservice.entity.OrderEventEntity;
import com.microservices.stockservice.repository.OrderEventRepository;

@RestController
@RequestMapping("/api/orders")
public class OrderEventController {

    @Autowired
    private OrderEventRepository orderEventRepository;

    @GetMapping
    public Page<OrderEventEntity> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "eventTime") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                                                    : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        if (status != null && !status.isBlank()) {
            return orderEventRepository.findByStatusIgnoreCase(status, pageable);
        } else {
            return orderEventRepository.findAll(pageable);
        }
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
