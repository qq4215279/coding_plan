package com.mumu;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Tomcat 启动配置
 * create by gutongwei
 * on 2018/8/24 上午9:50
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(TestHolidayApplication.class);
        return builder.sources(TestDayApplication.class);
    }

}