package com.einfonet.programmingtechie.orderservice.repository;

import com.einfonet.programmingtechie.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{
}
