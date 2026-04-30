package com.example.umc10th.domain.user.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class OnboardingDTO {
  private String name;
  private Gender gender;
  private LocalDate birthDate;
  private String address;
  private String detailAddress;
  private List<FoodType> preferredFoods;
  private AgreementDto agreements;

  @Getter
  @NoArgsConstructor
  public static class AgreementDto {
    private Boolean isAgeOverFourteen;
    private Boolean termsOfService;
    private Boolean privacyPolicy;
    private Boolean location;
    private Boolean marketing;
  }

  public enum Gender {
    MALE,
    FEMALE
  }

  public enum FoodType {
    KOREAN,      // 한식
    JAPANESE,    // 일식
    CHINESE,     // 중식
    WESTERN,     // 양식
    CHICKEN,     // 치킨
    BUNSIK,      // 분식
    MEAT,        // 고기/구이
    LUNCHBOX,    // 도시락
    NIGHT_FOOD,  // 야식
    FASTFOOD,    // 패스트푸드
    DESSERT,     // 디저트
    ASIAN        // 아시안푸드
  }
}

