/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.thread;

/**
 * DeadLockDemo
 * 死锁demo
 * @author liuzhen
 * @version 1.0.0 2023/11/13 14:21
 */
public class DeadLockDemo  {

    public static void main(String[] args) {
        Resource resource = new Resource();

        Thread t1 = new Thread(() -> resource.update( 1), "线程1");
        t1.start();
        Thread t2 = new Thread(() -> resource.update(2), "线程2");
        t2.start();
    }

}

/**
 * 资源
 */
class Resource {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    /**
     * 同步资源
     * @param index index
     * @return void
     * @date 2023/11/13 15:13
     */
    public void update(int index) {
        if (index == 1) {
            synchronized (lock1) {
                try {
                    System.out.println(Thread.currentThread().getName() + "获得了lock1，等待获取lock2...");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "线程获得了两把锁");
                }
            }
        } else {
            synchronized (lock2) {
                try {
                    System.out.println(Thread.currentThread().getName() + "获得了lock2，等待获取lock1...");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "线程获得了两把锁");
                }
            }
        }

    }
}
