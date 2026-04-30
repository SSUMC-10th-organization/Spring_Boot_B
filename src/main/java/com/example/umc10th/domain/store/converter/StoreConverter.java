package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.store.dto.StoreResponseDTO;
import java.time.LocalDateTime;

public class StoreConverter {

    public static StoreResponseDTO.ReviewResultDto toReviewResultDto(Long reviewId) {
        return StoreResponseDTO.ReviewResultDto.builder()
                .reviewId(reviewId)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
