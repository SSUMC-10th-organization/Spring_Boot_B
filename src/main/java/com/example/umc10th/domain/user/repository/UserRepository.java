package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySocialTypeAndSocialId(SocialType socialType, Long socialId);
    // 소셜 로그인할 때 이미 가입한 유저인지 확인

    boolean existsByEmail(String email);
    // 이메일 중복 체크
}