/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * ThreadLocalDemo
 * ThreadLocal 使用
 * @author liuzhen
 * @version 1.0.0 2022/9/11 0:08
 */
public class ThreadLocalDemo {

    /**
     * 从结果我们可以看到，每一个线程都有各自的local值，我们设置了一个休眠时间，就是为了另外一个线程也能够及时的读取当前的local值。
     *
     * 我们使用数据库的时候首先就是建立数据库连接，然后用完了之后关闭就好了，这样做有一个很严重的问题，如果有1个客户端频繁的使用数据库，
     * 那么就需要建立多次链接和关闭，我们的服务器可能会吃不消，怎么办呢？如果有一万个客户端，那么服务器压力更大。
     * 这时候最好ThreadLocal，因为ThreadLocal在每个线程中对连接会创建一个副本，且在线程内部任何地方都可以使用，线程之间互不影响，
     * 这样一来就不存在线程安全问题，也不会严重影响程序执行性能。是不是很好用。
     * @date 2022/9/11 0:13
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        // 新建一个ThreadLocal
        ThreadLocal<String> local = new ThreadLocal<>();
        // 新建一个随机数类
        Random random = new Random();
        // 使用Stream新建5个线程
        IntStream.range(0, 5).forEach(a -> new Thread(() -> {
            // 为每一个线程设置相应的local值
            local.set(a + "  " + random.nextInt(10));
            System.out.println("线程和local值分别是：" + local.get());

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());
    }

}
