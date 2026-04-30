package com.example.umc10th.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MissionListResponseDTO {
  private List<MissionDto> missions;

  @Getter
  @Builder
  public static class MissionDto {
    private Long userMissionId;
    private String storeName;
    private String missionCondition;
    private Integer rewardPoint;
    private MissionStatus status;
    private Boolean isReviewed;
  }

  public enum MissionStatus {
    COMPLETED,
    FAILED
  }
}
