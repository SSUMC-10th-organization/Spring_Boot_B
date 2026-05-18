package org.example.erd.domain.mission.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.erd.domain.mission.converter.MissionConverter;
import org.example.erd.domain.mission.dto.MissionReqDTO;
import org.example.erd.domain.mission.dto.MissionResDTO;
import org.example.erd.domain.mission.entity.Mission;
import org.example.erd.domain.mission.entity.mapping.MemberMission;
import org.example.erd.domain.mission.enums.MissionStatus;
import org.example.erd.domain.mission.repository.MemberMissionRepository;
import org.example.erd.domain.mission.repository.MissionRepository;
import org.example.erd.domain.store.entity.Store;
import org.example.erd.domain.store.exception.StoreException;
import org.example.erd.domain.store.exception.code.StoreErrorCode;
import org.example.erd.domain.store.repository.StoreRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    // 내 미션 조회
    public MissionResDTO.MissionListPageRes getMyMissions(
            Long memberId,
            MissionStatus status,
            Pageable pageable

    )
    {
        Page<MemberMission> page = memberMissionRepository
                .findByMemberIdAndMissionStatus(memberId,status,pageable);

        return MissionConverter.toMissionListPageRes(page);
    }

    // 가게 미션 생성
    @Transactional
    public Void createMission(
            Long storeId,
            MissionReqDTO.CreateMission dto
    ){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(store,dto);

        missionRepository.save(mission);
        return null;
    }





    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);
        Slice<Mission> missionSlice;
        String nextCursor;

        if (cursor.equals("-1")) {
            missionSlice = missionRepository.findByStore_IdOrderByIdDesc(storeId, pageRequest);
        } else {
            String[] cursorSplit = cursor.split(":");
            Long idCursor = Long.parseLong(cursorSplit[1]);
            missionSlice = missionRepository.findByStore_IdAndIdLessThanOrderByIdDesc(storeId, idCursor, pageRequest);
        }

        if (missionSlice.hasNext()) {
            Mission last = missionSlice.getContent().get(missionSlice.getContent().size() - 1);
            nextCursor = "id:" + last.getId();
        } else {
            nextCursor = null;
        }

        List<MissionResDTO.GetMission> missions = missionSlice.getContent().stream()
                .map(MissionConverter::toGetMission)
                .toList();

        return MissionConverter.toPagination(missions, missionSlice.hasNext(), nextCursor, pageSize);
    }

}
