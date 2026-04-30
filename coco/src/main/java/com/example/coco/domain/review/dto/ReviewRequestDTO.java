package com.example.coco.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class ReviewRequestDTO {
    public record CreateReviewDTO(
            @Schema(description = "평점", example = "4") Integer rating,
            @Schema(description = "내용", example = "맛있어요") String content,
            @Schema(description = "이미지 리스트") List<String> images
    ) {}
}