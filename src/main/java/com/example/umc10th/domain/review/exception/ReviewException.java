package com.example.umc10th.domain.review.exception;

import com.example.umc10th.global.apiPayload.code.BaseCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class ReviewException extends ProjectException {
  public ReviewException(BaseCode errorCode) {
    super(errorCode);
  }
}
