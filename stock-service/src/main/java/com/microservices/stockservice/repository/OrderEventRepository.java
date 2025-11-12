package com.microservices.stockservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservices.stockservice.entity.OrderEventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface OrderEventRepository extends JpaRepository<OrderEventEntity, Long> {
	
    Page<OrderEventEntity> findByStatusIgnoreCase(String status, Pageable pageable);

}
