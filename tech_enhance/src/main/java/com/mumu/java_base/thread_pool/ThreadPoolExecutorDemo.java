/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.thread_pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutorDemo
 * 线程池demo
 * @author liuzhen
 * @version 1.0.0 2023/11/13 10:37
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3),
                // new ThreadPoolExecutor.AbortPolicy()
                // new ThreadPoolExecutor.CallerRunsPolicy()
                // new ThreadPoolExecutor.DiscardOldestPolicy()
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 20; i++) {
            int finalI = i;

            executor.execute(() -> {
                System.out.println(Thread.currentThread().getId() + "[" + finalI + "] -- 开始");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getId() + "[" + finalI + "] -- 结束");
            });

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
