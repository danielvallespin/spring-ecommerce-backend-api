package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;

import com.dani.spring.ecommerce_backend_api.entities.order.Order;

public interface OrderService {

    List<Order> getAllOrders();

    List<Order> getAllMyOrders(String username);

    Order getOrderById(Long orderId, String username);

}
