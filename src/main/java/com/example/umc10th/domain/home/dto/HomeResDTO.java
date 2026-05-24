package com.example.umc10th.domain.home.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class HomeResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Home {
        private String locationName;
        private int clearedCount;
        private int totalCount;
        private List<MissionSummary> missions;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionSummary {
        private Long missionId;
        private String restaurantName;
        private String category;
        private int rewardPoint;
        private LocalDate deadline;
        private String status;
    }
}