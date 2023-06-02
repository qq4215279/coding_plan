package com.mumu.java_base.date.tools;

import org.junit.Test;

import java.time.LocalDateTime;

/**
 * LocalDateTimeDemo
 * LocalDateTime使用
 * JDK8开始，用LocalDateTime 替代 Calendar
 * @author liuzhen
 * @version 1.0.0 2022/6/22 17:30
 */
public class LocalDateTimeDemo {

    @Test
    public void test01() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }

}
