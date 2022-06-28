/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.thread;

/**
 * MustDeadLock 死锁测试
 * 
 * @author liuzhen
 * @version 1.0.0 2022/6/27 22:08
 */
public class MustDeadLock implements Runnable {
    public int flag;
    static Object object1 = new Object();
    static Object object2 = new Object();

    public void run() {
        System.out.println("线程" + Thread.currentThread().getName() + "的flag为" + flag);
        if (flag == 1) {
            synchronized (object1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.out.println("线程1获得了两把锁");
                }
            }
        }
        if (flag == 2) {
            synchronized (object2) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.out.println("线程2获得了两把锁");
                }
            }
        }
    }

    public static void main(String[] argv) {
        MustDeadLock r1 = new MustDeadLock();
        MustDeadLock r2 = new MustDeadLock();
        r1.flag = 1;
        r2.flag = 2;
        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r2, "t2");
        t1.start();
        t2.start();
    }
}
