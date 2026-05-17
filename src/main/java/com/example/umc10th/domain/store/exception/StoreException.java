package com.example.umc10th.domain.store.exception;

import com.example.umc10th.global.apiPayload.code.BaseCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {
  public StoreException(BaseCode code){
    super(code);
  }
}
