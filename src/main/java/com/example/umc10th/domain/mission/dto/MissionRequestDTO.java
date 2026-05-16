package com.example.umc10th.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class MissionRequestDTO {

    @Getter
    @Schema(description = "미션 도전 요청 DTO")
    public static class ChallengeDto {
        @Schema(description = "도전하는 회원의 ID", example = "1")
        private Long memberId;
    }
}
