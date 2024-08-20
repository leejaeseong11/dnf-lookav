package com.dnf.lookav.avatar.exception;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = MyException.class)
    protected ResponseEntity<ErrorResponse> myException(MyException ex) {
        log.error("MyException :: ", ex);

        ErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponse.toErrorResponse(errorCode));
    }
}
