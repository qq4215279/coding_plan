/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.framework.studydemo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * SeparateLockDemo
 * 分离锁 - demo
 * @author liuzhen
 * @version 1.0.0 2025/7/11 15:47
 */
public class SeparateLockDemo {
    /**
     * 分离锁
     */
    private static Object[] locks = new Object[1024];
    private static int LOCK_LEN = locks.length;

    static {
        for (int i = 0; i < LOCK_LEN; i++) {
            locks[i] = new Object();
        }
    }


    public void test(int playerId, int targetPlayerId) {
        // 1. 获取锁index
        int lockIndex1 = playerId % LOCK_LEN;
        int lockIndex2 = targetPlayerId % LOCK_LEN;

        // 2. 获取锁对象
        Object lock1 = locks[Math.min(lockIndex1, lockIndex2)];
        Object lock2 = locks[Math.max(lockIndex1, lockIndex2)];

        // 1. 使用synchronized
        synchronized (lock1) {
            synchronized (lock2) {
                // 业务...
                System.out.println("操作玩家1数据....");
                System.out.println("操作玩家2数据....");
            }
        }

        // 2. 使用 LockUtil
        try (InternalLock ignored = LockUtil.getLock(lock1)) {
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
            try (InternalLock ignored2 = LockUtil.getLock(lock2)) {
                System.out.println(Thread.currentThread() + "111111>>> olai olai ooo...");
            }
        }

    }

}
