package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dani.spring.ecommerce_backend_api.entities.Product;
import com.dani.spring.ecommerce_backend_api.repositories.ProductRepository;
import com.dani.spring.ecommerce_backend_api.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository repository;

    @Override
    @Transactional(readOnly=true)
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }
    
    @Override
    @Transactional(readOnly=true)
    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

}
