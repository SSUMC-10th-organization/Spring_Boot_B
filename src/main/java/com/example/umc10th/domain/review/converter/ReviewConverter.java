package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.req.ReviewCreateReqDTO;
import com.example.umc10th.domain.review.entity.Review;

public class ReviewConverter {

  public static Review toReview(ReviewCreateReqDTO dto, Store store) {
    return Review.builder()
        .store(store)
        .content(dto.getContent())
        .rating(dto.getRating())
        .build();
  }
}
