package com.example.coco.domain.review.controller;

import com.example.coco.base.ApiResponse;
import com.example.coco.domain.review.dto.ReviewRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Review API", description = "리뷰 관련 API")
@RestController
@RequestMapping("/api/stores")
public class ReviewController {

    @Operation(summary = "리뷰 작성", description = "가게에 리뷰를 등록합니다.")
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<String> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewRequestDTO.CreateReviewDTO request) {
        return ApiResponse.onSuccess("리뷰 등록 성공");
    }
}