package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.req.ReviewCreateReqDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final StoreRepository storeRepository;

  @Transactional
  public Long createReview(Long storeId, ReviewCreateReqDTO dto) {

    // 가게 조회, 없으면 예외
    Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new ReviewException(MissionErrorCode.STORE_NOT_FOUND));

    Review review = ReviewConverter.toReview(dto, store);
    Review saved = reviewRepository.save(review);

    return saved.getId();
  }
}
