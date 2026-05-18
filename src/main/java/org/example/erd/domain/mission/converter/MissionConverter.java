package org.example.erd.domain.mission.converter;

import org.example.erd.domain.mission.dto.MissionReqDTO;
import org.example.erd.domain.mission.dto.MissionResDTO;
import org.example.erd.domain.mission.entity.Mission;
import org.example.erd.domain.mission.entity.mapping.MemberMission;
import org.example.erd.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionItem toMissionItem(MemberMission memberMission)
    {
        return MissionResDTO.MissionItem.builder()

                .missionId(memberMission.getMission().getId())

                .missionName(memberMission.getMission().getConditional())

                .point(memberMission.getMission().getPoint())

                .status(memberMission.getMissionStatus().name())
                .build();
    }

    public static MissionResDTO.MissionListPageRes toMissionListPageRes(Page<MemberMission> page) {
        List<MissionResDTO.MissionItem> items = page.getContent().stream()

                .map(MissionConverter::toMissionItem)
                .toList();

        return MissionResDTO.MissionListPageRes.builder()
                .missionList(items)
                .hasNext(page.hasNext())
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }

    // 가게 미션 생성
    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ){
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    // 가게 내 미션 조회
    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ){
        return MissionResDTO.GetMission.builder()
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

    // 페이지네이션 틀 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }






}
