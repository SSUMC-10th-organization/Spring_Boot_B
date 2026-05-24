package com.example.umc10th.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class UserResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SignUp {
        private Long userId;
        private String name;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyPage {
        private Long userId;
        private String name;
        private String email;
        private String phone;
        private Integer myPoint;
    }


}