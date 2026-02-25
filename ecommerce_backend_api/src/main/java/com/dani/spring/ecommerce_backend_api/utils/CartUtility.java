package com.dani.spring.ecommerce_backend_api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dani.spring.ecommerce_backend_api.dto.responses.CartItemResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.CartResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.ProductCartResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.Cart;
import com.dani.spring.ecommerce_backend_api.entities.CartItem;
import com.dani.spring.ecommerce_backend_api.entities.Product;

import jakarta.persistence.EntityNotFoundException;

public class CartUtility {

    /**
     * Metodo para obtener el objeto dto respuesta del carrito
     *
     * @param cart
     * @return CartResponseDto
     */
    public static CartResponseDto getCartResponse(Cart cart) {
        List<CartItemResponseDto> items = getCartItemsListResponse(cart.getItems());
        return new CartResponseDto(cart.getId(), cart.getUser().getId(), items);
    }

    /**
     * Metodo para obtener el objeto dto respuesta de los items del carrito
     *
     * @param items
     * @return List<CartItemResponseDto>
     */
    private static List<CartItemResponseDto> getCartItemsListResponse(List<CartItem> items) {
        List<CartItemResponseDto> response = new ArrayList<>();
        for (CartItem item : items) {
            response.add(new CartItemResponseDto(getProductCartResponse(item.getProduct()), item.getQuantity()));
        }

        return response;
    }

    /**
     * Metodo para obtener objeto dto respuesta de producto del carrito
     *
     * @param product
     * @return ProductCartResponseDto
     */
    private static ProductCartResponseDto getProductCartResponse(Product product) {
        return new ProductCartResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImageUrl(),
                product.getDetail().getBrand()
        );
    }

    /**
     * Metodo que devuelve un objeto Cart de un optional, sino lanza error 404 a traves de la clase GlobalExceptionHandler
     * @param optCart
     * @param id
     * @return Cart
     */
    public static Cart getCartFromOptionalOrThrow(Optional<Cart> optCart){
        return optCart.orElseThrow(() -> new EntityNotFoundException("El usuario no tiene asignado ningun carrito."));
    }

}
