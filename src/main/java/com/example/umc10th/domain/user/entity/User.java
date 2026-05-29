package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.entity.mapping.Food;
import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column()
  private String name;

  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(name = "gender")
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column()
  private String address;

  @Column(name = "detail_address")
  private String detailAddress;

  @Column(name = "agree_location")
  private Boolean agreeLocation;

  @Column(name = "agree_marketing")
  private Boolean agreeMarketing;

  @Column(name = "current_point")
  private Long currentPoint;

  @Column(name = "completed_count")
  private Long completedCount;

  public static User signUp(String email, String encodedPassword) {
    User user = new User();
    user.email = email;
    user.password = encodedPassword;
    return user;
  }
}