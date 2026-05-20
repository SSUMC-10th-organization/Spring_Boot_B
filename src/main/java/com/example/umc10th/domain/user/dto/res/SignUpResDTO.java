package com.example.umc10th.domain.user.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignUpResDTO {
  private Long userId;
  private String email;
}
