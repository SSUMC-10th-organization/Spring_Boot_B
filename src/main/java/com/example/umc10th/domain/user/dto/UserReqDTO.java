package com.example.umc10th.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {

    @Getter
    public static class SignUp {

        @NotNull
        @Size(max = 10)
        private String name;

        @NotNull
        private Gender gender;

        @NotNull
        private LocalDate birth;

        @NotNull
        private String address;

        @NotNull
        private String detailAddress;

        @Size(max = 100)
        private String email;

        @Size(max = 30)
        private String phone;

        private List<FoodCategory> foodCategories;

        @NotNull
        private List<Long> termIds;

        public enum Gender {
            MALE, FEMALE
        }

        public enum FoodCategory {
            NONE, KOREAN, JAPANESE, CHINESE,
            WESTERN, CHICKEN, PORK, GRILL,
            LUNCHBOX, SNACK, FASTFOOD, DESSERT, ASIAN
        }
    }

    @Getter
    public static class Login {
        private String email;
        private String password;
    }
}