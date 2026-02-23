package com.dani.spring.ecommerce_backend_api.services;

import com.dani.spring.ecommerce_backend_api.dto.responses.CartResponseDto;

public interface CartService {

    CartResponseDto getUserCart(String username);

}
