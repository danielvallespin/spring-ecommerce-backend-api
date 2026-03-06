package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.entities.user.User;
import com.dani.spring.ecommerce_backend_api.entities.wishlist.Wishlist;
import com.dani.spring.ecommerce_backend_api.entities.wishlist.WishlistItem;
import com.dani.spring.ecommerce_backend_api.repositories.UserRepository;
import com.dani.spring.ecommerce_backend_api.repositories.WishlistItemRepository;
import com.dani.spring.ecommerce_backend_api.repositories.WishlistRepository;
import com.dani.spring.ecommerce_backend_api.services.WishlistService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    WishlistRepository repository;

    @Autowired
    WishlistItemRepository wishlistItemRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly=true)
    @Override
    public Wishlist getWishlistByUserAndId(String username, Long wishlistId) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Optional<Wishlist> optWishlist = repository.findByUserIdAndId(user.getId(), wishlistId);
        if (!optWishlist.isPresent()){
            throw  new EntityNotFoundException("La lista indicada no existe");
        }
        return optWishlist.orElseThrow();
    }

    @Transactional(readOnly=true)
    @Override
    public List<Optional<Wishlist>> getUserWishlists(Long userId) {
        return repository.findByUserId(userId);
    }

    @Transactional(readOnly=true)
    @Override
    public List<Optional<Wishlist>> getUserWishlists(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return repository.findByUserId(user.getId());
    }

    @Transactional
    @Override
    public Wishlist createWishlist(User user, String name) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setName(name);
        return repository.save(wishlist);
    }

    @Transactional
    @Override
    public Wishlist createWishlist(String username, String name) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return createWishlist(user, name);
    }

    @Transactional
    @Override
    public WishlistItem addItem(Wishlist wishlist, Product product) {
        WishlistItem wishlistItem = new WishlistItem(wishlist, product);
        return wishlistItemRepository.save(wishlistItem);
    }

    @Transactional(readOnly=true)
    @Override
    public boolean alreadyExistsListName(String username, String wishlistName) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return repository.existsByUserIdAndName(user.getId(), wishlistName);
    }

    @Transactional
    @Override
    public void deleteWishlist(Long wishlistId) {
        repository.deleteById(wishlistId);
    }

    @Transactional
    @Override
    public void deleteWishlistItem(Long wishlistId, Long productId) {
        wishlistItemRepository.deleteByIdWishlistIdAndIdProductId(wishlistId, productId);
    }

    @Transactional(readOnly=true)
    @Override
    public boolean existsByWishlistAndProduct(Wishlist wishlist, Product product) {
        return wishlistItemRepository.existsByWishlistAndProduct(wishlist, product);
    }


}
  