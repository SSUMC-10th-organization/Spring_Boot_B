package org.example.erd.domain.mission.converter;

import org.example.erd.domain.mission.dto.MissionResDTO;
import org.example.erd.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionItem toMissionItem(MemberMission memberMission)
    {
        return MissionResDTO.MissionItem.builder()

                .missionId(memberMission.getMission().getId())

                .missionName(memberMission.getMission().getDescription())

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
                .build();
    }


}
