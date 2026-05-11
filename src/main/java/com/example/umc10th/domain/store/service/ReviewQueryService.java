package com.example.umc10th.domain.store.service;

import com.example.umc10th.domain.store.dto.StoreResponseDTO;

public interface ReviewQueryService {
    StoreResponseDTO.ReviewListDto getMyReviews(Long memberId, String cursor, String sortBy, int size);
}
