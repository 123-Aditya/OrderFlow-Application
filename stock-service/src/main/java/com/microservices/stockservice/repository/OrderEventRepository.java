package com.microservices.stockservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservices.stockservice.entity.OrderEventEntity;

@Repository
public interface OrderEventRepository extends JpaRepository<OrderEventEntity, Long> {}
