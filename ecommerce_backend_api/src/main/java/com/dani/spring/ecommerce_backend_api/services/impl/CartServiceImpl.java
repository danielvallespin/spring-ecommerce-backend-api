package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dani.spring.ecommerce_backend_api.dto.responses.CartItemResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.CartResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.ProductCartResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.Cart;
import com.dani.spring.ecommerce_backend_api.entities.CartItem;
import com.dani.spring.ecommerce_backend_api.entities.Product;
import com.dani.spring.ecommerce_backend_api.entities.User;
import com.dani.spring.ecommerce_backend_api.repositories.CartRepository;
import com.dani.spring.ecommerce_backend_api.repositories.UserRepository;
import com.dani.spring.ecommerce_backend_api.services.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository repository;

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly=true)
    @Override
    public CartResponseDto getUserCart(String username){
        User user = userRepository.getByUsername(username).orElseThrow();
        Cart userCart = repository.findByUserId(user.getId()).orElseThrow();
        
        return getCartResponse(userCart);
    }


    /**
     * Metodo para obtener el objeto dto respuesta del carrito
     * @param cart
     * @return
     */
    private CartResponseDto getCartResponse(Cart cart){
        List<CartItemResponseDto> items = getCartItemsListResponse(cart.getItems());
        return new CartResponseDto(cart.getId(), cart.getUser().getId(), items);
    }


    /**
     * Metodo para obtener el objeto dto respuesta de los items del carrito
     * @param items
     * @return
     */
    private List<CartItemResponseDto> getCartItemsListResponse(List<CartItem> items){
        List<CartItemResponseDto> response = new ArrayList<>();
        for (CartItem item : items){
            response.add(new CartItemResponseDto(getProductCartResponse(item.getProduct()), item.getQuantity()));
        }

        return response;
    }

    /**
     * Metodo para obtener objeto dto respuesta de producto del carrito
     * @param product
     * @return
     */
    private ProductCartResponseDto getProductCartResponse(Product product){
        return new ProductCartResponseDto(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getImageUrl(),
            product.getDetail().getBrand()
        );
    }

}
