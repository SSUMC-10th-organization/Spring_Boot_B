package com.example.umc10th.domain.store.service;

import com.example.umc10th.domain.store.entity.Review;
import com.example.umc10th.domain.store.dto.StoreRequestDTO;

public interface ReviewCommandService {
    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDto request);
}
