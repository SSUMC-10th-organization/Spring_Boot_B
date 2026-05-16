package org.example.erd.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.erd.domain.mission.enums.MissionStatus;

import java.time.LocalDate;

public class MissionReqDTO {

    // 가게 미션 생성
    public record CreateMission(
            @NotNull(message = "마감일은 필수입니다.")
            LocalDate deadline,
            @NotNull(message = "포인트는 1 이상이어야 합니다.")
            @Min(value = 1, message = "포인트는 1 이상이어야 합니다.")
            Integer point,
            @NotBlank(message = "미션 조건은 필수입니다.")
            String conditional
    ){}

    // 진행중인 미션 조회
    public record GetChallengingMissions(
            Long memberId
    ){}

    // 전체 미션 조회 (진행중 + 완료)
    public record GetMyMissions(
            Long memberId,
            MissionStatus status
    ) {}
}
