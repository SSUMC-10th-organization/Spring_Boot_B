package com.example.umc10th.domain.restaurant.repository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r " +
            "WHERE r.location.id = :locationId " +
            "AND r.deletedAt IS NULL " +
            "ORDER BY r.createdAt DESC")
    Page<Restaurant> findByLocationId(
            @Param("locationId") Long locationId,
            Pageable pageable);
}