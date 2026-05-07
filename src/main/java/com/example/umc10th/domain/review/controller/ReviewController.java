package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.req.ReviewCreateReqDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
  private final ReviewService reviewService;

  @PostMapping("/stores/{storeId}/reviews")
  public ApiResponse<Long> createReview(
      @PathVariable Long storeId,
      @RequestBody ReviewCreateReqDTO request
  ){
    Long reviewId = reviewService.createReview(storeId, request);
    return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewId);
  }
}
