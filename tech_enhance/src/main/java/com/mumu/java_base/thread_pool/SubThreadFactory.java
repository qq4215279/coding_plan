/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.thread_pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SubThreadFactory
 * 线程工厂
 * @author liuzhen
 * @version 1.0.0 2023/11/13 14:01
 */
public class SubThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public SubThreadFactory(String name) {
        SecurityManager var1 = System.getSecurityManager();
        this.group = var1 != null ? var1.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = name + "-" + poolNumber.getAndIncrement() + "-";
    }

    @Override
    public Thread newThread(Runnable var1) {
        Thread var2 = new Thread(this.group, var1, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
        if (var2.isDaemon()) {
            var2.setDaemon(false);
        }

        if (var2.getPriority() != 5) {
            var2.setPriority(5);
        }

        return var2;
    }
}
