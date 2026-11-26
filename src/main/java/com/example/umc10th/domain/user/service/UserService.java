package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.req.LoginReqDTO;
import com.example.umc10th.domain.user.dto.req.SignUpReqDTO;
import com.example.umc10th.domain.user.dto.res.LoginResDTO;
import com.example.umc10th.domain.user.dto.res.MyPageResponseDTO;
import com.example.umc10th.domain.user.dto.res.SignUpResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.security.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  public MyPageResponseDTO getMyPage(Long userId) {

    // deletedDateIsNull 조건
    User user = userRepository.findByIdAndDeletedDateIsNull(userId)
        .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

    return UserConverter.toMyPageResponse(user);
  }

  public Void onboarding() {
    return null;
  }

  @Transactional
  public SignUpResDTO signUp(SignUpReqDTO request){
    // 이메일 중복 체크
    if (userRepository.existsByEmail(request.getEmail())){
      throw new UserException(UserErrorCode.EMAIL_ALREADY_EXISTS);
    }

    // 비밀번호 인코딩
    String encodePassword = passwordEncoder.encode(request.getPassword());

    // User 생성 및 저장
    User user = UserConverter.toUser(request, encodePassword);
    User saved = userRepository.save(user);

    // 응답 DTO 변환
    return UserConverter.toSignUpResponse(saved);
  }

  public LoginResDTO login(LoginReqDTO request) {
    // 1. 이메일로 유저 조회
    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

    // 2. 비밀번호 비교
    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new UserException(UserErrorCode.INVALID_PASSWORD);
    }

    // 3. AuthMember 생성
    AuthMember authMember = AuthMember.builder()
        .id(user.getId())
        .email(user.getEmail())
        .password(user.getPassword())
        .role("ROLE_USER")
        .build();

    // 4. JWT 발급
    String accessToken = jwtUtil.createAccessToken(authMember);

    // 5. 응답 DTO
    return LoginResDTO.builder()
        .accessToken(accessToken)
        .tokenType("Bearer")
        .build();
  }
}
