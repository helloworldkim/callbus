package com.example.callbus.consts.responsecode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode implements ResponseCode {
    // ================================================================================================================
    // 기본 오류
    // ================================================================================================================

    BAD_REQUEST(HttpStatus.BAD_REQUEST, false, "E0001", "잘못된 요청입니다."),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, false, "E0002", "미인증 상태입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, false, "E0003", "권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, false, "E0004", "리소스를 찾을 수 없습니다."),
    UNSUPPORTED(HttpStatus.BAD_REQUEST, false, "E0005", "리소스를 찾을 수 없습니다."),
    VALIDATION_INPUT(HttpStatus.BAD_REQUEST, false, "E0006", "데이터 유효성 오류 입니다."),
    // ================================================================================================================
    // 내부 오류
    // ================================================================================================================
    NO_ROLE(HttpStatus.INTERNAL_SERVER_ERROR, false, "E0101", "명시된 권한이 없습니다."),
    // ================================================================================================================
    // 기타 오류
    // ================================================================================================================
    OTHER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, "E9998", "오류가 발생 했습니다."),
    SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, "E9999", "시스템 오류가 발생 했습니다.");

    private final HttpStatus status;
    private final boolean    success;
    private final String     code;
    private final String     message;

    ErrorCode(HttpStatus status, boolean success, String code, String message) {
        this.status = status;
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
