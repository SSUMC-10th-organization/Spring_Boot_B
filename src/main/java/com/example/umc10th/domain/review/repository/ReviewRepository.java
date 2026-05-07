package com.example.umc10th.domain.review.repository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.restaurant " +
            "LEFT JOIN FETCH r.reviewImages " +
            "WHERE r.user.id = :userId " +
            "AND r.deletedAt IS NULL " +
            "ORDER BY r.createdAt DESC")
    Page<Review> findByUserId(
            @Param("userId") Long userId,
            Pageable pageable);

    @Query("SELECT AVG(r.rate) FROM Review r " +
            "WHERE r.restaurant.id = :restaurantId")
    Float avgRateByRestaurantId(
            @Param("restaurantId") Long restaurantId);
}