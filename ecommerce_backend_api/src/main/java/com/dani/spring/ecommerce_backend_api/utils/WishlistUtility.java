package com.dani.spring.ecommerce_backend_api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dani.spring.ecommerce_backend_api.dto.responses.ProductCartOrWishResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.WishlistResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.wishlist.Wishlist;
import com.dani.spring.ecommerce_backend_api.entities.wishlist.WishlistItem;

public class WishlistUtility {

    
    /**
     * Metodo que devuelve una lista de objetos wishlist response dto
     *
     * @param optWishlists
     * @return
     */
    public static List<WishlistResponseDto> getListOfWishlistResponse(List<Optional<Wishlist>> optWishlists) {
        List<WishlistResponseDto> responseList = new ArrayList<>();
        if (optWishlists != null && !optWishlists.isEmpty()){
            for (Optional<Wishlist> opt : optWishlists) {
                Wishlist wishlist = opt.orElseThrow();
                responseList.add(getWishlistResponse(wishlist));
            }
        }

        return responseList;
    }

    /**
     * Metodo que devuelve un objeto wishlist response dto
     *
     * @param wishlist
     * @return WishlistResponseDto
     */
    public static WishlistResponseDto getWishlistResponse(Wishlist wishlist) {
        List<ProductCartOrWishResponseDto> items = getListItemsResponse(wishlist.getItems());
        return new WishlistResponseDto(wishlist.getId(), wishlist.getUser().getId(), wishlist.getName(), items);
    }

    /**
     * Metodo que convierte una lista de items de wishlist en objeto producto
     * wishlist response dto
     *
     * @param items
     * @return
     */
    private static List<ProductCartOrWishResponseDto> getListItemsResponse(List<WishlistItem> items) {
        List<ProductCartOrWishResponseDto> listProductsResponse = new ArrayList<>();
        for (WishlistItem item : items) {
            listProductsResponse.add(CartUtility.getProductCartOrWishResponse(item.getProduct()));
        }

        return listProductsResponse;
    }


}
