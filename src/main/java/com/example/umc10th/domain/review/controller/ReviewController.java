package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @PostMapping
    public ApiResponse<ReviewResDTO.CreateReviewResponse> createReview(
            @RequestBody ReviewReqDTO.CreateReviewRequest request
    ) {
        ReviewResDTO.CreateReviewResponse response =
                ReviewResDTO.CreateReviewResponse.builder()
                        .reviewId(1L)
                        .userId(request.userId())
                        .restaurantId(request.restaurantId())
                        .content(request.content())
                        .score(request.score())
                        .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.REVIEW_CREATE_SUCCESS, response);
    }

    @PostMapping("/detail")
    public ApiResponse<ReviewResDTO.GetReviewResponse> getReview(
            @RequestBody ReviewReqDTO.GetReviewRequest request
    ) {
        ReviewResDTO.GetReviewResponse response =
                ReviewResDTO.GetReviewResponse.builder()
                        .reviewId(request.reviewId())
                        .userName("nickname012")
                        .restaurantName("맛있는 식당")
                        .content("음식이 맛있고 친절했습니다.")
                        .score(4.5)
                        .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.REVIEW_GET_SUCCESS, response);
    }
}