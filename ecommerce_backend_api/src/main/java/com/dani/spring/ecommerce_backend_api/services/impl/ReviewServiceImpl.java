package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.entities.reviews.Review;
import com.dani.spring.ecommerce_backend_api.repositories.ProductRepository;
import com.dani.spring.ecommerce_backend_api.repositories.ReviewRepository;
import com.dani.spring.ecommerce_backend_api.services.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepository repository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Review> getAllByProduct(Product product) {
        return repository.findByProductOrderByPurchasedDesc(product);
    }



}
