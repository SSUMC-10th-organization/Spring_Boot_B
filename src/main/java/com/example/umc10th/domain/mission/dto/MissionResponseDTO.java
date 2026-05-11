package com.example.umc10th.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "미션 도전 결과 응답 DTO")
    public static class ChallengeResultDto {
        @Schema(description = "생성된 멤버-미션 매핑 ID", example = "1")
        private Long memberMissionId;
        @Schema(description = "생성 일시")
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "지역별 미션 목록 응답 DTO")
    public static class MissionListDto {
        @Schema(description = "미션 목록")
        private List<MissionDto> missionList;
        @Schema(description = "리스트 크기", example = "10")
        private Integer listSize;
        @Schema(description = "전체 페이지 수", example = "5")
        private Integer totalPage;
        @Schema(description = "전체 데이터 개수", example = "42")
        private Long totalElements;
        @Schema(description = "첫 페이지 여부", example = "true")
        private Boolean first;
        @Schema(description = "마지막 페이지 여부", example = "false")
        private Boolean last;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "개별 미션 정보 DTO")
    public static class MissionDto {
        @Schema(description = "미션 ID", example = "1")
        private Long missionId;
        @Schema(description = "가게 이름", example = "반이학생마라탕")
        private String storeName;
        @Schema(description = "성공 시 획득 포인트", example = "500")
        private Integer point;
        @Schema(description = "미션 조건", example = "10,000원 이상 식사시")
        private String condition;
        @Schema(description = "미션 기한")
        private LocalDate deadline;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "사용자 미션 목록 응답 DTO")
    public static class MemberMissionListDto {
        @Schema(description = "미션 목록")
        private List<MemberMissionDto> missionList;
        @Schema(description = "리스트 크기", example = "10")
        private Integer listSize;
        @Schema(description = "전체 페이지 수", example = "5")
        private Integer totalPage;
        @Schema(description = "전체 데이터 개수", example = "42")
        private Long totalElements;
        @Schema(description = "첫 페이지 여부", example = "true")
        private Boolean first;
        @Schema(description = "마지막 페이지 여부", example = "false")
        private Boolean last;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "개별 사용자 미션 정보 DTO")
    public static class MemberMissionDto {
        @Schema(description = "멤버-미션 매핑 ID", example = "1")
        private Long memberMissionId;
        @Schema(description = "가게 이름", example = "반이학생마라탕")
        private String storeName;
        @Schema(description = "성공 시 획득 포인트", example = "500")
        private Integer point;
        @Schema(description = "미션 조건", example = "10,000원 이상 식사시")
        private String conditional;
        @Schema(description = "미션 완료 여부", example = "false")
        private Boolean complete;
    }
}
