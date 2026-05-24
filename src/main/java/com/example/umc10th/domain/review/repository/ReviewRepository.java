package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 가게 리뷰 목록 - 첫 페이지
    @Query("SELECT r FROM Review r WHERE r.restaurant.id = :restaurantId AND r.deletedAt IS NULL ORDER BY r.id DESC")
    Slice<Review> findByRestaurantId(
            @Param("restaurantId") Long restaurantId,
            Pageable pageable
    );

    // 가게 리뷰 목록 - 커서 페이징
    @Query("SELECT r FROM Review r WHERE r.restaurant.id = :restaurantId AND r.id < :cursor AND r.deletedAt IS NULL ORDER BY r.id DESC")
    Slice<Review> findByRestaurantIdWithCursor(
            @Param("restaurantId") Long restaurantId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}