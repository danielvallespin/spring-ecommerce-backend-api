package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;
import java.util.Optional;

import com.dani.spring.ecommerce_backend_api.dto.FullProductRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.SimpleProductDto;
import com.dani.spring.ecommerce_backend_api.entities.Product;

public interface ProductService {
    
    List<SimpleProductDto> findAll();
    
    Optional<Product> getProductById(Long id);

    Product createFullProduct(FullProductRequestDto productRequest);

    Optional<Product> modifyFullProduct(FullProductRequestDto productRequest, Long id);

}
