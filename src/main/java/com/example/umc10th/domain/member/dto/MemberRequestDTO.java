package com.example.umc10th.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    @Schema(description = "회원 가입 요청 DTO")
    public static class JoinDto {
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "유효한 이메일 주소를 입력해주세요.")
        @Schema(description = "이메일", example = "user@example.com")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Pattern(
                regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$",
                message = "비밀번호는 영문자와 숫자를 포함한 8자 이상이어야 합니다."
        )
        @Schema(description = "비밀번호 (영문+숫자 8자 이상)", example = "password123")
        private String password;

        @NotBlank(message = "회원 이름은 필수입니다.")
        @Schema(description = "회원 이름", example = "홍길동")
        private String name;
        
        @NotNull(message = "회원 성별은 필수입니다.")
        @Schema(description = "회원 성별 (1: MALE, 2: FEMALE, 3: NONE)", example = "1")
        private Integer gender; 
        
        @NotNull(message = "생년은 필수입니다.")
        @Schema(description = "생년", example = "1999")
        private Integer birthYear;
        
        @NotNull(message = "생월은 필수입니다.")
        @Schema(description = "생월", example = "1")
        private Integer birthMonth;
        
        @NotNull(message = "생일은 필수입니다.")
        @Schema(description = "생일", example = "1")
        private Integer birthDay;
        
        @NotBlank(message = "주소는 필수입니다.")
        @Schema(description = "주소", example = "서울시 강남구")
        private String address;
        
        @Schema(description = "상세 주소", example = "테헤란로 123")
        private String detailAddress;
        
        @Size(min = 1, message = "선호하는 음식 카테고리는 최소 1개 이상 선택해야 합니다.")
        @Schema(description = "선호하는 음식 카테고리 ID 리스트", example = "[1, 2, 3]")
        private List<Long> preferCategory;
    }

    @Getter
    @Schema(description = "로그인 요청 DTO")
    public static class LoginDto {
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "유효한 이메일 주소를 입력해주세요.")
        @Schema(description = "이메일", example = "user@example.com")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Schema(description = "비밀번호", example = "password123")
        private String password;
    }
}
