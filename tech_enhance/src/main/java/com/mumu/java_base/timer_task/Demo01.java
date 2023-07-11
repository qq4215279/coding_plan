/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.timer_task;

import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demo01
 *
 * @author liuzhen
 * @version 1.0.0 2021/3/19 19:48
 */
public class Demo01 {


    private static class TestTask extends TimerTask {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("倒计时：" + i);
            }
        }
    }

    public static void main(String[] args) {

        Timer timer = new Timer();

        TestTask task = new TestTask();

        timer.schedule(task, new Date(System.currentTimeMillis() + 5000));

    }

    /**
     * 与Timer比较
     * 1、Timer管理延时任务的缺陷
     * a、以前在项目中也经常使用定时器，比如每隔一段时间清理项目中的一些垃圾文件，每个一段时间进行数据清洗；然而Timer是存在一些缺陷的，因为Timer在执行定时任务时只会创建一个线程，所以如果存在多个任务，且任务时间过长，超过了两个任务的间隔时间，会发生一些缺陷
     *
     * 2、Timer当任务抛出异常时的缺陷
     * 如果TimerTask抛出RuntimeException，Timer会停止所有任务的运行：
     *
     * 3、Timer执行周期任务时依赖系统时间
     * Timer执行周期任务时依赖系统时间，如果当前系统时间发生变化会出现一些执行上的变化，ScheduledExecutorService基于时间的延迟，不会由于系统时间的改变发生执行变化。
     */
    @Test
    public void scheduledExecutorServiceTest() {
        // 创建 ScheduledExecutorService
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // 延迟 2 秒后执行任务
        executor.schedule(() -> {
            System.out.println("Task executed after 2 seconds");
        }, 2, TimeUnit.SECONDS);

        // 延迟 1 秒后开始执行任务，每隔 3 秒重复执行一次
        ScheduledFuture<?> scheduledFuture = executor.scheduleAtFixedRate(() -> {
            System.out.println("Task executed every 3 seconds");
        }, 1, 3, TimeUnit.SECONDS);

        // 关闭 ScheduledExecutorService
        executor.shutdown();
    }


}
