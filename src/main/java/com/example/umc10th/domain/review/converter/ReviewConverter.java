package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.res.ReviewResDTO;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.review.dto.req.ReviewCreateReqDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.user.entity.User;

import java.util.List;

public class ReviewConverter {

  public static Review toReview(ReviewCreateReqDTO dto, Store store, User user) {
    return Review.builder()
        .store(store)
        .user(user)
        .content(dto.getContent())
        .rating(dto.getRating())
        .build();
  }
  public static ReviewResDTO.MyReviewItem toMyReviewItem(Review review) {
    return ReviewResDTO.MyReviewItem.builder()
        .reviewId(review.getId())
        .rating(review.getRating())
        .content(review.getContent())
        .storeId(review.getStore().getId())
        .storeName(review.getStore().getStoreName())
        .build();
  }

  public static <T> ReviewResDTO.CursorPage<T> toCursorPage(
      List<T> data,
      Long nextCursorId,
      Integer nextCursorRating,
      boolean hasNext
  ) {
    return ReviewResDTO.CursorPage.<T>builder()
        .data(data)
        .nextCursorId(nextCursorId)
        .nextCursorRating(nextCursorRating)
        .hasNext(hasNext)
        .build();
  }
}
