/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.java_base.date;

import java.util.Calendar;

/**
 * CalendarDemo01
 * 日历类
 * @author liuzhen
 * @version 1.0.0 2021/3/19 21:56
 */
public class CalendarDemo01 {

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
