package com.dani.spring.ecommerce_backend_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.entities.reviews.Review;


public interface ReviewRepository extends JpaRepository<Review, Long>{

    List<Review> findByProductOrderByPurchasedDesc(Product product);

}
