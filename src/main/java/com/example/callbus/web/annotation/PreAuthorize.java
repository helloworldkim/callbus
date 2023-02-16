package com.example.callbus.web.annotation;

import com.example.callbus.consts.enums.AccountType;
import java.lang.annotation.*;

/**
 * 지점 API 요청주소별 권한 체크를 위한 어노테이션
 * @author: kcgi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PreAuthorize {

    /**
     * API 메서드별 접근권한
     * @return
     */
    AccountType[] hasRole();

}
