/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.thread;

import lombok.SneakyThrows;

/**
 * YieldDemo
 * yield表示让步、放弃的意思。
 * Thread.yield() 方法，使当前线程由执行状态，变成为就绪状态，让出CPU，在下一个线程执行时候，此线程有可能被执行，也有可能没有被执行
 * @author liuzhen
 * @version 1.0.0 2023/10/16 14:10
 */
public class YieldDemo {

    /**
     *  面试题：yield() 是否释放 synchronized 锁？
     * 多个线程竞争 synchronized 同步块 执行权时，获得执行权线程在同步块内部执行 yield()，是否会释放锁？
     * 答：不会释放锁
     * @date 2023/10/16 14:58
     * @param args
     * @return void
     */
    @SneakyThrows
    public static void main(String[] args) {
        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println(String.format("当前线程 :: %s, 获取锁并准备执行 Thread.yield() ", Thread.currentThread().getName()));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // ignore
                }
                Thread.yield();
                System.out.println(String.format("当前线程 :: %s, 执行 Thread.yield() 后", Thread.currentThread().getName()));
            }
        }, "麻花").start();
        Thread.sleep(10);

        new Thread(() -> {
            System.out.println(String.format("当前线程 :: %s, 开始竞争锁 ", Thread.currentThread().getName()));
            synchronized (lock) {
                System.out.println(String.format("当前线程 :: %s, 获取锁 ", Thread.currentThread().getName()));
            }
        }, "百万").start();
    }
}
