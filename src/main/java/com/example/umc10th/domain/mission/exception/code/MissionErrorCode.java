package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseCode {

  MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "미션을 찾을 수 없습니다"),
  STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_2", "가게를 찾을 수 없습니다");

  private final HttpStatus status;
  private final String code;
  private final String message;
}
