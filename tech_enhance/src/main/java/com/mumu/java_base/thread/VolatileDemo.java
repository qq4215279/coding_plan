/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.thread;

import java.util.concurrent.TimeUnit;

/**
 * VolatileDemo
 * 可见性的代码验证说明
 * @author liuzhen
 * @version 1.0.0 2022/7/19 21:08
 */
public class VolatileDemo {

    /**
     * 因为有了volatile修饰，具有了可见性，AAA线程中将number的修改之后，会立刻通知main线程，
     * number的值修改为了60，则退出死循环，并打印“main mission is over, main get number value:60”
     * @date 2022/7/19 21:12
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        // main是一切方法的运行入口
        MyData myData = new MyData(); // 资源类

        // 第一个线程
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            // 暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t update number value:" + myData.number);
        }, "AAA").start();

        // 第二个线程就是我们的main线程
        while (myData.number == 0) {
            // main线程就一直在这里等待，直到number值不再等于0
        }

        System.out.println(Thread.currentThread().getName() + "\t mission is over, main get number value:" + myData.number);
    }

}

class MyData {
    volatile int number = 0;
    // int number = 0; // 程序陷入死循环，因为没有可见性，main线程并不知道number的值已经被修改

    public void addTo60() {
        this.number = 60;
    }
}