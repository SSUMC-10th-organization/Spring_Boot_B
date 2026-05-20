package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.req.SignUpReqDTO;
import com.example.umc10th.domain.user.dto.res.MyPageResponseDTO;
import com.example.umc10th.domain.user.dto.res.SignUpResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
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
}
