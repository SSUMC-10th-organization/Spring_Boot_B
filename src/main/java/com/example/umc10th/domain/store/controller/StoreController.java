package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.converter.StoreConverter;
import com.example.umc10th.domain.store.dto.StoreRequestDTO;
import com.example.umc10th.domain.store.dto.StoreResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.ReviewResultDto> createReview(
            @PathVariable Long storeId,
            @RequestBody StoreRequestDTO.ReviewDto request) {
        
        // 더미 로직: 실제 DB 저장 없이 임의의 리뷰 ID와 현재 시간을 반환
        return ApiResponse.onSuccess(StoreConverter.toReviewResultDto(1L));
    }
}
