package com.dani.spring.ecommerce_backend_api.dto.requests;

import com.dani.spring.ecommerce_backend_api.validations.IsRequired;
import com.dani.spring.ecommerce_backend_api.validations.NumberRange;
import com.dani.spring.ecommerce_backend_api.validations.StringSize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class AddReviewRequestDto {

    @Schema(description = "Puntuacion de la review", example = "4")
    @NotNull
    @NumberRange(min=1, max=5)
    private Integer rating;

    @Schema(description = "Titulo de la review", example = "Buen producto, recomendado")
    @IsRequired
    @StringSize(min=5, max=100)
    private String title;

    @Schema(description = "Descripcion de la review", example = "Recomiendo este producto porque me ha sido muy util")
    @IsRequired
    @StringSize(min=10, max=250)
    private String comment;

    public AddReviewRequestDto(){
    }

    public AddReviewRequestDto(Integer rating, String title, String comment) {
        this.rating = rating;
        this.title = title;
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    

}
