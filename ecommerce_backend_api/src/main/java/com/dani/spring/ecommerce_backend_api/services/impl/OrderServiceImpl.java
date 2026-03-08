package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dani.spring.ecommerce_backend_api.entities.order.Order;
import com.dani.spring.ecommerce_backend_api.entities.user.User;
import com.dani.spring.ecommerce_backend_api.repositories.OrderItemRepository;
import com.dani.spring.ecommerce_backend_api.repositories.OrderPaymentRepository;
import com.dani.spring.ecommerce_backend_api.repositories.OrderRepository;
import com.dani.spring.ecommerce_backend_api.repositories.UserRepository;
import com.dani.spring.ecommerce_backend_api.services.OrderService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository repository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderPaymentRepository orderPaymentRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly=true)
    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    
    @Override
    public List<Order> getAllMyOrders(String username) {
        User user = getUserByUsername(username);
        return repository.findByUser(user);
    }

    @Transactional(readOnly=true)
    @Override
    public Order getOrderById(Long orderId, String username) {
        User user = getUserByUsername(username);
        Order order = repository.findByIdAndUserOrderByCreatedAtDesc(orderId, user);
        //Si el usuario no tiene este order devolvemos un 404
        if (order == null){
            throw new EntityNotFoundException("No se ha encontrado el pedido indicado");
        }

        return order;
    }



    @Transactional(readOnly=true)
    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }


}
