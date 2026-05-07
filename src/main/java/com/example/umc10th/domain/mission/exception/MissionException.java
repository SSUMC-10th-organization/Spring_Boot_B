package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.global.apiPayload.code.BaseCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class MissionException extends ProjectException {
  public MissionException(BaseCode errorCode) {
    super(errorCode);
  }
}
