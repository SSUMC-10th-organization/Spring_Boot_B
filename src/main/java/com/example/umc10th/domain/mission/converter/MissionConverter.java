package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.res.MissionUpdateResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;

public class MissionConverter {

  public static MissionUpdateResponseDTO toMissionUpdateResponse(Mission mission) {
    return MissionUpdateResponseDTO.builder()
        .missionId(mission.getId().intValue())
        .status(MissionUpdateResponseDTO.MissionStatus.COMPLETED)
        .rewardPoint(mission.getRewardPoint())
        .build();
  }
}
