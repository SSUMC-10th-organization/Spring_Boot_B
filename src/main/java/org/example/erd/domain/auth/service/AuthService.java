package org.example.erd.domain.auth.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.erd.domain.auth.dto.AuthReqDTO;
import org.example.erd.domain.auth.dto.AuthResDTO;
import org.example.erd.domain.member.entity.Food;
import org.example.erd.domain.member.entity.Member;
import org.example.erd.domain.member.entity.Term;
import org.example.erd.domain.member.entity.mapping.MemberFood;
import org.example.erd.domain.member.entity.mapping.MemberTerm;
import org.example.erd.domain.member.enums.FoodType;
import org.example.erd.domain.member.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final TermRepository termRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final MemberTermRepository memberTermRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResDTO.SignUpRes signUp(AuthReqDTO.SignUpDTO request) {
        Member member = Member.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .name(request.name())
                .gender(request.gender())
                .birth(request.birthDate())
                .address(request.address())
                .detailAddress(request.detailAddress())
                .phoneNumber(request.phoneNumber())
                .point(0)
                .build();

        member = memberRepository.save(member);

        for (FoodType foodType : request.foodTypes()) {
            Food food = foodRepository.findByFoodType(foodType).orElseThrow();

            memberFoodRepository.save(MemberFood.builder().member(member).food(food).build());
        }

        for (Long termId : request.termIds()) {
            Term term = termRepository.findById(termId).orElseThrow();

            memberTermRepository.save(MemberTerm.builder().member(member).term(term).isAgreed(true).build());
        }

        return new AuthResDTO.SignUpRes(member.getId());
    }
}
