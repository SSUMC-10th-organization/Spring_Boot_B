package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.converter.StoreConverter;
import com.example.umc10th.domain.store.dto.StoreRequestDTO;
import com.example.umc10th.domain.store.dto.StoreResponseDTO;
import com.example.umc10th.domain.store.entity.Review;
import com.example.umc10th.domain.store.service.ReviewCommandService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
@Tag(name = "Store API", description = "가게 관련 API")
public class StoreController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{storeId}/reviews")
    @Operation(summary = "가게 리뷰 작성 API", description = "특정 가게에 리뷰를 작성하는 API입니다.")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다.")
    })
    public ApiResponse<StoreResponseDTO.ReviewResultDto> createReview(
            @PathVariable Long storeId,
            @RequestBody StoreRequestDTO.ReviewDto request) {
        
        Review review = reviewCommandService.createReview(request.getMemberId(), storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toReviewResultDto(review.getId()));
    }
}
