package com.microservices.stockservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_events")
public class OrderEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;
    private String productName;
    private int quantity;
    private double price;
    private String status;

    private LocalDateTime eventTime;

    public OrderEventEntity() {}

    public OrderEventEntity(String orderId, String productName, int quantity, double price, String status, LocalDateTime eventTime) {
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.eventTime = eventTime;
    }

    public Long getId() { 
    	return id; 
    }

    public String getOrderId() { 
    	return orderId; 
    }
    
    public void setOrderId(String orderId) { 
    	this.orderId = orderId; 
    }

    public String getProductName() { 
    	return productName; 
    }
    
    public void setProductName(String productName) { 
    	this.productName = productName; 
    }

    public int getQuantity() { 
    	return quantity; 
    }
    
    public void setQuantity(int quantity) { 
    	this.quantity = quantity; 
    }

    public double getPrice() { 
    	return price; 
    }
    
    public void setPrice(double price) { 
    	this.price = price; 
    }

    public String getStatus() { 
    	return status; 
    }
    
    public void setStatus(String status) { 
    	this.status = status; 
    }

    public LocalDateTime getEventTime() { 
    	return eventTime; 
    }
    
    public void setEventTime(LocalDateTime eventTime) { 
    	this.eventTime = eventTime; 
    }
}
