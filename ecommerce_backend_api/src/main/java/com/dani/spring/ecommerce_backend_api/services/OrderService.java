package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;

import com.dani.spring.ecommerce_backend_api.dto.requests.OrderRequestDto;
import com.dani.spring.ecommerce_backend_api.entities.addresses.Address;
import com.dani.spring.ecommerce_backend_api.entities.cart.Cart;
import com.dani.spring.ecommerce_backend_api.entities.order.Order;
import com.dani.spring.ecommerce_backend_api.entities.payment_method.PaymentMethod;
import com.dani.spring.ecommerce_backend_api.entities.user.User;

public interface OrderService {

    List<Order> getAllOrders();

    List<Order> getAllMyOrders(String username);

    Order getOrderById(Long orderId, String username);

    Order createOrder(User user, Cart cart, Address address, List<PaymentMethod> paymentMethods, OrderRequestDto request);

}
