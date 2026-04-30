package com.example.umc10th.domain.review.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewCreateReqDTO {
  private Integer rating;
  private String content;
  private List<String> images;
}
