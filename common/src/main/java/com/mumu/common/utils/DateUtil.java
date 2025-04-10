/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtil
 * 日期工具类
 * @author liuzhen
 * @version 1.0.0 2023/7/7 11:10
 */
public class DateUtil {
    public static Logger log = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 获取YYYY格式
     * @param
     * @return java.lang.String
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 获取YYYY格式
     * @param date
     * @return java.lang.String
     */
    public static String getYear(Date date) {
        return formatDate(date, "yyyy");
    }

    /**
     * 获取YYYY-MM-DD格式
     * @param
     * @return java.lang.String
     */
    public static String getDay() {
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    /**
     * 获取YYYY-MM-DD格式
     * @date 2023/7/7 11:12
     * @param date
     * @return java.lang.String
     */
    public static String getDay(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 获取YYYYMMDD格式
     * @date 2023/7/7 11:12
     * @param
     * @return java.lang.String
     */
    public static String getDays() {
        return formatDate(new Date(), "yyyyMMdd");
    }

    /**
     *获取YYYYMMDD格式
     * @date 2023/7/7 11:12
     * @param date
     * @return java.lang.String
     */
    public static String getDays(Date date) {
        return formatDate(date, "yyyyMMdd");
    }

    public static String todayStartTime() {
        return formatDate(new Date(), "yyyyMMdd") + "000000";
    }

    public static String todayEndTime() {
        return formatDate(new Date(), "yyyyMMdd") + "235959";
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss.SSS格式
     *
     * @return
     */
    public static String getMsTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 获取YYYYMMDDHHmmss格式
     *
     * @return
     */
    public static String getAllTime() {
        return formatDate(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDate(Date date, String pattern) {
        String formatDate = null;
        if (StringUtils.isNotBlank(pattern)) {
            formatDate = DateFormatUtils.format(date, pattern);
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * @param s
     * @param e
     * @return boolean
     * @throws @author luguosui
     * @Title: compareDate
     * @Description:(日期比较，如果s>=e 返回true 否则返回false)
     */
    public static boolean compareDate(String s, String e) {

        if (parseDate(s) == null || parseDate(e) == null) {
            return false;
        }
        return parseDate(s).getTime() >= parseDate(e).getTime();
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parseDate(String date) {
        return parse(date, "yyyy-MM-dd");
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parseDateTime(String date) {
        return parse(date, "yyyyMMddHHmmss");
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parseTime(String date) {
        return parse(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化日期
     *
     * @return
     */

    public static Date parse(String date, String pattern) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(date, pattern);
        } catch (ParseException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 把日期转换为Timestamp
     *
     * @param date
     * @return
     */
    public static Timestamp format(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s) {
        return parse(s, "yyyy-MM-dd HH:mm:ss") != null;
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s, String pattern) {
        return parse(s, pattern) != null;
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24))
                    / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }


    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    /**
     * 转成时间在转字符
     *
     * @param dateStr    时间字符串
     * @param oldPattern 传入时间字符串格式
     * @param newPattern 获取回来的时间格式
     * @return
     */
    public static String parseAndFormat(String dateStr, String oldPattern, String newPattern) {
        if (StringUtils.isEmpty(dateStr)) return "";
        return format(parse(dateStr, oldPattern), newPattern);
    }


    /**
     * 获取时间距离
     *
     * @return
     */
    public static String getTimeDistance(String dateTimeStr, String pattern) {
        Date dateTime = parse(dateTimeStr, pattern);
        if (dateTime == null) {
            dateTime = new Date();
        }
        Date nowTime = Calendar.getInstance().getTime();
        //相差秒数
        long second = (nowTime.getTime() - dateTime.getTime()) / 1000;
        if (second < 60) {
            return "刚刚";
        } else if (second > 60 && second <= 60 * 60) {
            return (second / 60) + "分钟前";
        } else if (second > 60 * 60 && second <= 24 * 60 * 60) {
            return (second / (60 * 60)) + "小时之前";
        } else if (second > 24 * 60 * 60 && second <= 30 * 24 * 60 * 60) {
            return (second / (24 * 60 * 60)) + "天之前";
        }
        return format(dateTime, "yyyy-MM-dd");
    }

    /**
     * 格式化Oracle Date
     *
     * @param
     * @return
     */
    // public static String buildDateValue(Object value){
    // if(Func.isOracle()){
    // return "to_date('"+ value +"','yyyy-mm-dd HH24:MI:SS')";
    // }else{
    // return Func.toStr(value);
    // }
    // }
    public static void main(String[] args) {

//        String a = "^\\d{4}/\\d{2}/\\d{2}$";
//
//        String year = "1999/12/15";
//        System.out.println(year.matches(a));
//        String b = "^\\d{4}-\\d{2}-\\d{2}$";
//        String year2 = "1999-12-15";
//        System.out.println(year2.matches(b));


        System.out.println(getDaySpace(new Date(), parse("2025-03-03 00:00:00", "yyyy-MM-dd HH:mm:ss")));
        System.out.println(getDaySpace(new Date(), parse("2025-03-03 18:00:00", "yyyy-MM-dd HH:mm:ss")));
        System.out.println(getDaySpace(new Date(), parse("2025-03-04 00:00:00", "yyyy-MM-dd HH:mm:ss")));
        System.out.println(getDaySpace(new Date(), parse("2025-03-05 00:00:00", "yyyy-MM-dd HH:mm:ss")));
        System.out.println(getDaySpace(new Date(), parse("2025-03-05 17:10:00", "yyyy-MM-dd HH:mm:ss")));

    }


    private final static String[] months = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};

    /**
     * 获取月份的中文名
     *
     * @param calendar
     * @return
     */
    public static String getMonthCnName(Calendar calendar) {
        int month = calendar.getTime().getMonth();
        return months[month];
    }


    /**
     * 获取两个日期相差几天
     * @return int
     * @date 2025/3/4 17:15
     */
    public static int getDaySpace(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }

        Calendar fromCalendar = parseCalendar(start);
        Calendar toCalendar = parseCalendar(end);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

    private static Calendar parseCalendar(Date start) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(start);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);
        return fromCalendar;
    }

    /**
     * 获取两个日期相差几个月
     * @param start start
     * @param end end
     * @return int
     * @date 2025/3/4 17:17
     */
    public static int getMonthSpace(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);
        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }

    /**
     * 获取两个日期相差几个年
     * @param start start
     * @param end end
     * @return int
     * @date 2025/3/4 17:17
     */
    public static int getYearSpace(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }

        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(start);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(end);

        return toCalendar.get(Calendar.YEAR) - fromCalendar.get(Calendar.YEAR);
    }

    /**
     * 获取当月最后一天
     *
     * @param calendar
     * @return
     */
    public static Date getMonthLastDay(Calendar calendar) {
        if (calendar == null) calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();
    }


    /**
     * 获取当月第一天
     *
     * @param calendar
     * @return
     */
    public static Date getMonthFirstDay(Calendar calendar) {
        if (calendar == null) calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();
    }

}
