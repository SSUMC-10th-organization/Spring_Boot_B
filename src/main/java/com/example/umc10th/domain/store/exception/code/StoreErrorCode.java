package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseCode {

  NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "해당 가게가 존재하지 않습니다");

  private final HttpStatus status;
  private final String code;
  private final String message;
}
