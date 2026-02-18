package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dani.spring.ecommerce_backend_api.entities.ProductDetail;
import com.dani.spring.ecommerce_backend_api.repositories.ProductDetailRepository;
import com.dani.spring.ecommerce_backend_api.services.ProductDetailService;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductDetailRepository repository;

    @Override
    public Optional<ProductDetail> getDetailByID(Long id) {
        return repository.findById(id);
    }

}
