package com.example.umc10th.domain.store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class StoreResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "리뷰 작성 응답 DTO")
    public static class ReviewResultDto {
        @Schema(description = "생성된 리뷰 ID", example = "1")
        private Long reviewId;
        @Schema(description = "생성 일시")
        private LocalDateTime createdAt;
    }
}
