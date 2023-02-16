package com.example.callbus.consts.responsecode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SuccessCode implements ResponseCode {

    SUCCESS(HttpStatus.OK, true, "S0000", "성공");

    private final HttpStatus status;
    private final boolean    success;
    private final String     code;
    private final String     message;

    SuccessCode(HttpStatus status, boolean success, String code, String message) {
        this.status = status;
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
