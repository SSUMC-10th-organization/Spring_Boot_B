package org.example.erd.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.erd.domain.member.enums.FoodType;
import org.example.erd.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class AuthReqDTO {
    public record SignUpDTO(
            @Schema(description = "사용자 이메일", example = "UMC@gmail.com")
        String email,
            @Schema(description = "사용자 비밀번호")
        String password,
            @Schema(description = "이름")
        String name,
            @Schema(description = "성별")
        Gender gender,
            @Schema(description = "생년월일")
        LocalDate birthDate,
            @Schema(description = "주소")
        String address,
            @Schema(description = "상세주소")
        String detailAddress,
            @Schema(description = "선호하는 음식")
            List<FoodType> foodTypes,
            @Schema(description = "동의한 약관 ID 목록", example = "[1,2]")
            List<Long> termIds





    ) {}
    public record SocialSignUpDTO(
            @Schema(description = "이름")
            String name,

            @Schema(description = "성별")
            Gender gender,

            @Schema(description = "생년월일")
            LocalDate birthDate,

            @Schema(description = "주소")
            String address,

            @Schema(description = "상세주소")
            String detailAddress,

            @Schema(description = "선호하는 음식")
            List<FoodType> foodTypes,

            @Schema(description = "동의한 약관 ID 목록", example = "[1,2]")
            List<Long> termIds
    ) {}

}
