package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;
import java.util.Optional;

import com.dani.spring.ecommerce_backend_api.entities.user.User;
import com.dani.spring.ecommerce_backend_api.entities.wishlist.Wishlist;

public interface WishlistService {

    List<Optional<Wishlist>> getUserWishlists(Long userId);

    Wishlist createWishlist(User user, String name);

}
