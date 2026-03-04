package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;

import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.entities.reviews.Review;

public interface ReviewService {

    List<Review> getAllByProduct(Product product);


}
