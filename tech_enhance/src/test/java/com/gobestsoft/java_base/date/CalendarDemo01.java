/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

/**
 * CalendarDemo01
 * 日历类
 * @author liuzhen
 * @version 1.0.0 2021/3/19 21:56
 */
public class CalendarDemo01 {

    /**
     * 取得从 1970 年 1 月 1 日 0 时 0 分 0 秒到现在的毫秒数
     * @author liuzhen
     * @date 2022/3/2 9:06
     * @param
     * @return void
     */
    @Test
    public void getMills() {
        // 第一种方式
        long timeInMillis1 = Calendar.getInstance().getTimeInMillis();
        // 第二种方式
        long timeInMillis2 = System.currentTimeMillis();
        // Java 8
        long timeInMillis3 = Clock.systemDefaultZone().millis();

        System.out.println(timeInMillis1);
        System.out.println(timeInMillis2);
        System.out.println(timeInMillis3);
    }

    /**
     * 获取本月的最后一天
     * @author liuzhen
     * @date 2022/3/2 8:11
     * @param
     * @return void
     */
    @Test
    public void getMonthLastDay() {
        LocalDate today = LocalDate.now();
        // 本月的第一天
        LocalDate firstDay = LocalDate.of(today.getYear(), today.getMonth(), 1);
        // 本月的最后一天
        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());

        System.out.println("本月的第一天" + firstDay);
        System.out.println("本月的最后一天" + lastDay);
    }

    /**
     * 获取当前月的最后一天
     * @author liuzhen
     * @date 2022/3/2 8:14
     * @param
     * @return void
     */
    @Test
    public void getMonthLastDay2() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'");

        // 获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        // 设置为 1 号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        String first = format.format(c.getTime());
        System.out.println("first:" + first);

        // 获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        System.out.println("last:" + last);

    }

    public static void main(String[] args) {
        // 创建Calendar对象
        Calendar cal = Calendar.getInstance();
        // 设置年
        cal.set(Calendar.YEAR, 2022);
        cal.add(Calendar.YEAR, 1);
        int year = cal.get(Calendar.YEAR);
        // 设置月
        int month = cal.get(Calendar.MONTH) + 1;
        // 设置日
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        System.out.println(year + "年" + month + "月" + dayOfMonth + "日");

        System.out.println(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

}
