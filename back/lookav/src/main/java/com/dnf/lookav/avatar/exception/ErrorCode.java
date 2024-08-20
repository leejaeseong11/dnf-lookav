package com.dnf.lookav.avatar.exception;

import static org.springframework.http.HttpStatus.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    /* 401 UNAUTHORIZED : 사용자 인증 실패 */
    /* 400 FORBIDDEN : 인가 실패. 특정 리소스에 대한 권한 부족 */
    /* 400 NOT_FOUND : 리소스를 찾을 수 없음 */
    /* 409 CONFLICT : 리소스의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    /* 500 INTERNAL_SERVER_ERROR : 내부 서버 오류 발생 */
    AWS_SERVER_ERROR(INTERNAL_SERVER_ERROR, "AWS 서버 동작 중 문제가 발생했습니다."),
    MALFORMED_URL(INTERNAL_SERVER_ERROR, "잘못된 URL 형식입니다.");
    private final HttpStatus httpStatus;
    private final String message;
}
