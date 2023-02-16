package com.example.callbus.consts.responsecode;

import org.springframework.http.HttpStatus;

public interface ResponseCode {

    boolean isSuccess();
    String getCode();
    String getMessage();
    HttpStatus getStatus();
}
