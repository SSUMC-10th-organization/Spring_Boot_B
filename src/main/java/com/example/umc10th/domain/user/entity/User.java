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

  @Column(nullable = false)
  private String name;

  @Column(name = "gender", nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;

  @Column(nullable = false)
  private String address;

  @Column(name = "detail_address")
  private String detailAddress;

  @Column(name = "agree_location", nullable = false)
  private Boolean agreeLocation;

  @Column(name = "agree_marketing", nullable = false)
  private Boolean agreeMarketing;

  @Column(name = "current_point")
  private Long currentPoint;

  @Column(name = "completed_count")
  private Long completedCount;

}