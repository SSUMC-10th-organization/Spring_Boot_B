package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

public class MissionReqDTO {

    @Getter
    public static class MyMissionRequest {

        @NotNull(message = "사용자 ID는 필수입니다.")
        @Positive(message = "사용자 ID는 양수여야 합니다.")
        private Long userId;

        @NotNull(message = "미션 상태는 필수입니다.")
        private String status;  // IN_PROGRESS, COMPLETED, CANCELED
    }

    @Getter
    public static class Complete {

        @NotNull(message = "인증 코드는 필수입니다.")
        private String verificationCode;
    }
}
