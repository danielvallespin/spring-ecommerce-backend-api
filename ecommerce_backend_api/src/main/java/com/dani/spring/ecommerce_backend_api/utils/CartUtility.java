package com.dani.spring.ecommerce_backend_api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dani.spring.ecommerce_backend_api.dto.responses.CartItemResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.CartResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.ProductCartOrWishResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.cart.Cart;
import com.dani.spring.ecommerce_backend_api.entities.cart.CartItem;
import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.exceptions.InsufficientStockException;

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
            response.add(new CartItemResponseDto(getProductCartOrWishResponse(item.getProduct()), item.getQuantity()));
        }

        return response;
    }

    /**
     * Metodo para obtener objeto dto respuesta de producto del carrito o wishlist
     *
     * @param product
     * @return ProductCartOrWishResponseDto
     */
    public static ProductCartOrWishResponseDto getProductCartOrWishResponse(Product product) {
        return new ProductCartOrWishResponseDto(
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


    /**
     * Respuesta predefinida para cuando un producto no ha sido encontrado
     * @return
     */
    public static ResponseEntity<Map<String, String>> getProductNotFoundMessage(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "El producto indicado no existe"));
    }


    /**
     * Metodo de validacion para no superar el stock disponible (si no se supera devuelve un 409)
     * @param stockRequest
     * @param stockProduct
     */
    public static void validateAvailableStock(Integer stockRequest, Integer stockProduct){
        if(stockRequest > stockProduct){
            throw new InsufficientStockException("Stock insuficiente", stockProduct);
        }
    }

}
