package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.entity.mapping.UserCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_name", nullable = false)
    private CategoryName categoryName;

    // 연관관계
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<UserCategory> userCategories = new ArrayList<>();

    public enum CategoryName {
        NONE, KOREAN, JAPANESE, CHINESE,
        WESTERN, CHICKEN, PORK, GRILL,
        LUNCHBOX, SNACK, FASTFOOD, DESSERT, ASIAN
    }
}