/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.enums;

/**
 * WeekEnum
 *
 * @author liuzhen
 * @version 1.0.0 2023/7/6 16:35
 */
public enum WeekEnum {
    MONDAY(1, "星期一"),
    TUESDAY(2, "星期二"),
    WEDNESDAY(3, "星期三"),
    THURSDAY(4, "星期四"),
    FRIDAY(5, "星期五"),
    SATURDAY(6, "星期六"),
    SUNDAY(7, "星期天");

    /** 第几天 */
    private int weekNum;
    /** 描述 */
    private String intro;

    WeekEnum(int weekNum, String intro) {
        this.weekNum = weekNum;
        this.intro = intro;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "WeekEnum{" +
                "weekNum=" + weekNum +
                ", intro='" + intro + '\'' +
                '}';
    }
}
