package com.example.umc10th.domain.mission.entity.mapping;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_mission")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMission extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_mission_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mission_id", nullable = false)
  private Mission mission;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private MissionStatus status;

  @Column(name = "user_auth_number", length = 50)
  private String userAuthNumber;

  @Column(name = "requested_date")
  private LocalDateTime requestedDate;

  @Column(name = "completed_date")
  private LocalDateTime completedDate;

  @Column(name = "is_reviewd", nullable = false)
  private Boolean reviewed;
}
