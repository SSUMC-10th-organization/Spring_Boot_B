package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.entity.mapping.Food;
import com.example.umc10th.domain.user.enums.FoodType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "preference_food")
public class PreferenceFood {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "preference_food_id")
  private Long id;

  @Column(name = "food_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private FoodType foodType;

}
