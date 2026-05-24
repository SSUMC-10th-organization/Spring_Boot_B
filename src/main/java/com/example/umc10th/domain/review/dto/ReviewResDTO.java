package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReviewResponse(
            Long reviewId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record ReviewSummary(
            Long reviewId,
            String userName,
            Float rate,
            String content,
            List<String> images,
            LocalDateTime createdAt
    ) {
    }


    @Builder
    public record GetReviewResponse(
            List<ReviewSummary> reviews,
            Long nextCursor,
            Boolean hasNext
    ) {
    }
}