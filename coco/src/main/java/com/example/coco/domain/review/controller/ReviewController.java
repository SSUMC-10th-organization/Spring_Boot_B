package com.example.coco.domain.review.controller;

import com.example.coco.base.ApiResponse;
import com.example.coco.domain.review.dto.ReviewRequestDTO;
import com.example.coco.domain.review.dto.ReviewResponseDTO;
import com.example.coco.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.RequestParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Review API", description = "리뷰 관련 API")
@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 작성", description = "가게에 리뷰를 등록합니다.")
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<String> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewRequestDTO.CreateReviewDTO request) {

        String result = reviewService.createReview(storeId, 1L, request);
        return ApiResponse.onSuccess(result);
    }

    @Operation(summary = "내가 생성한 리뷰들 조회하기 (커서)", description = "커서 기반 스크롤 페이지네이션으로 리뷰 목록을 정렬별로 가져옵니다.")
    @GetMapping("/users/{memberId}/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewCursorPageDTO> getMyReviewsCursor(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(required = false) Float cursorRating,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "10") int size) {

        ReviewResponseDTO.ReviewCursorPageDTO result = reviewService.getMyReviewsCursor(memberId, cursorId, cursorRating, sortBy, size);
        return ApiResponse.onSuccess(result);
    }
}