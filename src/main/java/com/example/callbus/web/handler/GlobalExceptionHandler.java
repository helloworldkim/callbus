package com.example.callbus.web.handler;

import com.example.callbus.consts.responsecode.ErrorCode;
import com.example.callbus.web.response.ApiResponseDTO;
import com.example.callbus.web.response.CommonResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.callbus.web.handler.ResponseEntityHelper.createResponseEntity;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 전체 에러처리
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> apiException(RuntimeException e) {

        ErrorCode errorCode = ErrorCode.BAD_REQUEST;
        return createResponseEntity(errorCode, new ApiResponseDTO(errorCode, errorCode.getMessage()));
    }


    /**
     * RestController Valid 안맞는 대상 BindingResult 에러처리
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler({BindException.class})
    public ResponseEntity<ApiResponseDTO> BindingResultException(HttpServletRequest request, BindException e) {

        ErrorCode errorCode = ErrorCode.VALIDATION_INPUT;

        /** bindingResult 값 획득 */
        BindingResult bindingResult = e.getBindingResult();
        List<Map<String, String>> responseList = new ArrayList<>();

        HashMap<String, String> errorMap = new HashMap<>();

        /* Validation 에러확인 */
        if (bindingResult.hasErrors()) {
            for (FieldError fieldFieldError : bindingResult.getFieldErrors()) {
                errorMap.put(fieldFieldError.getField(),fieldFieldError.getDefaultMessage());
            }
        }
        responseList.add(errorMap);

        return createResponseEntity(errorCode, new ApiResponseDTO(errorCode, responseList));

    }

}
