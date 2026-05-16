package com.example.umc10th.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "회원 가입 응답 DTO")
    public static class JoinResultDto {
        @Schema(description = "가입된 회원 ID", example = "1")
        private Long memberId;
        @Schema(description = "가입 일시")
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "마이페이지 조회 응답 DTO")
    public static class MyPageDto {
        @Schema(description = "회원 ID", example = "1")
        private Long memberId;
        @Schema(description = "회원 닉네임", example = "nickname012")
        private String nickname;
        @Schema(description = "회원 이메일", example = "test@example.com")
        private String email;
        @Schema(description = "회원 전화번호", example = "010-1234-5678")
        private String phoneNumber;
        @Schema(description = "회원 보유 포인트", example = "2500")
        private Integer point;
    }
}
