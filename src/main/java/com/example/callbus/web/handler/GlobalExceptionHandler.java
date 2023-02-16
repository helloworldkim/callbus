package com.example.callbus.web.handler;

import com.example.callbus.web.response.CommonResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 전체 에러처리
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> apiException(RuntimeException e) {

        return new ResponseEntity<>(CommonResponseDto.builder().code(HttpStatus.BAD_REQUEST.value()).msg(e.getMessage()).build(), HttpStatus.BAD_REQUEST);

    }


    /**
     * RestController Valid 안맞는 대상 BindingResult 에러처리
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> BindingResultException(MethodArgumentNotValidException e, HttpServletRequest request) {

        /** bindingResult 값 획득 */
        BindingResult bindingResult = e.getBindingResult();
        ResponseEntity<?> bindingResultErrorResponse = getBindingResultErrorResponse(e, bindingResult);

        return bindingResultErrorResponse;

    }

    private static ResponseEntity<CommonResponseDto<Object>> getBindingResultErrorResponse(MethodArgumentNotValidException e, BindingResult bindingResult) {
        HashMap<String, String> errorMap = new HashMap<>();

        /* Validation 에러확인 */
        if (bindingResult.hasErrors()) {
            for (FieldError fieldFieldError : bindingResult.getFieldErrors()) {
                errorMap.put(fieldFieldError.getField(),fieldFieldError.getDefaultMessage());
            }
        }

        return new ResponseEntity<>(CommonResponseDto.builder().code(HttpStatus.BAD_REQUEST.value()).msg(errorMap.toString()).build(), HttpStatus.BAD_REQUEST);
    }
}
