/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.date;

import org.apache.http.client.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * CalendarDemo00
 *
 * @author liuzhen
 * @version 1.0.0 2021/3/19 21:56
 */
public class CalendarDemo00 {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(DateUtils.formatDate(new Date(calendar.getTimeInMillis())));
    }

    /**
     * 计算某月份的最大天数
     * @date 2022/4/30 18:34
     * @param year
     * @param month
     * @return void
     */
    public void caclMonthMaxDay(int year, int month) {
        // 计算某一月份的最大天数
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year);
        // 注意,Calendar对象默认一月为0
        time.set(Calendar.MONTH, month - 1);
        // 本月份的天数
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 注：在使用set方法之前，必须先clear一下，否则很多信息会继承自系统当前时间
    }

    /** 
     * 计算相隔天数的方法
     * @date 2022/4/30 18:35 
     * @param d1
     * @param d2 
     * @return int
     */
    public int getDaysBetween(Calendar d1, Calendar d2) {
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar)d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    /** 
     * Calendar转化为Date
     * @date 2022/4/30 18:35 
     * @param  
     * @return void
     */
    public void toDate() {
        // Calendar转化为Date
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
    }
}
