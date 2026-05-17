package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

  boolean existsByUserIdAndMissionId(
      Long userId, Long missionId, MissionStatus status
  );

  // Store name 들어가서 join fetch
  @Query("SELECT um FROM UserMission um " +
      "JOIN FETCH um.mission m " +
      "JOIN FETCH m.store " +
      "WHERE um.user.id = :userId AND um.status = :status")
  Page<UserMission> findAllByUserIdAndStatusWithMissionAndStore(
      @Param("userId") Long userId,
      @Param("status") MissionStatus status,
      Pageable pageable
  );
}
