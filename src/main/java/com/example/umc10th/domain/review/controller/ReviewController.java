package com.example.umc10th.domain.review.controller;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 리뷰 작성
     * POST /api/v1/restaurants/{restaurantId}/reviews
     * Header: Authorization: Bearer {token}, Content-Type: multipart/form-data
     */
    @PostMapping("/restaurants/{restaurantId}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ReviewResDTO.CreateReviewResponse> createReview(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long restaurantId,
            @ModelAttribute @Valid ReviewReqDTO.Create request
    ) {
        Long userId = extractUserId(authorization);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, reviewService.createReview(userId, restaurantId, request));
    }

    /**
     * 가게 리뷰 목록 조회
     * GET /api/v1/restaurants/{restaurantId}/reviews?cursor=10&size=10
     */
    @GetMapping("/restaurants/{restaurantId}/reviews")
    public ApiResponse<ReviewResDTO.GetReviewResponse> getRestaurantReviews(
            @PathVariable Long restaurantId,
            @RequestParam(value = "cursor", required = false) Long cursor,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewService.getRestaurantReviews(restaurantId, cursor, size));
    }

    private Long extractUserId(String authorization) {
        // TODO: JWT 파싱 로직
        return 1L;
    }
}