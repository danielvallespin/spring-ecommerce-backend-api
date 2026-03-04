package com.dani.spring.ecommerce_backend_api.dto.responses;

public class ReviewResponseDto {

    Long productId;

    Long userId;

    String reviewerUsername;

    Integer rating;

    String title;

    String comment;

    boolean purchased;

    public ReviewResponseDto(){
    }
    
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
