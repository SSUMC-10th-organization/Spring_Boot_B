package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import jakarta.validation.constraints.*;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ReviewReqDTO {

    @Getter
    public static class Create {

        @NotNull(message = "식당 ID는 필수입니다.")
        @Positive(message = "식당 ID는 양수여야 합니다.")
        private Long restaurantId;

        @NotNull(message = "별점은 필수입니다.")
        @Min(value = 1, message = "별점은 최소 1점입니다.")
        @Max(value = 5, message = "별점은 최대 5점입니다.")
        private Float rate;

        @Size(max = 500, message = "리뷰 내용은 최대 500자입니다.")
        private String content;

        private List<MultipartFile> images;
    }

    @Getter
    public static class MyReviewRequest {

        @NotNull(message = "사용자 ID는 필수입니다.")
        @Positive(message = "사용자 ID는 양수여야 합니다.")
        private Long userId;

        // 정렬 기준: ID_DESC(기본), RATE_DESC, RATE_ASC
        private String sortBy = "ID_DESC";
    }
}