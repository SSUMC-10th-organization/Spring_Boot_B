package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionList {
        private List<MyMissionSummary> missions;
        private Long nextCursor;        // nullable
        private boolean hasNext;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionSummary {
        private Long userMissionId;
        private Long missionId;
        private String restaurantName;
        private String category;
        private Integer orderPrice;
        private Integer rewardPoint;
        private LocalDate deadline;     // nullable
        private String status;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class UserMission {
        private Long userMissionId;
        private String status;
        private LocalDateTime startedAt; // nullable
    }
}