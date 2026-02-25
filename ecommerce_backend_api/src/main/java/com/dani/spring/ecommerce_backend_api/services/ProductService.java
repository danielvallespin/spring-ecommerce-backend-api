package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;
import java.util.Optional;

import com.dani.spring.ecommerce_backend_api.dto.requests.FullProductRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.ProductUpdateDto;
import com.dani.spring.ecommerce_backend_api.entities.Product;

public interface ProductService {
    
    List<Product> findAllProducts();
    
    Optional<Product> getProductById(Long id);

    Product createFullProduct(FullProductRequestDto productRequest);

    Optional<Product> modifyFullProduct(ProductUpdateDto productRequest, Long id);

    void deleteProductById(Long id);

}
