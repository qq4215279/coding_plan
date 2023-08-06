/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.enums.abs;

/**
 * AbsWeekEnum
 * 枚举类中还可以有抽象方法
 * @author liuzhen
 * @version 1.0.0 2023/7/6 16:43
 */
public enum AbsWeekEnum {

    MONDAY() {
        @Override
        public void getWeekIntro() {
            System.out.println("星期一");
        }
    },

    TUESDAY() {
        @Override
        public void getWeekIntro() {
            System.out.println("礼拜二");
        }
    },

    WEDNESDAY() {
        @Override
        public void getWeekIntro() {
            System.out.println("周三");
        }
    },

    SUNDAY() {
        @Override
        public void getWeekIntro() {
            System.out.println(7);
        }
    };

    public abstract void getWeekIntro();
}
