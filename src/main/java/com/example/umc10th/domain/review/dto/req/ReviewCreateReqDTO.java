package com.example.umc10th.domain.review.dto.req;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewCreateReqDTO {
  @NotNull(message = "userId 는 필수입니다")
  private Long userId;

  @NotNull(message = "별점은 필수입니다")
  @Min(value = 1, message = "별점은 최소 1점 이상이어야 합니다")
  @Max(value = 5, message = "별점은 최대 5점까지 가능합니다")
  private Integer rating;

  @NotBlank(message = "리뷰 내용은 필수입니다")
  @Size(max = 1000, message = "리뷰 내용은 1000자 이내여야 합니다")
  private String content;
  private List<String> images;
}

