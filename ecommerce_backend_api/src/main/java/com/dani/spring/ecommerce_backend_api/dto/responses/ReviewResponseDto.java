package com.dani.spring.ecommerce_backend_api.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReviewResponseDto {

    @Schema(description = "Id del producto", example = "5")
    private final Long productId;

    @Schema(description = "Id del usuario", example = "3")
    private final Long userId;

    @Schema(description = "Username del usuario", example = "dani")
    private final String reviewerUsername;

    @Schema(description = "Puntuacion de la reseña", example = "4")
    private final Integer rating;

    @Schema(description = "Titulo de la reseña", example = "Buen producto")
    private final String title;

    @Schema(description = "Comentario de la reseña", example = "Relacion calidad precio correcta")
    private final String comment;

    @Schema(description = "Indicado de si el producto ha sido comprado", example = "true")
    private final boolean purchased;
    
    public ReviewResponseDto(Long productId, Long userId, String reviewerUsername, Integer rating, String title, String comment, boolean purchased) {
        this.productId = productId;
        this.userId = userId;
        this.reviewerUsername = reviewerUsername;
        this.rating = rating;
        this.title = title;
        this.comment = comment;
        this.purchased = purchased;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getReviewerUsername() {
        return reviewerUsername;
    }

    public Integer getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }

    public boolean isPurchased() {
        return purchased;
    }

    

}
