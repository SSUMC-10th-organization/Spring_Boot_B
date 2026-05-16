package com.example.umc10th.domain.store.entity;

import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "store_id")
  private Long id;

  @Column(name = "store_name", nullable = false, length = 100)
  private String storeName;

  @Column(name = "owner_auth_number", nullable = false, length = 50)
  private String ownerAuthNumber;

  @Column(nullable = false, length = 255)
  private String address;

  @Column(length = 255)
  private String region;

  @Column(nullable = false, length = 255)
  private String category;

  @Column(name = "average_score")
  private Float averageScore;
}
