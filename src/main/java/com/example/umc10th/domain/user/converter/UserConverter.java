package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.req.SignUpReqDTO;
import com.example.umc10th.domain.user.dto.res.MyPageResponseDTO;
import com.example.umc10th.domain.user.dto.res.SignUpResDTO;
import com.example.umc10th.domain.user.entity.User;

public class UserConverter {

  public static MyPageResponseDTO toMyPageResponse(User user) {
    return MyPageResponseDTO.builder()
        .name(user.getName())
        .point(user.getCurrentPoint())
        .build();
  }

  public static User toUser(SignUpReqDTO request, String encodedPassword) {
    return User.signUp(request.getEmail(), encodedPassword);
  }

  public static SignUpResDTO toSignUpResponse(User user) {
    return SignUpResDTO.builder()
        .userId(user.getId())
        .email(user.getEmail())
        .build();
  }
}
