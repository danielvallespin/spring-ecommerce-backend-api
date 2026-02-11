package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;
import java.util.Optional;

import com.dani.spring.ecommerce_backend_api.entities.Product;

public interface ProductService {
    
    List<Product> findAll();
    
    Optional<Product> getProductById(Long id);

    Product save(Product product);


}
