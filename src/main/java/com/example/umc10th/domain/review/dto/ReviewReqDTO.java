package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {

    public record CreateReviewRequest(
            Long userId,
            Long restaurantId,
            String content,
            Double score
    ) {
    }

    public record GetReviewRequest(
            Long reviewId
    ) {
    }
}