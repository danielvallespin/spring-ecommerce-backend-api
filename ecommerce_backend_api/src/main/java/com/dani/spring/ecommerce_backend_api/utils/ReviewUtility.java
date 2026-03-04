package com.dani.spring.ecommerce_backend_api.utils;

import java.util.ArrayList;
import java.util.List;

import com.dani.spring.ecommerce_backend_api.dto.responses.ReviewResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.reviews.Review;

public class ReviewUtility {

    /**
     * Metodo que convierte una Review en ReviewResponseDto
     * @param review
     * @return
     */
    public static ReviewResponseDto getReviewResponse(Review review){
        return new ReviewResponseDto(
            review.getProduct().getId(),
            review.getUser().getId(),
            review.getUser().getUsername(),
            review.getRating(),
            review.getTitle(),
            review.getComment(),
            review.isPurchased()
        );
    }

    /**
     * Metodo que convierte una lista de Review en una lista de ReviewResponseDto
     * @param reviews
     * @return
     */
    public static List<ReviewResponseDto> getReviewResponseList(List<Review> reviews){
        List<ReviewResponseDto> response = new ArrayList<>();
        for (Review review : reviews){
            response.add(getReviewResponse(review));
        }

        return response;
    }

}
