package com.dani.spring.ecommerce_backend_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dani.spring.ecommerce_backend_api.entities.wishlist.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    List<Optional<Wishlist>> findByUserId(Long userId);

}
