package com.mumu.java_base.date.tools;

import org.junit.Test;

import java.time.LocalDate;

/**
 * LocalDateDemo
 * LocalDate使用
 * @author liuzhen
 * @version 1.0.0 2022/6/22 17:33
 */
public class LocalDateDemo {

    @Test
    public void test01() {
        // 获取今年的天数
        int daysOfThisYear = LocalDate.now().lengthOfYear();
        System.out.println(daysOfThisYear);

        // 获取指定某年的天数
        int daysOfAppointYear = LocalDate.of(2000, 1, 10).lengthOfYear();
        System.out.println(daysOfAppointYear);
    }

}
