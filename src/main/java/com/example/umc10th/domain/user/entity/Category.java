package com.example.umc10th.domain.user.entity;

@Entity
@Table(name = "category")
@Getter
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(length = 30, nullable = false)
    private String categoryName;
}