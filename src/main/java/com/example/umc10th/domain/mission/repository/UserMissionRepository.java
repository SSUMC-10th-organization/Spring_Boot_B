package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 내 미션 목록 - 오프셋 페이지네이션
    Page<UserMission> findByUserIdAndStatus(Long userId, Status status, Pageable pageable);

    // 지역별 완료 미션 수
    @Query("SELECT COUNT(um) FROM UserMission um JOIN um.mission m JOIN m.restaurant r WHERE um.user.id = :userId AND r.location.id = :locationId AND um.status = 'COMPLETED'")
    int countCompletedByUserIdAndLocationId(
            @Param("userId") Long userId,
            @Param("locationId") Long locationId
    );

    // 이미 도전 중인 미션인지 확인
    boolean existsByUserIdAndMissionIdAndStatus(Long userId, Long missionId, Status status);
}
