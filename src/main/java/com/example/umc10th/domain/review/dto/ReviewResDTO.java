package com.example.umc10th.domain.review.dto;

import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record CreateReviewResponse(
            Long reviewId,
            Long userId,
            Long restaurantId,
            String content,
            Double score
    ) {
    }

    @Builder
    public record GetReviewResponse(
            Long reviewId,
            String userName,
            String restaurantName,
            String content,
            Double score
    ) {
    }
}