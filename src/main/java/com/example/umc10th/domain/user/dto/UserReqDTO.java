package com.example.umc10th.domain.user.dto;

import lombok.Getter;

public class UserReqDTO {

    @Getter
    public static class SignUpDTO {
        private String name;
        private String email;
        private String password;
    }

    @Getter
    public static class LoginDTO {
        private String email;
        private String password;
    }

    @Getter
    public static class MyPageRequest {
    }
}