package org.example.erd.domain.mission.repository;

import org.example.erd.domain.mission.entity.Mission;
import org.example.erd.domain.mission.entity.mapping.MemberMission;
import org.example.erd.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findByMemberIdAndMissionStatus
            (Long memberId, MissionStatus missionStatus, Pageable pageable);

    @Query("SELECT mm FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.missionStatus =:status")
    Page<MemberMission> findByMissions(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

    Long countByMemberIdAndMissionStatus(
            Long memberId, MissionStatus missionStatus);

    Long countByMemberId(Long memberId);

    @Query("SELECT mm.mission.id FROM MemberMission  mm WHERE mm.member.id = :memberId")
    List<Long> findMissionIdsByMemberId(@Param("memberId") Long memberId);

    List<MemberMission> findByMemberIdAndMissionStatus(
            Long memberId, MissionStatus missionStatus
    );
}
