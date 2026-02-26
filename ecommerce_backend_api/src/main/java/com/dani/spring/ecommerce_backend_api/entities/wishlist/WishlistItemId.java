package com.dani.spring.ecommerce_backend_api.entities.wishlist;

import jakarta.persistence.Embeddable;

@Embeddable
public class WishlistItemId {

    private Long wishlistId;

    private Long productId;

    public WishlistItemId() {}

    public WishlistItemId(Long wishlistId, Long productId) {
        this.wishlistId = wishlistId;
        this.productId = productId;
    }

    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((wishlistId == null) ? 0 : wishlistId.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WishlistItemId other = (WishlistItemId) obj;
        if (wishlistId == null) {
            if (other.wishlistId != null)
                return false;
        } else if (!wishlistId.equals(other.wishlistId))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        return true;
    }

    

}
