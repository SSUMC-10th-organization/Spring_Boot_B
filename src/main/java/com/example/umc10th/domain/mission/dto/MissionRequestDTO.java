package com.example.umc10th.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MissionRequestDTO {

    @Getter
    @Schema(description = "미션 도전 요청 DTO")
    public static class ChallengeDto {
        @NotNull(message = "도전하는 회원의 ID는 필수입니다.")
        @Schema(description = "도전하는 회원의 ID", example = "1")
        private Long memberId;
    }
    
    @Getter
    @Schema(description = "내가 진행중인 미션 조회 요청 DTO")
    public static class GetMyMissionsDto {
        @NotNull(message = "조회할 회원의 ID는 필수입니다.")
        @Schema(description = "조회할 회원의 ID", example = "1")
        private Long memberId;
    }
}
