package com.dani.spring.ecommerce_backend_api.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.dto.requests.ReviewRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.ReviewUpdateRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.ReviewResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.entities.reviews.Review;
import com.dani.spring.ecommerce_backend_api.exceptions.DataAlreadyExistsException;
import com.dani.spring.ecommerce_backend_api.services.ProductService;
import com.dani.spring.ecommerce_backend_api.services.ReviewService;
import com.dani.spring.ecommerce_backend_api.services.UserService;
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

@Tag(name = "6. Reviews", description = "API para gestión de reviews")
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

    @Autowired
    UserService userService;


    //GET_ALL
    @Operation(summary = "Obtener todos las reviews de un productos (SIN JWT)")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Reviews obtenidas correctamente",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ReviewResponseDto.class)))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado ningún producto con id: id", content = @Content)
    })
    @GetMapping("/{productId}")
    public ResponseEntity<List<ReviewResponseDto>> getProductReviews(@PathVariable Long productId, Principal principal){
        //Obtenemos el product (sino existe o no tienes acceso a el devuelve un 404)
        Product product = getProductWithVisibilityValidation(productId, principal);

        //Obtenemos la lista de reviews
        List<Review> reviews = service.getAllByProduct(product);

        return ResponseEntity.ok(ReviewUtility.getReviewResponseList(reviews));
    }

    //GET_ALL_MY_REVIEWS
    @Operation(summary = "Obtener todas mis reviews")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Reviews obtenidas correctamente",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ReviewResponseDto.class))))
    })
    @GetMapping("/my/all")
    public ResponseEntity<List<ReviewResponseDto>> getAllMyReviews(Principal principal){
        //Obtenemos la lista de reviews
        List<Review> reviews = service.getAllMyReviews(principal.getName());

        return ResponseEntity.ok(ReviewUtility.getReviewResponseList(reviews));
    }

    //GET_MY_REVIEW
    @Operation(summary = "Obtener mi review por producto")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Reviews obtenidas correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado el producto o la review", content = @Content)
    })
    @GetMapping("/my/{productId}")
    public ResponseEntity<ReviewResponseDto> getMyProductReviews(@PathVariable Long productId, Principal principal){
        //Obtenemos el product (sino existe o no tienes acceso a el devuelve un 404)
        Product product = getProductWithVisibilityValidation(productId, principal);

        //Obtenemos la review (si no existe devuelve 404)
        Review review = service.getReview(product, principal.getName());

        return ResponseEntity.ok(ReviewUtility.getReviewResponse(review));
    }

    //CREATE
    @Operation(summary = "Crear una nueva review")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Reviews creada correctamente",
            content = @Content(schema = @Schema(implementation = ReviewResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado ningún producto con id: id", content = @Content)
    })
    @PostMapping("/{productId}")
    public ResponseEntity<ReviewResponseDto> createReview(@Valid @RequestBody ReviewRequestDto request, @PathVariable Long productId, Principal principal){
        //Obtenemos el product (sino existe o no tienes acceso a el devuelve un 404)
        Product product = getProductWithVisibilityValidation(productId, principal);

        //Validamos si ya habia una review
        Optional<Review> optReview = service.getOptionalReview(product, principal.getName());
        if (optReview.isPresent()){
            throw new DataAlreadyExistsException(String.format("Ya has publicado una reseña para %s", product.getName()));
        }

        //Creamos la review
        Review newReview = service.createReview(product, principal.getName(), request);

        return ResponseEntity.ok(ReviewUtility.getReviewResponse(newReview));
    }

    //MODIFY
    @Operation(summary = "Modificar una review")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Review modificada correctamente",
            content = @Content(schema = @Schema(implementation = ReviewResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado el producto o la review", content = @Content)
    })
    @PatchMapping("/{productId}")
    public ResponseEntity<ReviewResponseDto> updateReview(@Valid @RequestBody ReviewUpdateRequestDto request, @PathVariable Long productId, Principal principal){
        //Obtenemos el product (sino existe o no tienes acceso a el devuelve un 404)
        Product product = getProductWithVisibilityValidation(productId, principal);

        //Guardamos los cambios
        Review reviewMod = service.updateReview(product, principal.getName(), request);
        
        return ResponseEntity.ok(ReviewUtility.getReviewResponse(reviewMod));
    }

    //DELETE
    @Operation(summary = "Modificar una review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Review eliminada correctamente", content = @Content),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado el producto o la review", content = @Content)
    })
    @DeleteMapping("/{productId}")
    public ResponseEntity<Map<String, String>> deleteReview(@PathVariable Long productId, Principal principal){
        //Obtenemos el product (sino existe o no tienes acceso a el devuelve un 404)
        Product product = getProductWithVisibilityValidation(productId, principal);

        //Eliminamos y montamos la respuesta
        service.deleteReview(product, principal.getName());
        Map<String, String> response = Map.of("message", "Se ha eliminado correctamente la review de " + product.getName());

        return ResponseEntity.ok(response);
    }


    /**
     * Metodo que devuelve si el usuario es admin
     * @param principal
     * @return
     */
    private boolean getIsAdmin(Principal principal){
        boolean isAdmin = false;
        if (principal != null){
            isAdmin = userService.isAdmin(principal.getName());
        }

        return isAdmin;
    }

    /**
     * Metodo que devuele un Product o un 404 si (no es visible y no eres admin) o el producto no existe
     * @param productId
     * @param principal
     * @return
     */
    private Product getProductWithVisibilityValidation(Long productId, Principal principal){
        //Obtenemos el product (sino existe devuelve un 404)
        Product product = productService.getProductById(productId);
        //Si el producto no es visible y el usuario no es admin devolvemos un 404
        if (!product.isVisible()){
            ProductUtility.visibilityValidation(getIsAdmin(principal), productId);
        }

        return product;
    }


}
