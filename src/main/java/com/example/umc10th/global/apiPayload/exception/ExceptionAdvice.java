package com.example.umc10th.global.apiPayload.exception;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.status.ErrorStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(annotations = {RestController.class})
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {
        return handleExceptionInternalFalse(e, ErrorStatus._INTERNAL_SERVER_ERROR, HttpHeaders.EMPTY, ErrorStatus._INTERNAL_SERVER_ERROR.getHttpStatus(), request, e.getMessage());
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Object> onThrowException(GeneralException generalException, WebRequest request) {
        BaseErrorCode code = generalException.getCode();
        return handleExceptionInternal(generalException, code, HttpHeaders.EMPTY, request);
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception e, BaseErrorCode errorCode, HttpHeaders headers, WebRequest request) {
        ApiResponse<Object> body = ApiResponse.onFailure(errorCode.getCode(), errorCode.getMessage(), null);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                errorCode.getHttpStatus(),
                request
        );
    }

    private ResponseEntity<Object> handleExceptionInternalFalse(Exception e, ErrorStatus errorCommonStatus, HttpHeaders headers, HttpStatus status, WebRequest request, String errorPoint) {
        ApiResponse<Object> body = ApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(), errorPoint);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                status,
                request
        );
    }
}
