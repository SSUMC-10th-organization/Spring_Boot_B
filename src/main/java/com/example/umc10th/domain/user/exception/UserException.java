package com.example.umc10th.domain.user.exception;

import com.example.umc10th.global.apiPayload.code.BaseCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class UserException extends ProjectException {
  public UserException(BaseCode errorCode){
    super(errorCode);
  }
}
