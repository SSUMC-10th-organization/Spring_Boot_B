package com.example.umc10th.domain.member.dto;

import lombok.Getter;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDto {
        private String name;
        private Integer gender; // 1: MALE, 2: FEMALE, 3: NONE
        private Integer birthYear;
        private Integer birthMonth;
        private Integer birthDay;
        private String address;
        private String detailAddress;
        private List<Long> preferCategory;
    }
}
