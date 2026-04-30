package com.example.umc10th.global.apiPayload;

import com.example.umc10th.global.apiPayload.code.BaseCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder
public class ApiResponse<T> {

  @JsonProperty("isSuccess")
  private final Boolean isSuccess;

  @JsonProperty("code")
  private final String code;

  @JsonProperty("message")
  private final String message;

  @JsonProperty("result")
  private T result;

  public static <T> ApiResponse<T> onFailure(BaseCode code, T result){
    return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
  }

  public static <T> ApiResponse<T> onSuccess(BaseCode code, T result){
    return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
  }
}
