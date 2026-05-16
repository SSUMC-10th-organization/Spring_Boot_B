package com.example.coco.domain.mission.repository;

import com.example.coco.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리 (페이징 포함)
    @Query(value = "SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberId AND mm.status = :status",
            countQuery = "SELECT count(mm) FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.status = :status")
    Page<MemberMission> findMyMissionsByStatus(@Param("memberId") Long memberId,
                                               @Param("status") String status,
                                               Pageable pageable);
}
