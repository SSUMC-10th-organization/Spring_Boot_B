package com.example.umc10th.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionUpdateResponseDTO {
  private Integer missionId;
  private MissionStatus status;
  private Integer rewardPoint;

  public enum MissionStatus {
    COMPLETED,
    FAILED
  }
}
