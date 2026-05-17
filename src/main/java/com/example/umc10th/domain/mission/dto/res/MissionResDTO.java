package com.example.umc10th.domain.mission.dto.res;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {

  // 가게 내 미션 조회
  @Builder
  public record Summary(
      Long missionId,
      Integer rewardPoint,
      String missionCondition
  ){}

  // 페이지네이션 틀
  @Builder
  public record Pagination<T>(
      List<T> data,
      Integer pageNumber,
      Integer pageSize
  ){}

  @Builder
  public record InProgressItem(
      Long userMissionId,
      Long missionId,
      String missionCondition,
      Integer rewardPoint,
      String storeName
  ){}
}
