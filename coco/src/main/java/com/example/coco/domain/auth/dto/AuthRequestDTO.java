package com.example.coco.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class AuthRequestDTO {
    public record SignUpDTO(
            @Schema(description = "이메일", example = "coco@gmail.com") String email,
            @Schema(description = "비밀번호") String password,
            @Schema(description = "닉네임") String nickname,
            @Schema(description = "성별") String gender,
            @Schema(description = "생일") String birth,
            @Schema(description = "주소") String address
    ) {}
}