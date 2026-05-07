package com.example.umc10th.domain.mission.repository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.restaurant r " +
            "WHERE r.id = :restaurantId " +
            "AND m.deletedAt IS NULL " +
            "ORDER BY m.createdAt DESC")
    Page<Mission> findByRestaurantId(
            @Param("restaurantId") Long restaurantId,
            Pageable pageable);
}