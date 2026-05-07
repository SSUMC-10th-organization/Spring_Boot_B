package com.example.umc10th.domain.restaurant.entity;

@Entity
@Table(name = "location")
@Getter
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(length = 255, nullable = false)
    private String name;
}
