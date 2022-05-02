/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * CalendarDemo
 *
 * @author liuzhen
 * @version 1.0.0 2022/4/29 21:56
 */
public class CalendarDemo {

    /** 
     * get()
     * get方法用来获取指定字段的值
     * @date 2022/4/29 23:21 
     * @param  
     * @return void
     */
    @Test
    public void getTest() {
        Calendar calendar = Calendar.getInstance();
        // calendar.set(Calendar.MONTH, 0);
        calendar.add(Calendar.MONTH, 0);

        Date time = calendar.getTime();
        System.out.println(formatDate(time));

        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        System.out.println(weekOfMonth);

        int date = calendar.get(Calendar.DATE);
        System.out.println("date: " + date);

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(dayOfMonth);

        int dayOfWeekInMonth = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        System.out.println(dayOfWeekInMonth);

        int am_pm = calendar.get(Calendar.AM_PM);
        System.out.println(am_pm);

        int hour = calendar.get(Calendar.HOUR);
        System.out.println(hour);

        int hour_of_day = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour_of_day);

        // TODO
        int zone_offset = calendar.get(Calendar.ZONE_OFFSET);
        System.out.println(zone_offset);
    }

    /** 
     * set()
     * set方法用来设置指定字段的值
     * @date 2022/4/29 23:21
     * @param  
     * @return void
     */
    @Test
    public void setTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, 10);
        calendar.set(Calendar.DAY_OF_MONTH, 20);

        System.out.print(formatDate(calendar.getTime())); // 2020年1月17日

    }

    /** 
     * add()
     * add方法可以对指定日历字段的值进行加减操作，如果第二个参数为正数则加上偏移量，如果为负数则减去偏移量。
     * @date 2022/4/29 23:24
     * @param  
     * @return void
     */
    @Test
    public void addTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, 10);
        calendar.set(Calendar.DAY_OF_MONTH, 20);

        // 使用add方法
        calendar.add(Calendar.YEAR, -3); // 减3年
        calendar.add(Calendar.DAY_OF_MONTH, 2); // 加2天

        System.out.print(formatDate(calendar.getTime()));

    }

    /**
     * 获取日历实例api（取得本地时间）：
     * Calendar getInstance(): 使用默认时区和语言环境获得一个日历。
     * Calendar getInstance(Locale aLocale): 使用默认时区和指定语言环境获得一个日历。
     * Calendar getInstance(TimeZone zone): 使用指定时区和默认语言环境获得一个日历。
     * Calendar getInstance(TimeZone zone, Locale aLocale): 使用指定时区和语言环境获得一个日历。
     *
     * setTime(Date date): 设置当前日历日期
     *
     * add(int field, int amount): 根据日历的规则，为给定的日历字段添加或减去指定的时间量。
     * after(Object when): 判断此 Calendar 表示的时间是否在指定 Object 表示的时间之后，返回判断结果。
     * before(Object when): 判断此 Calendar 表示的时间是否在指定 Object 表示的时间之前，返回判断结果。
     * getActualMaximum(int field): 给定此 Calendar 的时间值，返回指定日历字段可能拥有的最大值。
     * getActualMinimum(int field): 给定此 Calendar 的时间值，返回指定日历字段可能拥有的最小值。
     * getFirstDayOfWeek(): 获取一星期的第一天；例如，在美国，这一天是 SUNDAY，而在法国，这一天是 MONDAY。
     * getGreatestMinimum(int field): 返回此 Calendar 实例给定日历字段的最高的最小值。
     *
     * getLeastMaximum(int field): 返回此 Calendar 实例给定日历字段的最低的最大值。
     * getMaximum(int field): 返回此 Calendar 实例给定日历字段的最大值。
     * getMinimalDaysInFirstWeek(): 获取一年中第一个星期所需的最少天数，例如，如果定义第一个星期包含一年第一个月的第一天，则此方法将返回 1。
     *
     * setFirstDayOfWeek(int value): 设置一星期的第一天是哪一天；例如，在美国，这一天是 SUNDAY，而在法国，这一天是 MONDAY。
     * setMinimalDaysInFirstWeek(int value): 设置一年中第一个星期所需的最少天数，例如，如果定义第一个星期包含一年第一个月的第一天，则使用值 1 调用此方法。
     * setTime(Date date): 使用给定的 Date 设置此 Calendar 的时间。
     * setTimeInMillis(long millis): 用给定的 long 值设置此 Calendar 的当前时间值。
     *
     * toString(): 返回此日历的字符串表示形式。
     * @date 2022/4/29 23:29
     * @param
     * @return void
     */
    @Test
    public void apiTest() {
        Calendar calendar = Calendar.getInstance();
        Calendar beforeCalendar = Calendar.getInstance();
        beforeCalendar.set(Calendar.YEAR, 2020);

        System.out.println(beforeCalendar.before(calendar));
    }

    private static String formatDate(Date now) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss:SSS'Z'");
        return format.format(now); // 2022年01月29日 22:18:28:079Z
    }

}
