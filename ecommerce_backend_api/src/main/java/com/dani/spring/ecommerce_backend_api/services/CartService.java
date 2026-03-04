package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;
import java.util.Optional;

import com.dani.spring.ecommerce_backend_api.entities.cart.Cart;
import com.dani.spring.ecommerce_backend_api.entities.cart.CartItem;
import com.dani.spring.ecommerce_backend_api.entities.cart.CartItemId;
import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.entities.user.User;

public interface CartService {

    Cart getUserCart(String username);

    Cart createCart(User user);

    void addProductToCart(Product product, Integer quantity, String username);

    void removeProductFromCart(Long productId, String username);

    boolean isProductInCart(Long productId, String username);

    void updateProductQuantity(Long productId, Integer quantity, String username);

    Optional<CartItem> getCartItemById(CartItemId cartItemId);

    Optional<CartItem> getCartItemById(Long productId, String username);

    List<CartItem> getAllItemsFromCart(String username);

    void emptyCart(String username);

}
