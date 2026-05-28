package org.example.erd.domain.member.repository;

import org.example.erd.domain.member.entity.Food;
import org.example.erd.domain.member.enums.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food,Long> {
    Optional<Food> findByFoodType(FoodType foodType);
}
