package com.example.umc10th.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    @Schema(description = "회원 가입 요청 DTO")
    public static class JoinDto {
        @Schema(description = "회원 이름", example = "홍길동")
        private String name;
        @Schema(description = "회원 성별 (1: MALE, 2: FEMALE, 3: NONE)", example = "1")
        private Integer gender; 
        @Schema(description = "생년", example = "1999")
        private Integer birthYear;
        @Schema(description = "생월", example = "1")
        private Integer birthMonth;
        @Schema(description = "생일", example = "1")
        private Integer birthDay;
        @Schema(description = "주소", example = "서울시 강남구")
        private String address;
        @Schema(description = "상세 주소", example = "테헤란로 123")
        private String detailAddress;
        @Schema(description = "선호하는 음식 카테고리 ID 리스트", example = "[1, 2, 3]")
        private List<Long> preferCategory;
    }
}
