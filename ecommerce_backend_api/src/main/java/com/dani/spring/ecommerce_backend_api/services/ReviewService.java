package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;

import com.dani.spring.ecommerce_backend_api.dto.requests.ReviewRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.ReviewUpdateRequestDto;
import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.entities.reviews.Review;

public interface ReviewService {

    List<Review> getAllByProduct(Product product);

    List<Review> getAllMyReviews(String username);

    Review getReview(Product product,String username);

    Review createReview(Product product, String username, ReviewRequestDto request);

    Review updateReview(Product product, String username, ReviewUpdateRequestDto request);

    void deleteReview(Product product, String username);

}
