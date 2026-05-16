package com.example.coco.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

public class MissionRequestDTO {
    public record MyMissionOptsDTO(
            @NotNull(message = "사용자 ID는 필수 입력 항목입니다.")
            Long memberId,
            @NotNull(message = "페이지 번호는 필수입니다.")
            Integer page
    ) {}
}