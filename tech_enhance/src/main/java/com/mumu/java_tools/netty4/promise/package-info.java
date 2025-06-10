/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

/**
 * package-info
 * DefaultPromiseDemo
 * @author liuzhen
 * @version 1.0.0 2025/6/10 21:18
 */
package com.mumu.java_tools.netty4.promise;

/**
 * 继承关系: DefaultPromise<V> extends AbstractFuture<V> implements Promise<V>
 * 实现原理
 * 1. 监听器机制
 *  DefaultPromise 支持添加监听器，在操作完成时通知：
 *  public interface GenericFutureListener<F extends Future<?>> {
 *     void operationComplete(F future) throws Exception;
 * }
 * // 添加监听器
 * Promise<V> addListener(GenericFutureListener<? extends Future<? super V>> listener);
 *
 *
 * 2. 等待/通知机制
 * 内部使用 synchronized 块和 wait()/notifyAll() 实现线程同步：
 *  // 等待结果的核心实现
 * private void await() throws InterruptedException {
 *     synchronized (this) {
 *         while (!isDone()) {
 *             checkDeadLock();  // 检查死锁
 *             incWaiters();     // 增加等待者计数
 *             try {
 *                 wait();      // 释放锁并等待
 *             } finally {
 *                 decWaiters(); // 减少等待者计数
 *             }
 *         }
 *     }
 * }
 *
 * 3. 取消机制
 * public boolean cancel(boolean mayInterruptIfRunning) {
 *     // 原子性地尝试取消
 *     if (RESULT_UPDATER.compareAndSet(this, null, CANCELLATION_CAUSE)) {
 *         // 通知所有等待线程
 *         checkNotifyWaiters();
 *         // 触发监听器
 *         notifyListeners();
 *         return true;
 *     }
 *     return false;
 * }
 *
 */