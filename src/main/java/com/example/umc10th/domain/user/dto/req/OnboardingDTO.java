package com.example.umc10th.domain.user.dto.req;

import com.example.umc10th.domain.user.enums.FoodType;
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
}

