package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.converter.StoreConverter;
import com.example.umc10th.domain.store.dto.StoreRequestDTO;
import com.example.umc10th.domain.store.dto.StoreResponseDTO;
import com.example.umc10th.domain.store.entity.Review;
import com.example.umc10th.domain.store.service.ReviewCommandService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.ReviewResultDto> createReview(
            @PathVariable Long storeId,
            @RequestBody StoreRequestDTO.ReviewDto request) {
        
        Review review = reviewCommandService.createReview(request.getMemberId(), storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toReviewResultDto(review.getId()));
    }
}
