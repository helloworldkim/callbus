package com.example.callbus.web.exception;

import com.example.callbus.consts.responsecode.ResponseCode;
import lombok.Getter;

/**
 * 어플리케이션 비즈니스 로직 exception
 */
@Getter
public class ApiResponseException extends Exception {

    private ResponseCode code;
    private String           exceptionMessage;
    private String           redirectUrl;



    public ApiResponseException() {
        super();
    }



    public ApiResponseException(ResponseCode code) {
        super(code.getCode() + " - " + code.getMessage());
        this.code = code;
    }



    public ApiResponseException(ResponseCode code, String exceptionMessage) {
        super(exceptionMessage);
        this.code = code;
        this.exceptionMessage = exceptionMessage;
    }



    public ApiResponseException(ResponseCode code, String exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
        this.code = code;
        this.exceptionMessage = exceptionMessage;
    }


    public ApiResponseException(ResponseCode code, String exceptionMessage, String redirectUrl, Throwable cause) {
        super(exceptionMessage, cause);
        this.code = code;
        this.exceptionMessage = exceptionMessage;
        this.redirectUrl = redirectUrl;
    }



    public ApiResponseException(Throwable cause) {
        super(cause);
    }


}
