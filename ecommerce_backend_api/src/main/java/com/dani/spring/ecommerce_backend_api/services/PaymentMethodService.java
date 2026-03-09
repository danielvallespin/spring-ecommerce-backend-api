package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;

import com.dani.spring.ecommerce_backend_api.dto.requests.PaymentMethodRequestDto;
import com.dani.spring.ecommerce_backend_api.entities.payment_method.PaymentMethod;
import com.dani.spring.ecommerce_backend_api.entities.user.User;

public interface PaymentMethodService {

    List<PaymentMethod> getAllUserPaymentMethods(String username);
    
    List<PaymentMethod> getAllUserPaymentMethods(User user);

    List<PaymentMethod> getUserPaymentMethodsByIds(User user, List<Long> ids);

    PaymentMethod getPaymentMethodById(Long paymentId, String username);

    PaymentMethod getByUserAndLast4(String last4, String username);

    PaymentMethod createPaymentMethod(PaymentMethodRequestDto request, String username);

    PaymentMethod savePaymentMethod(PaymentMethod paymentMethod);

    PaymentMethod setPaymentAsDefault(Long paymentId, String username);

    void disbaleDefaultFromOtherPayments(String username);

    void disbaleDefaultFromOtherPayments(User user);

    void disablePaymentMethod(Long paymentId, String username);

}
