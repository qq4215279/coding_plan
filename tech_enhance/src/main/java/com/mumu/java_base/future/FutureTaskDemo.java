/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTaskDemo
 *
 * @author liuzhen
 * @version 1.0.0 2024/11/17 15:58
 */
public class FutureTaskDemo {
    /**
     * FutureTask 是 Future 的具体实现。FutureTask 实现了 RunnableFuture 接口。 RunnableFuture 接口又同时继承了 Runnable 和 Future
     * 接口。所以 FutureTask 既可以作为 Runnable 被线程执行，又可以作为 Future 得到 Callable 的返回值。
     * 
     * @date 2024/11/17 15:58
     */

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long starttime = System.currentTimeMillis();

        // input2生成， 需要耗费3秒
        FutureTask<Integer> input2_futuretask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                return 5;
            }
        });

        new Thread(input2_futuretask).start();

        // input1生成，需要耗费2秒
        FutureTask<Integer> input1_futuretask = new FutureTask<>(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);
                return 3;
            }
        });
        new Thread(input1_futuretask).start();

        System.out.println("start input2_futuretask");
        Integer integer2 = input2_futuretask.get();
        System.out.println("start input1_futuretask");
        Integer integer1 = input1_futuretask.get();
        System.out.println(algorithm(integer1, integer2));
        long endtime = System.currentTimeMillis();
        System.out.println("用时：" + String.valueOf(endtime - starttime));
    }

    // 这是我们要执行的算法
    public static int algorithm(int input, int input2) {
        return input + input2;
    }
}
