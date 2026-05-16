package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.res.MyPageResponseDTO;
import com.example.umc10th.domain.user.entity.User;

public class UserConverter {

  public static MyPageResponseDTO toMyPageResponse(User user) {
    return MyPageResponseDTO.builder()
        .name(user.getName())
        .point(user.getCurrentPoint())
        .build();
  }
}
