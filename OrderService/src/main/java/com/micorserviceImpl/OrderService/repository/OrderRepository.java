package com.micorserviceImpl.OrderService.repository;

import com.micorserviceImpl.OrderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
