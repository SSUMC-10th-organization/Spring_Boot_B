package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.MemberMission;
import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDTO.ChallengeResultDto toChallengeResultDto(Long memberMissionId) {
        return MissionResponseDTO.ChallengeResultDto.builder()
                .memberMissionId(memberMissionId)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MissionResponseDTO.MissionDto toMissionDto(Mission mission) {
        return MissionResponseDTO.MissionDto.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .point(mission.getPoint())
                .condition(mission.getCondition())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResponseDTO.MissionListDto toMissionListDto(Page<Mission> missionPage) {
        List<MissionResponseDTO.MissionDto> missionDtoList = missionPage.stream()
                .map(MissionConverter::toMissionDto)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionListDto.builder()
                .missionList(missionDtoList)
                .listSize(missionPage.getSize())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

    public static MissionResponseDTO.MemberMissionDto toMemberMissionDto(MemberMission memberMission) {
        return MissionResponseDTO.MemberMissionDto.builder()
                .memberMissionId(memberMission.getId())
                .storeName(memberMission.getMission().getStore().getName())
                .point(memberMission.getMission().getPoint())
                .conditional(memberMission.getMission().getCondition())
                .isComplete(memberMission.getComplete())
                .build();
    }

    public static MissionResponseDTO.MemberMissionListDto toMemberMissionListDto(Page<MemberMission> memberMissionPage) {
        List<MissionResponseDTO.MemberMissionDto> memberMissionDtoList = memberMissionPage.stream()
                .map(MissionConverter::toMemberMissionDto)
                .collect(Collectors.toList());

        return MissionResponseDTO.MemberMissionListDto.builder()
                .missionList(memberMissionDtoList)
                .listSize(memberMissionPage.getSize())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }
}
