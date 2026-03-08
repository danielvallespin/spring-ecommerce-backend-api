package com.dani.spring.ecommerce_backend_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dani.spring.ecommerce_backend_api.entities.order.OrderItem;
import com.dani.spring.ecommerce_backend_api.entities.order.OrderItemId;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId>{

}
