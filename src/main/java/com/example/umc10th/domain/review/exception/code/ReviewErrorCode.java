package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseCode {

  REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "리뷰를 찾을 수 없습니다"),
  REVIEW_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_2", "리뷰 이미지를 찾을 수 없습니다");

  private final HttpStatus status;
  private final String code;
  private final String message;
}
