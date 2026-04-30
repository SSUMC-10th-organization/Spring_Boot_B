package com.example.umc10th.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class HomeResponseDTO {
  private Integer currentPoint;
  private Integer completedCount;
  private List<MissionDto> missions;

  @Getter
  @Builder
  public static class MissionDto {
    private Long missionId;
    private String storeName;
    private Integer rewardPoint;
    private Integer dDay;
  }
}
