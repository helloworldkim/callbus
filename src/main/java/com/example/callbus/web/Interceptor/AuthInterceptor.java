package com.example.callbus.web.Interceptor;

import com.example.callbus.consts.GlobalConst;
import com.example.callbus.enums.AccountType;
import com.example.callbus.web.annotation.PreAuthorize;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            String requestURI					= request.getRequestURI();
            String authentication				= request.getHeader(GlobalConst.REQUEST_HEADER); /** 구분 값 */

            log.info("requestURI: {}",			requestURI);
            log.info("authentication: {}",		authentication);


            if (StringUtils.isBlank(authentication)) {
                preAuthorizeRoleCheck(handler, AccountType.OTHER);
                return true;
            }
            String[] authenticationArray = authentication.split(" "); // 요청권한 + " " + accountId형태
            String role = authenticationArray[0].toUpperCase();
            String accountId = authenticationArray[1];

            validAccountType(handler, AccountType.getAccountType(role));


            request.setAttribute("accountId", accountId);
            return true;




    }

    /**
     * 계정 권한타입 별 권한체크 로직 수행
     * @param handler
     * @param accountType
     */
    private void validAccountType(Object handler, AccountType accountType) {
        switch (accountType) {
            case  REALTOR : preAuthorizeRoleCheck(handler, AccountType.REALTOR); break;
            case  LESSOR: preAuthorizeRoleCheck(handler, AccountType.LESSOR); break;
            case  LESSEE: preAuthorizeRoleCheck(handler, AccountType.LESSEE); break;
            default:
                throw new RuntimeException("부적절한 요청 입니다.");
        }
    }

    /**
     * 요청 컨트롤러의 메서드별 권한체크 수행
     * @param handler
     */
    private void preAuthorizeRoleCheck(Object handler, AccountType requestAccountType) {
        /** 현재 요청한 메서드의 어노테이션 가져오기.*/
        HandlerMethod method = (HandlerMethod) handler;
        PreAuthorize preAuthorizeAnnotation = method.getMethodAnnotation(PreAuthorize.class);

        /** 요청메서드에 PreAuthorize 명명 없는경우 오류값 리턴 (권한명시 필수) */
        if (preAuthorizeAnnotation == null) {
            log.error(" preAuthorizeAnnotation is null ");
            log.error(" target method : {}", method);
            throw new RuntimeException("API에 명시된 권한이 없습니다.");
        }

        /** 컨트롤러 메서드에 명시된 권한리스트 */
        AccountType[] accountTypes = preAuthorizeAnnotation.hasRole();
        boolean result = false;
        for (AccountType type : accountTypes) {
            /** 권한중 하나라도 동일한 경우 true */
            if (type.equals(requestAccountType)) {
                result  = true;
            }
        }

        if (!result) {
            log.debug("AccountType result : {}", result);
            throw new RuntimeException("요청 권한이 없습니다");
        }


    }


}
