package com.example.umc10th.domain.user.dto.req;

import com.example.umc10th.domain.user.enums.FoodType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class OnboardingDTO {

  @NotBlank(message = "이름은 필수입니다")
  @Size(max = 50, message = "이름은 50자 이내여야 합니다")
  private String name;

  @NotNull(message = "성별은 필수입니다")
  private Gender gender;

  @NotNull(message = "생년월일은 필수입니다")
  @Past(message = "생년월일은 오늘 이전이어야 합니다")
  private LocalDate birthDate;
  private String address;
  private String detailAddress;
  private List<FoodType> preferredFoods;
  private AgreementDto agreements;

  @Getter
  @NoArgsConstructor
  public static class AgreementDto {
    private Boolean ageOverFourteen;
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

