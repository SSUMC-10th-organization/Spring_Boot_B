package com.example.umc10th.domain.store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    @Schema(description = "리뷰 작성 요청 DTO")
    public static class ReviewDto {
        @Schema(description = "리뷰를 작성하는 회원의 ID", example = "1")
        private Long memberId;
        @Schema(description = "가게 별점", example = "4.5")
        private Float rating;
        @Schema(description = "리뷰 내용", example = "음식이 너무 맛있어요!")
        private String content;
    }
}
