package com.dani.spring.ecommerce_backend_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.entities.reviews.Review;
import com.dani.spring.ecommerce_backend_api.entities.user.User;


public interface ReviewRepository extends JpaRepository<Review, Long>{

    List<Review> findByProductOrderByPurchasedDesc(Product product);

    List<Review> findByUser(User user);

    Optional<Review> findByProductAndUser(Product product, User user);

}
