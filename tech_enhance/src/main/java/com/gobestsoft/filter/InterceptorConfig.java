/*
package com.gobestsoft.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private static final String[] excludePath = {"/login", "/checkUser", "/js/**", "/img/**"};

//   @Bean
//   LoginInterceptor loginInterceptor() {
//      return new LoginInterceptor();
//    }

    @Resource
    LoginInterceptor loginInterceptor;


    */
/*
    * 添加拦截路径
    * *//*

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(excludePath)
                .addPathPatterns("/**");
    }



}
*/
