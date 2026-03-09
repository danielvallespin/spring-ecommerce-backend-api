package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dani.spring.ecommerce_backend_api.entities.cart.Cart;
import com.dani.spring.ecommerce_backend_api.entities.cart.CartItem;
import com.dani.spring.ecommerce_backend_api.entities.cart.CartItemId;
import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.entities.user.User;
import com.dani.spring.ecommerce_backend_api.repositories.CartItemRepository;
import com.dani.spring.ecommerce_backend_api.repositories.CartRepository;
import com.dani.spring.ecommerce_backend_api.repositories.UserRepository;
import com.dani.spring.ecommerce_backend_api.services.CartService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository repository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly=true)
    @Override
    public Cart getUserCart(String username){
        User user = userRepository.findByUsername(username).orElseThrow();
        return getUserCart(user);
    }
    
    @Transactional(readOnly=true)
    @Override
    public Cart getUserCart(User user){
        Optional<Cart> optCart = repository.findByUserId(user.getId());
        if (!optCart.isPresent()){
            throw new EntityNotFoundException("El usuario no tiene asignado ningun carrito.");
        }
        Cart cart = optCart.orElseThrow();
        cart.calculateAmount();

        return cart;
    }

    @Transactional
    @Override
    public Cart createCart(User user) {
        Cart newCart = new Cart();
        newCart.setUser(user);
        return repository.save(newCart);
    }

    @Transactional
    @Override
    public void addProductToCart(Product product, Integer quantity, String username) {
        Cart cart = getUserCart(username);
        CartItem cartItem = new CartItem(cart, product, quantity);
        cartItemRepository.save(cartItem);
    }

    @Transactional
    @Override
    public void removeProductFromCart(Long productId, String username) {
        Cart cart = getUserCart(username);

        CartItemId cartItemId = new CartItemId(cart.getId(), productId);
        Optional<CartItem> optCartItem = getCartItemById(cartItemId);
        if (optCartItem.isPresent()){
            CartItem cartItem = optCartItem.orElseThrow();
            //Al eliminarlo de el objeto padre orphanRemoval lo elimina
            cart.getItems().remove(cartItem);
        }
    }

    @Transactional(readOnly=true)
    @Override
    public boolean isProductInCart(Long productId, String username) {
        Cart cart = getUserCart(username);
        CartItemId cartItemId = new CartItemId(cart.getId(), productId);
        return cartItemRepository.existsById(cartItemId);
    }

    @Transactional
    @Override
    public void updateProductQuantity(Long productId, Integer quantity, String username) {
        Cart cart = getUserCart(username);
        CartItemId cartItemId = new CartItemId(cart.getId(), productId);
        Optional<CartItem> optCartItem = getCartItemById(cartItemId);
        if (optCartItem.isPresent()){
            CartItem cartItem = optCartItem.orElseThrow();
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }
    }

    @Transactional(readOnly=true)
    @Override
    public Optional<CartItem> getCartItemById(CartItemId cartItemId) {
        return cartItemRepository.findById(cartItemId);
    }

    @Transactional(readOnly=true)
    @Override
    public Optional<CartItem> getCartItemById(Long productId, String username) {
        Cart cart = getUserCart(username);
        return getCartItemById(new CartItemId(cart.getId(), productId));
    }
    
    @Transactional
    @Override
    public void emptyCart(String username) {
        Cart cart = getUserCart(username);
        //Clear vacia el arraylist del padre y orphanRemoval lo elimina
        cart.getItems().clear();
    }


}
