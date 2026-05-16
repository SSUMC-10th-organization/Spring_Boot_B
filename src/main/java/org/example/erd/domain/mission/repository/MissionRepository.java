package org.example.erd.domain.mission.repository;

import org.example.erd.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission,Long> {

    @Query("SELECT m FROM Mission m WHERE m.store.region.id = :regionId AND m.id NOT IN :excludedIds")
    Page<Mission> findAvailableMissions(
            @Param("regionId") Long regionId,
            @Param("excludedIds") List<Long> excludedIds,
            Pageable pageable);

    List<Mission> findAllByStore_Id(Long storeId);
    Page<Mission> findAllByStore_Id(Long storeId, Pageable pageable);

    // 커서 없을 때
    Slice<Mission>
    findByStore_IdOrderByIdDesc(Long storeId, Pageable pageable);

    // 커서 있을 때
    Slice<Mission>
    findByStore_IdAndIdLessThanOrderByIdDesc(
            Long storeId, Long id, Pageable pageable
    );

}

