package com.example.umc10th.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Role;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}