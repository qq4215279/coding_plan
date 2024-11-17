/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.common.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * StandardThreadFactory
 * 标准线程工厂类
 * @author liuzhen
 * @version 1.0.0 2024/5/27 14:57
 */
public class StandardThreadFactory implements ThreadFactory {
    static final AtomicInteger poolNumber = new AtomicInteger(1);
    final ThreadGroup group;
    final AtomicInteger threadNumber = new AtomicInteger(1);
    final String namePrefix;

    /**
     * 创建一个线程工厂
     * @return java.util.concurrent.ThreadFactory
     * @date 2024/5/27 15:00
     */
    public static ThreadFactory defaultThreadFactory() {
        return new StandardThreadFactory("DefaultPool");
    }

    /**
     * 标准线程工厂类
     * @param poolName poolName
     * @return
     * @date 2024/5/27 15:00
     */
    public StandardThreadFactory(String poolName) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = poolName + "-" + poolNumber.getAndIncrement() + "-thread-";
    }

    @Override public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,namePrefix + threadNumber.getAndIncrement(), 0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
