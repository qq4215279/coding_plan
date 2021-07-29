/*
package com.gobestsoft.filter;

import com.gobestsoft.utils.CookiesUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value="/*" ,dispatcherTypes ={DispatcherType.REQUEST,DispatcherType.FORWARD})
@Component
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("开始执行拦截器.........");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        System.out.println(uri);
        if (uri.contains( "/login" ) || uri.contains( "/checkUser" ) || uri.contains( "/js/**" ) || uri.contains( "/img/**" )
                || uri.contains( "/css/**" ) || uri.contains( "/font/**" ) ){
            System.out.println("11111111111");
            chain.doFilter( req,resp );
        }else {
            boolean flag = CookiesUtils.isContainToken( CookiesUtils.Token_Name,request );
            if (flag){
                System.out.println("222222222222");
                chain.doFilter( req,resp );
            }else {
                request.setAttribute( "login_msg","您尚未登录，请先登录！" );
                System.out.println("33333333333333");
                request.getRequestDispatcher( "/login" ).forward( request,resp );
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("结束...............");
    }
}
*/
