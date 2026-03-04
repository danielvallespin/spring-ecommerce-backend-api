package com.dani.spring.ecommerce_backend_api.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.dto.requests.AddReviewRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.ReviewResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.entities.reviews.Review;
import com.dani.spring.ecommerce_backend_api.services.ProductService;
import com.dani.spring.ecommerce_backend_api.services.ReviewService;
import com.dani.spring.ecommerce_backend_api.utils.ProductUtility;
import com.dani.spring.ecommerce_backend_api.utils.ReviewUtility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "6. Reseñas", description = "API para gestión de reseñas")
@RestController
@RequestMapping("/review")
public class ReviewController {

    /*TODO
        OBTENER TODAS LAS RESEÑAS DE 1 PRODUCTO ORDENADAS POR USUARIOS QUE LO HAN COMPRADO (SIN JWT)
        CREAR UNA RESEÑA DE UN PRODUCTO (VALIDAR COMPRA PARA CAMPO PURCHASED Y VALIDAR EXISTENCIA POR USUARIO)
        MODIFICAR UNA RESEÑA (VALIDAR COMPRA PARA CAMPO PURCHASED Y VALIDAR EXISTENCIA POR USUARIO)
        ELIMINAR UNA RESEÑA (VALIDAR EXISTENCIA POR USUARIO)
    */

    @Autowired
    ReviewService service;

    @Autowired
    ProductService productService;


    //GET_ALL
    @Operation(summary = "Obtener todos las reseñas de un productos (SIN JWT)")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Reseñas obtenidas correctamente",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ReviewResponseDto.class)))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado ningún producto con id: id", content = @Content)
    })
    @GetMapping("/{productId}")
    public ResponseEntity<List<ReviewResponseDto>> getProductReviews(@PathVariable Long productId){
        //Obtenemos el product (sino existe devuelve un 404)
        Product product = productService.getProductById(productId);

        //Obtenemos la lista de reviews
        List<Review> reviews = service.getAllByProduct(product);

        return ResponseEntity.ok(ReviewUtility.getReviewResponseList(reviews));
    }

    //CREATE
    @Operation(summary = "Crear una nueva review")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Reseñas creada correctamente",
            content = @Content(schema = @Schema(implementation = ReviewResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado ningún producto con id: id", content = @Content)
    })
    @PostMapping("/{productId}")
    public ResponseEntity<ReviewResponseDto> createReview(@Valid @RequestBody AddReviewRequestDto request, @PathVariable Long productId, Principal principal){
        //Obtenemos el product (sino existe devuelve un 404)
        Product product = productService.getProductById(productId);

        return null;
    }
    

}
