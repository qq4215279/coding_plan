/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.jdk_api.util;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * TimeUnitTest
 * TimeUnit
 * @author liuzhen
 * @version 1.0.0 2023/6/27 13:54
 */
public class TimeUnitTest {

    /**
     * 纳秒 TimeUnit.NANOSECONDS
     * 微秒 TimeUnit.MICROSECONDS
     * 毫秒 TimeUnit.MILLISECONDS
     * 秒 TimeUnit.SECONDS
     * 分 TimeUnit.MINUTES
     * 时 TimeUnit.HOURS
     * 天 TimeUnit.DAYS
     *
     * 转化纳秒 toNanos(long duration)
     * 转化微秒 toMicros(long duration)
     * 转化毫秒 toMillis(long duration)
     * 转化秒 toSeconds(long duration)
     * 转化分钟 toMinutes(long duration)
     * 转化小时 toHours(long duration)
     * 转化天 toDays(long duration)
     *
     * 延时 sleep(long timeout)
     */

    /**
     * test 转 毫秒值
     * @date 2023/6/27 14:02
     * @param
     * @return void
     */
    @Test
    public void test1() {
        // 1. 秒 转 毫秒值
        System.out.println("1秒毫秒值：" + TimeUnit.SECONDS.toMillis(1));

        // 2. 分钟 转 毫秒值
        System.out.println("1分钟毫秒值：" + TimeUnit.MINUTES.toMillis(1));

        // 3. 小时 转 毫秒值
        System.out.println("1小时毫秒值：" + TimeUnit.HOURS.toMillis(1));

        // 4. 天 转 毫秒值
        System.out.println("1天毫秒值：" + TimeUnit.DAYS.toMillis(1));
    }

    /**
     * 延时，可替代Thread.sleep()
     * @date 2023/6/27 14:08
     * @param
     * @return void
     */
    public void test2() throws InterruptedException {
        /* Thread.sleep()的弊端：Thread.sleep()是一个静态方法，暂停线程时不会释放锁，并会抛出InterrupttedException异常，且可读性差
            例如：Thread.sleep(25000) 很难一下子得出暂停了多久 */

        // sleep 单位是毫秒
        // Thread.sleep(500);

        // 单位可以自定义,more convinent
        TimeUnit.SECONDS.sleep(1);
    }
}
