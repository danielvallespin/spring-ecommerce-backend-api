package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dani.spring.ecommerce_backend_api.entities.user.User;
import com.dani.spring.ecommerce_backend_api.entities.wishlist.Wishlist;
import com.dani.spring.ecommerce_backend_api.repositories.WishlistRepository;
import com.dani.spring.ecommerce_backend_api.services.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    WishlistRepository repository;

    @Transactional
    @Override
    public List<Optional<Wishlist>> getUserWishlists(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public Wishlist createWishlist(User user, String name) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setName(name);
        return repository.save(wishlist);
    }

}
  