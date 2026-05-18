package com.example.umc10th.domain.store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "리뷰 정보 DTO")
    public static class ReviewDto {
        @Schema(description = "리뷰 ID")
        private Long reviewId;
        @Schema(description = "가게 이름")
        private String storeName;
        @Schema(description = "리뷰 작성자 ID")
        private Long memberId;
        @Schema(description = "별점")
        private Float star;
        @Schema(description = "리뷰 내용")
        private String content;
        @Schema(description = "작성 일시")
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "리뷰 목록 응답 DTO")
    public static class ReviewListDto {
        @Schema(description = "리뷰 목록")
        private List<ReviewDto> reviewList;
        @Schema(description = "현재 커서 (마지막 리뷰 ID 또는 별점)")
        private String nextCursor;
        @Schema(description = "다음 페이지 존재 여부")
        private Boolean hasNext;
    }
}
