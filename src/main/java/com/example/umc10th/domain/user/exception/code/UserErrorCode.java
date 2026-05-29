package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseCode {

  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404_1", "유저를 찾을 수 없습니다"),
  EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER409_1", "이미 가입된 이메일입니다");

  private final HttpStatus status;
  private final String code;
  private final String message;
}
