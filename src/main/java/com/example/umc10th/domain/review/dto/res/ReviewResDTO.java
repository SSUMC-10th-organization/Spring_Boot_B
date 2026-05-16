package com.example.umc10th.domain.review.dto.res;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

  @Builder
  public record MyReviewItem(
      Long reviewId,
      Integer rating,
      String content,
      Long storeId,
      String storeName
  ) {}

  @Builder
  public record CursorPage<T>(
      List<T> data,
      Long nextCursorId,
      Integer nextCursorRating,
      Boolean hasNext
  ) {}

}
