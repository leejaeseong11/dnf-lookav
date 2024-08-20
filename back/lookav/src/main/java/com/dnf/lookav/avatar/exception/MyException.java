package com.dnf.lookav.avatar.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MyException extends RuntimeException {
    private final ErrorCode errorCode;
}
