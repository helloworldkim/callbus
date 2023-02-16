package com.example.callbus.web.response;

import com.example.callbus.consts.responsecode.ResponseCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

/**
 * Rest Api 응답 기본 DTO
 */
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseDTO {

    private boolean       success;
    private String        code;
    private String        message;

    /**
     * 응답 일시
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime responseDt;
    /**
     * data
     */
    private Object        data;


    /**
     * 기본 생성자
     *
     * @param code
     * @param message
     */
    public ApiResponseDTO(ResponseCode code, String message) {
        this(code, message, null);
    }


    /**
     * 기본 생성자
     *
     * @param code
     * @param responseData
     */
    public ApiResponseDTO(ResponseCode code, Object responseData) {
        this(code, null, responseData);
    }


    /**
     * 기본 생성자
     *
     * @param code
     * @param message
     * @param responseData
     */
    public ApiResponseDTO(ResponseCode code, String message, Object responseData) {
        success = code.isSuccess();
        this.code = code.getCode();
        this.message = message == null ? code.getMessage() : message;
        responseDt = LocalDateTime.now();
        data = responseData;
    }
}
