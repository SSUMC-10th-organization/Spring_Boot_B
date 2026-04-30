package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

public class MissionResDTO {

    @Builder
    public record CreateMissionResponse(
            Long missionId,
            String title,
            Integer point,
            Long restaurantId
    ) {
    }

    @Builder
    public record GetMissionResponse(
            Long missionId,
            String title,
            String content,
            Integer point,
            String status
    ) {
    }
}