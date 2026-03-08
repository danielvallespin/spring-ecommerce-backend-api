package com.dani.spring.ecommerce_backend_api.utils;

import java.util.ArrayList;
import java.util.List;

import com.dani.spring.ecommerce_backend_api.dto.responses.OrderDetailRespondeDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.OrderItemResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.OrderPaymentResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.OrderResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.order.Order;
import com.dani.spring.ecommerce_backend_api.entities.order.OrderItem;
import com.dani.spring.ecommerce_backend_api.entities.order.OrderPayment;

public class OrderUtility {

    /**
     * Metodo que convierte una Order en una OrderResponseDto
     * @param order
     * @return
     */
    public static OrderResponseDto getOrderResponse(Order order){
        return new OrderResponseDto(
            order.getId(),
            order.getUser().getId(),
            order.getAmount(),
            order.getStatus(),
            order.getCreatedAt()
        );
    }


    /**
     * Metodo que transdorma una lista de Orders en una lista de OrderResponseDto
     * @param orders
     * @return
     */
    public static List<OrderResponseDto> getOrderResponseList(List<Order> orders){
        List<OrderResponseDto> response = new ArrayList<>();
        for (Order order : orders){
            response.add(getOrderResponse(order));
        }

        return response;
    }

    /**
     * Metodo que convierte una lista de OrderItem en una lista de OrderItemResponseDto
     * @param items
     * @return
     */
    public static List<OrderItemResponseDto> getOrderItemResponse(List<OrderItem> items){
        List<OrderItemResponseDto> response = new ArrayList<>();
        for (OrderItem item : items){
            response.add(
                new OrderItemResponseDto(
                    item.getProduct().getId(),
                    item.getProduct().getName(),
                    item.getProduct().getDescription(),
                    item.getProduct().getImageUrl(),
                    item.getProduct().getDetail().getBrand(),
                    item.getProduct().getPrice(),
                    item.getQuantity()
                )
            );
        }

        return response;
    }

    /**
     * Metodo que convierte una lista de OrderPayment en una lista de OrderPaymentResponseDto
     * @param orderPayments
     * @return
     */
    public static List<OrderPaymentResponseDto> getOrderPaymentResponse(List<OrderPayment> orderPayments){
        List<OrderPaymentResponseDto> response = new ArrayList<>();
        for (OrderPayment payment : orderPayments){
            response.add(
                new OrderPaymentResponseDto(
                    payment.getPaymentMethod().getId(),
                    payment.getPaymentMethod().getLast4(),
                    payment.getAmount()
                )
            );
        }

        return response;
    }

    /**
     * Metodo que convierte una Order en un OrderDetailRespondeDto
     * @param order
     * @return
     */
    public static OrderDetailRespondeDto getOrderDetailResponse(Order order){
        return new OrderDetailRespondeDto(
            getOrderResponse(order),
            getOrderItemResponse(order.getItems()),
            getOrderPaymentResponse(order.getPayments())
        );
    }

}
