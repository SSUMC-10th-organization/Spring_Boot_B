package org.example.erd.domain.store.exception;

import lombok.Getter;
import org.example.erd.global.apiPayload.code.BaseErrorCode;
@Getter
public class StoreException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public StoreException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode=errorCode;
    }
}
