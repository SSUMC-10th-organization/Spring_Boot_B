package org.example.erd.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MissionResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionItem { //미션 목록에서 미션을 나타내는 것
        private Long missionId;
        private String missionName;
        private Integer point;
        private String status;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListRes { // 미션 목록 조회를 하면 나타나는 응답
        private List<MissionItem> missionList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionCompleteRes { // 미션 성공 처리 후 보여주는 응답
        private Long memberMissionId;
        private String status;
    }
}
