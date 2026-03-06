package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;
import java.util.Optional;

import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.entities.user.User;
import com.dani.spring.ecommerce_backend_api.entities.wishlist.Wishlist;
import com.dani.spring.ecommerce_backend_api.entities.wishlist.WishlistItem;

public interface WishlistService {

    List<Optional<Wishlist>> getUserWishlists(Long userId);

    List<Optional<Wishlist>> getUserWishlists(String username);

    Wishlist createWishlist(User user, String name);

    Wishlist createWishlist(String username, String name);

    WishlistItem addItem(Wishlist wishlist, Product product);

    Wishlist getWishlistByUserAndId(String username, Long wishlistId);

    boolean alreadyExistsListName(String username, String wishlistName);

    void deleteWishlist(Long wishlistId);

    void deleteWishlistItem(Long wishlistId, Long productId);

    boolean existsByWishlistAndProduct(Wishlist wishlist, Product product);

}
