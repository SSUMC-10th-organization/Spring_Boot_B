package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.res.MissionResDTO;
import com.example.umc10th.domain.mission.dto.res.MissionUpdateResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class MissionConverter {

  public static MissionUpdateResponseDTO toMissionUpdateResponse(Mission mission) {
    return MissionUpdateResponseDTO.builder()
        .missionId(mission.getId().intValue())
        .status(MissionUpdateResponseDTO.MissionStatus.COMPLETED)
        .rewardPoint(mission.getRewardPoint())
        .build();
  }

  public static Mission toMission(
      Store store,
      MissionReqDTO.Create dto
  ) {
    return Mission.builder()
        .store(store)
        .missionCondition(dto.missionCondition())
        .rewardPoint(dto.rewardPoint())
        .endDate(dto.deadline())
        .build();
  }

  public static MissionResDTO.Summary toSummary(
      Mission mission
  ){
    return MissionResDTO.Summary.builder()
        .missionCondition(mission.getMissionCondition())
        .rewardPoint(mission.getRewardPoint())
        .missionId(mission.getId())
        .build();
  }

  public static <T> MissionResDTO.Pagination<T> toPagination(
      List<T> data,
      Integer pageNumber,
      Integer pageSize
  ){
    return MissionResDTO.Pagination.<T>builder()
        .data(data)
        .pageNumber(pageNumber)
        .pageSize(pageSize)
        .build();
  }

  public static UserMission toUserMission(User user, Mission mission){
    return UserMission.builder()
        .user(user)
        .mission(mission)
        .status(MissionStatus.IN_PROGRESS)
        .requestedDate(LocalDateTime.now())
        .reviewed(false)
        .build();
  }

  public static MissionResDTO.InProgressItem toInProgressItem(UserMission userMission){
    Mission mission = userMission.getMission();
    return MissionResDTO.InProgressItem.builder()
        .userMissionId(userMission.getId())
        .missionId(mission.getId())
        .missionCondition(mission.getMissionCondition())
        .rewardPoint(mission.getRewardPoint())
        .storeName(mission.getStore().getStoreName())
        .build();
  }


}
