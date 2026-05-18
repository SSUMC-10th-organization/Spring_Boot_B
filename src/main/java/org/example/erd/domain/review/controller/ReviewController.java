package org.example.erd.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.erd.domain.review.dto.ReviewReqDTO;
import org.example.erd.domain.review.dto.ReviewResDTO;
import org.example.erd.domain.review.exception.code.ReviewSuccessCode;
import org.example.erd.domain.review.service.ReviewService;
import org.example.erd.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 작성", description = "가게의 별점과 리뷰 내용을 작성함")
    @PostMapping("/stores/{storeId}/reviews") //리뷰 작성
    public ApiResponse<ReviewResDTO.CreateReviewRes> createReview(
            @PathVariable Long storeId,
            @RequestHeader("Authorization") Long memberId,
            @RequestBody ReviewReqDTO.CreateReview dto
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATE_SUCCESS,
                reviewService.createReview(memberId,storeId,dto));
    }

    @Operation(summary = "내 리뷰 목록 조회", description = "ID순 또는 별점순으로 커서 기반 페이지네이션 조회")
    @GetMapping("/members/reviews")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.ReviewItem>> getMyReviews(
            @RequestBody ReviewReqDTO.GetMyReviews dto,
            @RequestParam Integer pageSize,
            @RequestParam(defaultValue = "-1") String cursor,
            @RequestParam String query
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_LIST_SUCCESS,
                reviewService.getMyReviews(dto.memberId(),pageSize,cursor,query)
        );
    }
}
