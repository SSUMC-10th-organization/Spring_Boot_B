package org.example.erd.domain.member.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.erd.domain.member.entity.mapping.MemberFood;
import org.example.erd.domain.member.enums.FoodType;
import org.example.erd.global.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    @OneToMany(mappedBy = "food", cascade = CascadeType.REMOVE)
    private List<MemberFood> memberFoodList = new ArrayList<>();
}
