/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * OneToHundred
 * ReentrantLock Condition使用
 * 举最简单的两个线程A，B轮流打印1-100
 * @author liuzhen
 * @version 1.0.0 2022/4/24 16:47
 */
public class OneToHundred {
    public static void main(String[] args) throws InterruptedException {
        Task t = new Task();
        // Task2 t = new Task2();
        Thread t1 = new Thread(t, "A");
        Thread t2 = new Thread(t, "B");
        t1.start();
        t2.start();
    }
}

class Task implements Runnable {
    private int number = 0;

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    @Override
    public void run() {
        while (number < 10) {
            lock.lock();
            number++;
            System.out.println("number: " + number);
            condition.signal();
            Thread curThread = Thread.currentThread();

            String curThreadName = curThread.getName();
            String otherThreadName = curThreadName.equals("A") ? "B" : "A";
            System.out.println("当前线程是：" + curThread.getName() + "，唤醒 " + otherThreadName + " 线程");
            try {
                if (number < 10) {
                    System.out.println("当前线程是：" + curThread.getName() + "，进行等待...");
                    condition.await();
                    // System.out.println(curThread.getName() + " wait ------------->");
                }
                System.out.println("本次结束------------->");
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 假设A线程先获得锁，B会阻塞(同步队列)，A第一次调用signal(),但此时条件队列为空，firstWaiter为null，
 * 然后A调用await(),让出锁，同时自己进入条件等待队列，用一个变量记录自己持有锁的次数(saveState)
 * 然后释放锁的时候唤醒同步队列的第一个阻塞线程，即B，B醒了以后，尝试获取锁
 * 假设只有这两个线程，则B获取锁成功，number++，然后signal()，将A加入到同步队列，然后await()，释放锁，唤醒A，将自己阻塞。
 * A醒来后，判断自己是否在同步队列中，在的话跳出自旋（这个地方肯定是在同步队列中的，因为唤醒只会唤醒同步队列上的线程。
 * 此处while循环是怕线程在别的情况下醒来)继续执行await()方法，没执行完，在await()方法内部阻塞了，
 * 然后调用acquire(node,saveState)尝试获得锁，A获取成功，然后number++，在signal()，再调用await()
 * 这样A和B可以一直输出到100，
 * 但是缺少了unlock()操作，ReentrantLock被A和B分别重入50次，A和B可以相互交替运行完全是因为await()和signal()的机制，所以未改正前，
 * 当A退出时，锁被A持有，且state = 50，表示重入了50次。
 * 调用了signal()也只是把B线程从条件队列移动到同步队列，没有调用await()，没让出锁。
 * 即便在最后调用一次unlock()，也只是将state - 1，变为49，依然未释放锁。
 * 所以，在每次lock()之后一定要unlock().
 */

// 改正
class Task2 implements Runnable {
    private int number = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    @Override
    public void run() {
        while (number < 10) {
            lock.lock();
            number++;
            System.out.println("number: " + number);
            condition.signal();
            Thread curThread = Thread.currentThread();

            String curThreadName = curThread.getName();
            String otherThreadName = curThreadName.equals("A") ? "B" : "A";
            System.out.println("当前线程是：" + curThread.getName() + "，唤醒 " + otherThreadName + " 线程");
            try {
                if (number < 10) {
                    System.out.println("当前线程是：" + curThread.getName() + "，进行等待...");
                    condition.await();
                    // System.out.println(curThread.getName() + " wait ------------->");
                }
                System.out.println("本次结束------------->");
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }
}
