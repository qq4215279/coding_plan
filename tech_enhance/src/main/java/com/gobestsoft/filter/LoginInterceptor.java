/*
package com.gobestsoft.filter;

import com.gobestsoft.utils.CookiesUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       boolean flag = CookiesUtils.isContainToken(CookiesUtils.Token_Name, request);
       if (!flag){
           response.sendRedirect( request.getContextPath() + "/login" );
       }
       return flag;
    }
}
*/
