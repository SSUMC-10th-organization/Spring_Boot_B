package com.example.coco.domain.review.controller;

import com.example.coco.base.ApiResponse;
import com.example.coco.domain.review.dto.ReviewRequestDTO;
import com.example.coco.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Review API", description = "리뷰 관련 API")
@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService; // 분리된 리뷰 서비스 주입

    @Operation(summary = "리뷰 작성", description = "가게에 리뷰를 등록합니다.")
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<String> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewRequestDTO.CreateReviewDTO request) {

        String result = reviewService.createReview(storeId, 1L, request); // 임시 사용자 ID 1L
        return ApiResponse.onSuccess(result);
    }
}