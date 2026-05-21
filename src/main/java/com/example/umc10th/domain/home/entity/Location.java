package com.example.umc10th.domain.home.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    // 연관관계
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Restaurant> restaurants = new ArrayList<>();
}