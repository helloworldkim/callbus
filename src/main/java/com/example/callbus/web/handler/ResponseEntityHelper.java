package com.example.callbus.web.handler;

import com.example.callbus.consts.responsecode.ResponseCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class ResponseEntityHelper {

    public static ResponseEntity createResponseEntity(ResponseCode code, Object body) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(APPLICATION_JSON);

        return new ResponseEntity<>(body, httpHeaders, code.getStatus());
    }
}
