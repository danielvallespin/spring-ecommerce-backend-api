package com.dani.spring.ecommerce_backend_api.utils;

import java.util.List;
import java.util.Optional;

import com.dani.spring.ecommerce_backend_api.dto.responses.FullProductResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.SimpleProductDto;
import com.dani.spring.ecommerce_backend_api.entities.Product;

import jakarta.persistence.EntityNotFoundException;

public class ProductUtility {

    /**
     * Metodo que devuelve una lista de SimpleProductDto a partir de otra de Product
     * @param products
     * @return List<SimpleProductDto>
     */
    public static List<SimpleProductDto> getSimpleProductsList(List<Product> products){
        return products.stream()
            .map(product -> new SimpleProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImageUrl(),
                product.isVisible()
            ))
            .toList();
    }

    /**
     * Metodo que devuelve objeto dto FullProduct a partir de un Product
     * @param product
     * @return FullProductResponseDto
     */
    public static FullProductResponseDto getFullProductResponseDto(Product product){
        return new FullProductResponseDto(
            product.getId(), 
            product.getName(), 
            product.getDescription(), 
            product.getPrice(),
            product.getStock(),
            product.getImageUrl(),
            product.isVisible(),
            product.getDetail().getLongDescription(),
            product.getDetail().getBrand(),
            product.getDetail().getCategories()
        );
    }

    /**
     * Metodo que devuelve un objeto Product de un optional, sino lanza error 404 a traves de la clase GlobalExceptionHandler
     * @param optProduct
     * @param id
     * @return Product
     */
    public static Product getProductFromOptionalOrThrow(Optional<Product> optProduct, Long id){
        return optProduct.orElseThrow(() -> new EntityNotFoundException("No se ha encontrado ningún producto con id: " + id));
    }

}
