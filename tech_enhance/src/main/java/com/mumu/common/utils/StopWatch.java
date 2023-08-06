/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.common.utils;

/**
 * StopWatch
 * 一个简单的计时器
 * @author liuzhen
 * @version 1.0.0 2023/7/7 11:29
 */
public class StopWatch {
    private long start;
    private long end;

    public StopWatch() {
    };

    /**
     * 获取秒表
     * @return
     * @version 1.0.0.0 2011-7-18 下午04:04:28
     */
    public static StopWatch begin() {
        StopWatch sw = new StopWatch();
        sw.start();
        return sw;
    }

    /**
     * 统计允许一个特定项目的时间
     * @param runnable
     * @return
     * @version 1.0.0.0 2011-7-18 下午04:24:20
     */
    public static StopWatch run(Runnable runnable) {
        StopWatch sw = begin();
        runnable.run();
        sw.stop();
        return sw;
    }

    /**
     * 开始计时
     * @version 1.0.0.0 2011-7-18 下午04:02:49
     */
    public void start() {
        this.start = System.currentTimeMillis();
    }

    /**
     * 结束计时
     *
     * @version 1.0.0.0 2011-7-18 下午04:03:25
     */
    public void stop() {
        this.end = System.currentTimeMillis();
    }

    /**
     * 获取消耗时间
     * @return
     * @version 1.0.0.0 2011-7-18 下午04:04:07
     */
    public long getElapsedTime() {
        return end - start;
    }
}
