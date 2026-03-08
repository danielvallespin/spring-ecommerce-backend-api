package com.dani.spring.ecommerce_backend_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dani.spring.ecommerce_backend_api.entities.order.Order;
import com.dani.spring.ecommerce_backend_api.entities.user.User;


public interface OrderRepository extends JpaRepository<Order, Long>{
    
    List<Order> findByUser(User user);

    Order findByIdAndUserOrderByCreatedAtDesc(Long id, User user);

}
