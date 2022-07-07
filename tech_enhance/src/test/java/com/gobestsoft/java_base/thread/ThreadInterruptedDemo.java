/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.thread;

import org.junit.Test;

/**
 * ThreadInterruptedDemo
 * 线程中断学习
 *
 * @author liuzhen
 * @version 1.0.0 2022/6/28 6:18
 */
public class ThreadInterruptedDemo {

    /**
     * 到底是什么是线程中断？
     * 线程中断即线程运行过程中被其他线程给打断了，它与 stop 最大的区别是：stop 是由系统强制终止线程，而线程中断则是给目标线程发送一个中断信号，
     * 如果目标线程没有接收线程中断的信号并结束线程，线程则不会终止，具体是否退出或者执行其他逻辑由目标线程决定。
     *
     * 3个中断api：
     * java.lang.Thread#interrupt()：实例方法，中断目标线程，给目标线程发一个中断信号，线程被打上中断标记。eg：thread.interrupt()
     * java.lang.Thread#isInterrupted()：实例方法，判断目标线程是否被中断，不会清除中断标记。eg：thread.isInterrupted()
     * java.lang.Thread#interrupted()：静态方法，判断目标线程是否被中断，会清除中断标记。eg：Thread.interrupted()
     *
     * 总结：
     * 首先，一个线程不应该由其他线程来强制中断或停止，而是应该由线程自己自行停止。所以，Thread.stop, Thread.suspend, Thread.resume 都已经被废弃了。
     * 而 Thread.interrupt 的作用其实也不是中断线程，而是「通知线程应该中断了」，具体到底中断还是继续运行，应该由被通知的线程自己处理。
     *
     * 具体来说，当对一个线程，调用 interrupt() 时，
     *  1. 如果线程处于被阻塞状态（例如处于sleep, wait, join 等状态），那么线程将立即退出被阻塞状态，并抛出一个InterruptedException异常。
     *      仅此而已。在线程被同步锁所阻塞时，调用interrupt线程无法去检查中断状态或者抛出InterruptedException
     *  2. 如果线程处于正常活动状态，那么会将该线程的中断标志设置为 true，仅此而已。被设置中断标志的线程将继续正常运行，不受影响。
     *
     * interrupt() 并不能真正的中断线程，需要被调用的线程自己进行配合才行。也就是说，一个线程如果有被中断的需求，那么就可以这样做。
     *  1. 在正常运行任务时，经常检查本线程的中断标志位，如果被设置了中断标志就自行停止线程。
     *  2. 在调用阻塞方法时正确处理InterruptedException异常。（例如，catch异常后就结束线程。）
     */


    /**
     * 中断失败
     * 示例1中的线程会被中断吗？
     * 答案：不会，因为虽然给线程发出了中断信号，但程序中并没有响应中断信号的逻辑，所以程序不会有任何反应。
     * @date 2022/6/28 6:21
     * @param
     * @return void
     */
    @Test
    public void test1() {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread.yield();
            }
        });

        thread.start();
        thread.interrupt();
    }

    /**
     * 中断成功
     * 给示例2加上了响应中断的逻辑，程序接收到中断信号打印出信息后返回退出。
     * @date 2022/6/28 6:22
     * @param
     * @return void
     */
    public void test2() {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread.yield();

                // 响应中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Java技术栈线程被中断，程序退出。");
                    return;
                }
            }
        });

        thread.start();
        thread.interrupt();
    }

    /**
     * 中断失败
     * 示例3 sleep() 方法被中断，并输出了 Java技术栈线程休眠被中断，程序退出。 程序继续运行……
     * 为什么呢？
     * sleep() 源码：public static native void sleep(long millis) throws InterruptedException;
     * 可以看出 sleep() 方法被中断后会清除中断标记，所以循环会继续运行。
     * @date 2022/6/28 6:22
     * @param
     * @return void
     */
    @Test
    public void test3() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                // 响应中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Java技术栈线程被中断，程序退出。111");
                    return;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("Java技术栈线程休眠被中断，程序退出。222");
                }
            }
        });

        thread.start();
        Thread.sleep(2000);
        System.out.println("main 调用 thread.interrupt()");
        thread.interrupt();
    }

    /**
     * 中断成功
     * 示例4全部信息输出并正常退出，只是在 sleep() 方法被中断并清除标记后手动重新中断当前线程，然后程序接收中断信号返回退出。
     * @date 2022/6/28 6:24
     * @param
     * @return void
     */
    @Test
    public void test4() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("进入 where true ...");
                // 响应中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Java技术栈线程被中断，程序退出。111");
                    return;
                }

                try {
                    System.out.println("start 进入 try 里的 Thread.sleep(3000) ...");
                    Thread.sleep(3000);
                    System.out.println("end 进入 try 里的 Thread.sleep(3000) ...");

                } catch (InterruptedException e) {
                    System.out.println("Java技术栈线程休眠被中断，程序退出。222");
                    Thread.currentThread().interrupt();
                }
            }
        });

        thread.start();

        System.out.println("main start 调用 Thread.sleep(2000)");
        Thread.sleep(2000);
        System.out.println("main end 调用 Thread.sleep(2000)");

        System.out.println("main start 调用 thread.interrupt()");
        thread.interrupt();
        System.out.println("main end 调用 thread.interrupt()");
    }


    @Test
    public void test5() {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                // do more work.
            }
        });
        thread.start();

        // 一段时间以后
        thread.interrupt();
    }
}
