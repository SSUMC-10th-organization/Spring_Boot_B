package com.example.coco.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class MissionResponseDTO {

    @Schema(description = "미션 목록 응답 DTO")
    public record MissionListDTO(
            @Schema(description = "미션 상세 목록")
            List<MissionDetailDTO> missions
    ) {}

    @Schema(description = "미션 상세 정보 DTO")
    public record MissionDetailDTO(
            @Schema(description = "미션 식별자", example = "1")
            Long id,
            @Schema(description = "가게 이름", example = "반이학생마라탕")
            String storeName,
            @Schema(description = "미션 달성 조건", example = "10,000원 이상의 식사")
            String condition,
            @Schema(description = "미션 보상 포인트", example = "500")
            Integer rewardPoint,
            @Schema(description = "남은 기간 (D-Day)", example = "7")
            Integer dDay,
            @Schema(description = "미션 상태 (progress, complete)", example = "progress")
            String status
    ) {}

    @Schema(description = "미션 완료 처리 응답 DTO")
    public record MissionCompleteResultDTO(
            @Schema(description = "유저 식별자", example = "1")
            Long userId,
            @Schema(description = "미션 식별자", example = "1")
            Long missionId,
            @Schema(description = "변경된 상태", example = "complete")
            String status,
            @Schema(description = "완료 일시", example = "2024-03-22T09:41:00Z")
            String completedAt
    ) {}
}