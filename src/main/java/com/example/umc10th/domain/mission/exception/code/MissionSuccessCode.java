package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseCode {

  CREATED(HttpStatus.OK, "MISSION200_1", "성공적으로 미션을 생성했습니다."),
  OK(HttpStatus.OK, "MISSION200_2", "성공적으로 미션을 조회했습니다."),
  CHALLENGE_OK(HttpStatus.OK, "MISSION200_3", "미션 도전을 시작했습니다."),
  IN_PROGRESS_OK(HttpStatus.OK, "MISSION200_4", "진행중인 미션 목록을 조회했습니다.");

  private final HttpStatus status;
  private final String code;
  private final String message;
}
