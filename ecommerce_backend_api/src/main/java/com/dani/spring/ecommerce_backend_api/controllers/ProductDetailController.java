package com.dani.spring.ecommerce_backend_api.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.dto.FullProductDto;
import com.dani.spring.ecommerce_backend_api.entities.Product;
import com.dani.spring.ecommerce_backend_api.entities.ProductDetail;
import com.dani.spring.ecommerce_backend_api.services.ProductDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("product/detail")
@RestController
public class ProductDetailController {

    @Autowired
    private ProductDetailService service;

    //GET FULL PRODUCT DTO
    @Operation(summary = "Obtener todos los datos de un producto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductDetail(@PathVariable Long id){
        Optional<ProductDetail> optProductDetail = service.getDetailByID(id);
        if (optProductDetail.isPresent()){
            ProductDetail detail = optProductDetail.get();
            Product product = detail.getProduct();

            // Crear DTO completo
            FullProductDto dto = new FullProductDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setPrice(product.getPrice());
            dto.setStock(product.getStock());
            dto.setImageUrl(product.getImageUrl());

            dto.setLongDescription(detail.getLongDescription());
            dto.setBrand(detail.getBrand());
            dto.setCategories(detail.getCategories());

            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

}