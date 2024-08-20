package com.dnf.lookav.avatar.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    private final HttpStatus status;
    private final String message;

    public static ErrorResponse toErrorResponse(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getHttpStatus(), errorCode.getMessage());
    }
}
