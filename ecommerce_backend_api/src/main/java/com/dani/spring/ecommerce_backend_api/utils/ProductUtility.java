package com.dani.spring.ecommerce_backend_api.utils;

import java.util.ArrayList;
import java.util.List;

import com.dani.spring.ecommerce_backend_api.dto.responses.FullProductResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.SimpleProductDto;
import com.dani.spring.ecommerce_backend_api.entities.product.Product;

import jakarta.persistence.EntityNotFoundException;

public class ProductUtility {

    /**
     * Metodo de convierte un Product en SimpleProductDto
     * @param product
     * @return
     */
    public static SimpleProductDto getSimpleProduct(Product product){
        return new SimpleProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImageUrl(),
                product.isVisible()
            );
    }

    /**
     * Metodo que convierte una lista de Product en una lista de SimpleProductDto
     * @param products
     * @return List<SimpleProductDto>
     */
    public static List<SimpleProductDto> getSimpleProductList(List<Product> products){
        List<SimpleProductDto> response = new ArrayList<>();
        for (Product product : products){
            response.add(getSimpleProduct(product));
        }

        return response;
    }

    /**
     * Metodo convierte un Product en un FullProductResponseDto
     * @param product
     * @return FullProductResponseDto
     */
    public static FullProductResponseDto getFullProductResponseDto(Product product){
        return new FullProductResponseDto(
            getSimpleProduct(product),
            product.getDetail().getLongDescription(),
            product.getDetail().getBrand(),
            product.getDetail().getCategories()
        );
    }

    /**
     * Metodo que devuelve 404 si el usuario no es admin
     * @param isAdmin
     * @param productId
     */
    public static void visibilityValidation(boolean isAdmin, Long productId) {
        if (!isAdmin){
            throw new EntityNotFoundException("No se ha encontrado ningún producto con id: " + productId);
        }
    }

}
