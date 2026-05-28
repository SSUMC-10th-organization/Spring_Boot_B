package com.example.umc10th.domain.user.service;


import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.*;
import com.example.umc10th.domain.user.entity.mapping.UserCategory;
import com.example.umc10th.domain.user.entity.mapping.UserTerm;
import com.example.umc10th.domain.user.enums.Address;
import com.example.umc10th.domain.user.enums.CategoryName;
import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.enums.SocialType;
import com.example.umc10th.domain.user.repository.*;
import com.example.umc10th.global.security.auth.AuthUser;
import com.example.umc10th.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final TermRepository termRepository;
    private final UserTermRepository userTermRepository;
    private final CategoryRepository categoryRepository;
    private final UserCategoryRepository userCategoryRepository;

    @Transactional
    public UserResDTO.SignUp signUp(UserReqDTO.SignUp request) {

        // 유저 생성
        User user = User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .socialType(SocialType.LOCAL)
                .socialId(0L)
                .gender(Gender.valueOf(request.getGender().name()))
                .birth(request.getBirth())
                .address(request.getAddress() != null ? Address.valueOf(request.getAddress()) : null)
                .detailAddress(request.getDetailAddress())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
        userRepository.save(user);

        // 약관 동의 저장
        if (request.getTermIds() != null && !request.getTermIds().isEmpty()) {
            List<Term> terms = termRepository.findAllByIdIn(request.getTermIds());
            List<UserTerm> userTerms = terms.stream()
                    .map(term -> UserTerm.builder().user(user).term(term).build())
                    .collect(Collectors.toList());
            userTermRepository.saveAll(userTerms);
        }

        // 선호 음식 저장
        if (request.getFoodCategories() != null && !request.getFoodCategories().isEmpty()) {
            List<CategoryName> categoryNames = request.getFoodCategories().stream()
                    .map(fc -> CategoryName.valueOf(fc.name()))
                    .collect(Collectors.toList());
            List<Category> categories = categoryRepository.findAllByCategoryNameIn(categoryNames);
            List<UserCategory> userCategories = categories.stream()
                    .map(category -> UserCategory.builder().user(user).category(category).build())
                    .collect(Collectors.toList());
            userCategoryRepository.saveAll(userCategories);
        }

        return UserResDTO.SignUp.builder()
                .userId(user.getId())
                .name(user.getName())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public UserResDTO.MyPage getMyProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        return UserResDTO.MyPage.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .myPoint(user.getPoint())
                .build();
    }

    public UserResDTO.Login login(UserReqDTO.Login request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtUtil.createAccessToken(new AuthUser(user));

        return UserResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }
}
