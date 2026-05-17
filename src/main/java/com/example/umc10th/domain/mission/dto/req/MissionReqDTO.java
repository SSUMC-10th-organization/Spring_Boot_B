package com.example.umc10th.domain.mission.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

public class MissionReqDTO {

  // 가게 미션 생성
  public record Create(
      @NotNull(message = "마감기한 필수")
      LocalDateTime deadline,
      @NotNull(message = "미션 성공 포인트는 필수")
      Integer rewardPoint,
      @NotNull(message = "조건은 빈칸이면 안됨")
      String missionCondition
  ){}

  // 미션 도전
  public record Challenge(
      @NotNull(message = "userId 는 필수입니다")
      Long userId
  ){}

  // 진행 중인 미션 조회 요청 body
  public record InProgress(
      @NotNull(message = "userId 는 필수입니다")
      Long userId
  ){}
}
