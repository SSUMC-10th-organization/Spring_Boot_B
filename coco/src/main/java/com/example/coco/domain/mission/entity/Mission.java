package com.example.coco.domain.mission.entity;

import com.example.coco.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String missionCondition; // DTO의 condition 필드와 매핑 (SQL 예약어 피하기 위해 변수명 변경)
    private Integer rewardPoint;
    private Integer dDay;
    private String status; // active 등

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}