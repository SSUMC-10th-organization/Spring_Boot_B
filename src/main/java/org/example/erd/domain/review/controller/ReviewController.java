package org.example.erd.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.example.erd.domain.review.dto.ReviewReqDTO;
import org.example.erd.domain.review.dto.ReviewResDTO;
import org.example.erd.domain.review.exception.code.ReviewSuccessCode;
import org.example.erd.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class ReviewController {

    @PostMapping("/{storeId}/reviews") //리뷰 작성
    public ApiResponse<ReviewResDTO.CreateReviewRes> createReview(
            @PathVariable Long storeId,
            @RequestHeader("Authorization") String authorization,
            @RequestBody ReviewReqDTO.CreateReview dto
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATE_SUCCESS, null);
    }
}
