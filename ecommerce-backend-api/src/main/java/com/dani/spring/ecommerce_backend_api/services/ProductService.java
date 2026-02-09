package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;

import com.dani.spring.ecommerce_backend_api.entities.Product;

public interface ProductService {

    List<Product> findAll();

    Product save(Product product);

}
