/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.date;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * TimeUtilDemo
 *
 * @author liuzhen
 * @version 1.0.0 2022/4/30 7:36
 */
public class TimeUtilDemo {

    @Test
    public void test() {
        int cardLastDays = 7;
        long days = TimeUnit.DAYS.toDays(cardLastDays);
        long seconds = TimeUnit.DAYS.toSeconds(cardLastDays);
        long millis = TimeUnit.DAYS.toMillis(cardLastDays);
        System.out.println(days); // 7
        System.out.println(seconds); // 604800
        System.out.println(millis); // 604800000
    }

}
