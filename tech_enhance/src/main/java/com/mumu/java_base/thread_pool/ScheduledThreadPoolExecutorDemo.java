/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.thread_pool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExecutorDemo
 *
 * @author liuzhen
 * @version 1.0.0 2023/11/13 13:59
 */
public class ScheduledThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3, new SubThreadFactory("Test_ScheduledThreadPoolExecutor_Name"));

        for (int i = 0; i < 10; i++) {
            int finalI = i;

            executor.schedule(() -> {
                System.out.println(Thread.currentThread().getId() + "[" + finalI + "] -- 开始");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getId() + "[" + finalI + "] -- 结束");
            }, 1000L, TimeUnit.MILLISECONDS);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        boolean flag;
        try {
            do {
                flag = !executor.awaitTermination(1, TimeUnit.SECONDS);
                System.out.println(flag);
            } while (flag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程池关闭成功。。。");
        System.out.println(Thread.currentThread().getId());
    }

}
