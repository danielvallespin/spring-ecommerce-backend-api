package com.dani.spring.ecommerce_backend_api.utils;

import java.util.ArrayList;
import java.util.List;

import com.dani.spring.ecommerce_backend_api.dto.requests.PaymentMethodRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.PaymentMethodResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.payment_method.PaymentMethod;
import com.dani.spring.ecommerce_backend_api.entities.user.User;

public class PaymentMethodUtility {

    /**
     * Transforma un objeto PaymentMethod en una respuesta PaymentMethodResponseDto
     * @param payment
     * @return
     */
    public static PaymentMethodResponseDto getPaymentMethodRespone(PaymentMethod payment){
        return new PaymentMethodResponseDto(
            payment.getId(),
            payment.getUser().getId(),
            payment.getType(),
            payment.getLast4(),
            payment.getExpiryMonth(),
            payment.getExpiryYear(),
            payment.isDefault(),
            payment.isEnabled()
        );
    }

    /**
     * Transforma una lista de PaymentMethod en una lista de respuesta PaymentMethodResponseDto
     * @param payments
     * @return
     */
    public static List<PaymentMethodResponseDto> getPaymentMethodResponeList(List<PaymentMethod> payments){
        List<PaymentMethodResponseDto> response = new ArrayList<>();
        for (PaymentMethod payment : payments){
            response.add(getPaymentMethodRespone(payment));
        }

        return response;
    }

    public static PaymentMethod getPaymentMethod(PaymentMethodRequestDto request, User user){
        return new PaymentMethod(
            user,
            request.getType(),
            request.getLast4(),
            request.getExpiryMonth(),
            request.getExpiryYear(),
            request.isDefault()
        );
    }

}
