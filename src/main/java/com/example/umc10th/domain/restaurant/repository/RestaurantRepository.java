package com.example.umc10th.domain.restaurant.repository;

import com.example.umc10th.domain.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByIdAndStatus(Long id, Restaurant.Status status);
    // 폐업한 식당 거르고 OPEN인 식당만 조회할 때
}