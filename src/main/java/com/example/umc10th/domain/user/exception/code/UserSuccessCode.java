package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseCode {

  SIGN_UP_SUCCESS(HttpStatus.CREATED, "USER201_1", "회원가입에 성공했습니다"),
  LOGIN_SUCCESS(HttpStatus.OK, "USER200_1", "로그인에 성공했습니다");

  private final HttpStatus status;
  private final String code;
  private final String message;
}
