package com.example.umc10th.domain.store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    @Schema(description = "리뷰 작성 요청 DTO")
    public static class ReviewDto {
        @NotNull(message = "리뷰를 작성하는 회원의 ID는 필수입니다.")
        @Schema(description = "리뷰를 작성하는 회원의 ID", example = "1")
        private Long memberId;
        
        @NotNull(message = "별점은 필수입니다.")
        @Min(value = 0, message = "별점은 0 이상이어야 합니다.")
        @Max(value = 5, message = "별점은 5 이하이어야 합니다.")
        @Schema(description = "가게 별점", example = "4.5")
        private Float rating;
        
        @NotBlank(message = "리뷰 내용은 필수입니다.")
        @Schema(description = "리뷰 내용", example = "음식이 너무 맛있어요!")
        private String content;
    }
}
