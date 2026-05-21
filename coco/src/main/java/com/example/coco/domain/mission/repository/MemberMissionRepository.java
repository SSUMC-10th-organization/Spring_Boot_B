package com.example.coco.domain.mission.repository;

import com.example.coco.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    @Query("SELECT mm FROM MemberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberId AND mm.status = :status")
    Page<MemberMission> findMyMissionsOffset(@Param("memberId") Long memberId,
                                             @Param("status") String status,
                                             Pageable pageable);
}