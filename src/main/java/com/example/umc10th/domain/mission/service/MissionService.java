package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.res.MissionResDTO;
import com.example.umc10th.domain.mission.dto.res.MissionUpdateResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

  private final MissionRepository missionRepository;
  private final StoreRepository storeRepository;
  private final UserRepository userRepository;
  private final UserMissionRepository userMissionRepository;

  public MissionUpdateResponseDTO updateMission() {
    return null;
  }

  public Void createMission(Long storeId, MissionReqDTO.Create dto) {

    // 가게 찾기
    Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

    // 미션 생성
    Mission mission = MissionConverter.toMission(store, dto);

    // 미션 DB 저장
    missionRepository.save(mission);
    return null;
  }

  // 가게 내 미션들 조회
  public MissionResDTO.Pagination<MissionResDTO.Summary> getMissions(
      Long storeId,
      Integer pageSize,
      Integer pageNumber,
      String sort
  ){

    // 정렬 정보 생성
    Sort sortInfo;
    if (sort != null){
      sortInfo = Sort.by(sort);
    } else {
      sortInfo = Sort.by("id").descending();
    }

    // 페이지 정보들을 PageRequest로 만들기
    PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

    // 가게 내 미션들 조회
    Page<Mission> missionList = missionRepository.findAllByStoreId(storeId, pageRequest);

    // 미션들 응답 DTO로 포장
    return  MissionConverter.toPagination(
        missionList.map(MissionConverter::toSummary).toList(),
        missionList.getNumber(),
        missionList.getSize()
    );
  }

  public Page<MissionUpdateResponseDTO> getMission(Pageable pageable) {
    return null;
  }

  public Long challengeMission(Long missionId, Long userId){

    // 미션 조회
    Mission mission = missionRepository.findById(missionId)
        .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

    // 유저 조회
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

    // 중복 도전 체크
    boolean alreadyChallenging = userMissionRepository
        .existsByUserIdAndMissionId(userId, missionId, MissionStatus.IN_PROGRESS);
    if (alreadyChallenging) {
      throw new MissionException(MissionErrorCode.ALREADY_IN_PROGRESS);
    }

    // 미션 생성 및 저장
    UserMission userMission = MissionConverter.toUserMission(user, mission);
    UserMission saved = userMissionRepository.save(userMission);

    return saved.getId();
  }

  // 진행 중인 미션 조회
  public MissionResDTO.Pagination<MissionResDTO.InProgressItem> getInProgressMissions(
      Long userId,
      Integer pageNumber,
      Integer pageSize
  ){

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

    // 페이지 요청
    PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

    // IN_PROGRESS 상태 조회
    Page<UserMission> page = userMissionRepository
        .findAllByUserIdAndStatusWithMissionAndStore(
            userId, MissionStatus.IN_PROGRESS, pageRequest
        );

    return MissionConverter.toPagination(
        page.map(MissionConverter::toInProgressItem).toList(),
        page.getNumber(),
        page.getSize()
    );
  }
}
