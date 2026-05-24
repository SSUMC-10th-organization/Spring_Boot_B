package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 홈 화면: 특정 지역의 미션 목록 (커서 페이징)
    @Query("SELECT m FROM Mission m JOIN m.restaurant r WHERE r.location.id = :locationId AND m.id < :cursor ORDER BY m.id DESC")
    Slice<Mission> findByLocationIdWithCursor(
            @Param("locationId") Long locationId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    // 홈 화면 첫 페이지 (cursor 없을 때)
    @Query("SELECT m FROM Mission m JOIN m.restaurant r WHERE r.location.id = :locationId ORDER BY m.id DESC")
    Slice<Mission> findByLocationId(
            @Param("locationId") Long locationId,
            Pageable pageable
    );
}