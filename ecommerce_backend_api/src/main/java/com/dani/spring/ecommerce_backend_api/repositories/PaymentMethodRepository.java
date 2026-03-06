package com.dani.spring.ecommerce_backend_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dani.spring.ecommerce_backend_api.entities.payment_method.PaymentMethod;
import com.dani.spring.ecommerce_backend_api.entities.user.User;


public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    List<PaymentMethod> findByUser(User user);

    PaymentMethod findByUserAndId(User user, Long paymentId);

    PaymentMethod findByUserAndLast4(User user, String last4);

}
