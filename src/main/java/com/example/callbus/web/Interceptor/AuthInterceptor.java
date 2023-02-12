package com.example.callbus.web.Interceptor;

import com.example.callbus.consts.GlobalConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
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

        try {
            String requestURI					= request.getRequestURI();
            String authentication				= request.getHeader(GlobalConst.REQUEST_HEADER); /** 구분 값 */

            log.info("requestURI: {}",			requestURI);
            log.info("authentication: {}",		authentication);


            if (StringUtils.isBlank(authentication)) {
                throw new RuntimeException("Authentication is required");
            }

            String accountId = "";
            if (authentication.startsWith(GlobalConst.REALTOR)) {
                accountId = authentication.replace(GlobalConst.REALTOR, "");
            } else if (authentication.startsWith(GlobalConst.LESSOR)) {
                accountId = authentication.replace(GlobalConst.LESSOR, "");
            } else {
                accountId = authentication.replace(GlobalConst.LESSEE, "");
            }

            request.setAttribute("accountId", accountId);



            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("msg", "권한이 없습니다.");
            throw new RuntimeException(errorMap.toString());
        }


    }


}
