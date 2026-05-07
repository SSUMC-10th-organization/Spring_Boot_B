package org.example.erd.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MemberResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeRes {
        private String name;        // 닉네임
        private String region;      // 지역
        private Long missionSuccessCount;  // 미션 성공 개수
        private Long missionTotalCount; // 총 미션 성공 개수
        private Integer rewardPoint;
        private List<MissionInfo> challengingMissions; // 내가 도전중인 미션 목록
        private List<MissionInfo> availableMissions; // 도전 가능한 밋녀들
        private Boolean hasNext;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionInfo {
        private LocalDate deadline;      // 미션 만료 기한
        private String storeName;        // 가게 이름
        private String storeCategory;    // 가게 카테고리
        private String missionContent;   // 미션 내용
        private Integer point;           // 적립 포인트
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPageRes{
        private String name;
        private String email;
        private String phoneNumber;
        private Integer point;
    }
}
