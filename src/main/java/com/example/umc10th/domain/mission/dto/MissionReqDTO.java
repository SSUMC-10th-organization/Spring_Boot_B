package com.example.umc10th.domain.mission.dto;

public class MissionReqDTO {

    public record CreateMissionRequest(
            String title,
            Integer point,
            Long restaurantId
    ) {
    }

    public record GetMissionRequest(
            Long missionId
    ) {
    }
}