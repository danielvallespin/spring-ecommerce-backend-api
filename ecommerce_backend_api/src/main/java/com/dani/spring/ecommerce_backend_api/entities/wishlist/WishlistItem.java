package com.dani.spring.ecommerce_backend_api.entities.wishlist;
 
import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Table(name="wishlist_items")
@Entity
public class WishlistItem {

    @EmbeddedId
    WishlistItemId id;

    //FK wishlist id
    @JsonIgnore
    @ManyToOne
    @MapsId("wishlistId")
    @JoinColumn(name = "wishlist_id", nullable = false)
    private Wishlist wishlist;

    //FK product id
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public WishlistItem(){}

    public WishlistItem(Wishlist wishlist, Product product) {
        this.id = new WishlistItemId(wishlist.getId(), product.getId());
        this.wishlist = wishlist;
        this.product = product;
    }

    public WishlistItemId getId() {
        return id;
    }

    public void setId(WishlistItemId id) {
        this.id = id;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    

}
