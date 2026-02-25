package com.dani.spring.ecommerce_backend_api.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.dto.responses.CartResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.Cart;
import com.dani.spring.ecommerce_backend_api.services.CartService;
import com.dani.spring.ecommerce_backend_api.utils.CartUtility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Carrito de compra", description = "API para la gestión del carrito")
@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService service;

    @Operation(summary = "Obtener tu carrito de compra")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Carrito obtenido correctamente",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = CartResponseDto.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping
    public CartResponseDto getUserCart(Principal principal){
        Optional<Cart> optUserCart = service.getUserCart(principal.getName());
        Cart userCart = CartUtility.getCartFromOptionalOrThrow(optUserCart);
        return CartUtility.getCartResponse(userCart);
    }

}
