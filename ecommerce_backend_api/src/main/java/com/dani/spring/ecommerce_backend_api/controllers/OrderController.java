package com.dani.spring.ecommerce_backend_api.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.dto.responses.OrderDetailRespondeDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.OrderResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.order.Order;
import com.dani.spring.ecommerce_backend_api.services.OrderService;
import com.dani.spring.ecommerce_backend_api.utils.OrderUtility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "8. Pedidos", description = "API para gestión de pedidos")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService service;

    //GET_ALL
    @Operation(summary = "Obtener todos los pedidos (solo para admins)")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Pedidos obtenidos correctamente",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderResponseDto.class))))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(){
        return ResponseEntity.ok(OrderUtility.getOrderResponseList(service.getAllOrders()));
    }

    //GET_MY_ALL
    @Operation(summary = "Obtener mis pedidos")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Pedidos obtenidos correctamente",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderResponseDto.class))))
    })
    @GetMapping("/my")
    public ResponseEntity<List<OrderResponseDto>> getAllMyOrders(Principal principal){
        return ResponseEntity.ok(OrderUtility.getOrderResponseList(service.getAllMyOrders(principal.getName())));
    }

    //GET_ONE
    @Operation(summary = "Obtener detalle de pedido")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Detalle del pedido correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetailRespondeDto.class))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado el pedido indicado", content = @Content)
    })
    @GetMapping("/my/{orderId}")
    public ResponseEntity<OrderDetailRespondeDto> getOrderDetail(@PathVariable Long orderId, Principal principal){
        //Obtenemos el pedido (si no exite devuelve un 404)
        Order order = service.getOrderById(orderId, principal.getName());
        return ResponseEntity.ok(OrderUtility.getOrderDetailResponse(order));
    }

}
