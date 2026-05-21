package com.example.coco.domain.mission.repository;

import com.example.coco.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 홈 화면 쿼리: 현재 선택된 지역에서 도전이 가능한 미션 목록 (내가 아직 시작하지 않은 미션만 리스트업, 페이징 포함)
    @Query(value = "SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "WHERE s.region.id = :regionId " +
            "AND m.id NOT IN (SELECT mm.mission.id FROM MemberMission mm WHERE mm.member.id = :memberId)",
            countQuery = "SELECT count(m) FROM Mission m JOIN m.store s WHERE s.region.id = :regionId")
    Page<Mission> findAvailableMissionsByRegion(@Param("regionId") Long regionId,
                                                @Param("memberId") Long memberId,
                                                Pageable pageable);
}
