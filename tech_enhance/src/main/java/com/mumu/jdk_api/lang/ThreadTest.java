/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.jdk_api.lang;

import org.junit.Test;

/**
 * ThreadTest
 *
 * @author liuzhen
 * @version 1.0.0 2021/12/10 22:19
 */
public class ThreadTest {

    /**
     * 线程构造方法使用
     */
    @Test
    public void instanceTest() {
        // 1. 
    }

    /**
     * 静态api：Thread.
     * currentThread() 返回对当前正在执行的线程对象的引用。
     * interrupted() 测试当前线程是否已被中断。
     * sleep(long millis) 导致当前正在执行的线程休眠（暂时停止执行）指定的毫秒数，具体取决于系统计时器和调度程序的精度和准确性。
     * sleep(long millis, int nanos) 导致当前正在执行的线程休眠（暂时停止执行）指定的毫秒数加上指定的纳秒数，具体取决于系统定时器和调度程序的精度和准确性。
     * yield() 向调度程序提示当前线程是否愿意产生其当前使用的处理器。
     */
    @Test
    public void staticApiTest() {

    }
}
