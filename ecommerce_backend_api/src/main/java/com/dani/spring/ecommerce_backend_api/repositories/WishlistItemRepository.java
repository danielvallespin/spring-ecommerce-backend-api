package com.dani.spring.ecommerce_backend_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dani.spring.ecommerce_backend_api.entities.wishlist.WishlistItem;
import com.dani.spring.ecommerce_backend_api.entities.wishlist.WishlistItemId;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, WishlistItemId> {

    void deleteByIdWishlistIdAndIdProductId(Long wishlistId, Long productId);

}
