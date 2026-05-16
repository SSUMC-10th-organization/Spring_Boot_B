package org.example.erd.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.erd.domain.member.entity.mapping.MemberFood;
import org.example.erd.domain.member.entity.mapping.MemberTerm;
import org.example.erd.domain.member.enums.Gender;
import org.example.erd.domain.member.enums.SocialType;
import org.example.erd.domain.review.entity.Review;
import org.example.erd.global.common.BaseEntity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;
    @Column(name = "birth",nullable = false)
    private LocalDate birth;
    @Column(name = "address",nullable = false)
    private String address;
    @Column(name = "detail_address",nullable = false)
    private String detailAddress;

    private Integer point;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Review> reviewList = new ArrayList<>();

}
