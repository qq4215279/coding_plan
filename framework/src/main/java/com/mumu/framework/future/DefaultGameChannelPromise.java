/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.framework.future;

import com.mumu.common.thread.StandardThreadFactory;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;


/**
 * 游戏业务专用的Promise实现
 * @param <V> 结果类型
 */
public class DefaultGameChannelPromise<V> implements GameChannelPromise<V> {

    // 线程池数组
    private final ThreadPoolExecutor[] executors;
    // 玩家ID用于选择线程池
    private final String playerId;
    // 结果引用
    private volatile Object result;
    // 监听器列表
    private Object listeners;
    // 等待线程计数器
    private short waiters;

    // 原子更新器
    private static final AtomicReferenceFieldUpdater<DefaultGameChannelPromise, Object> RESULT_UPDATER =
            AtomicReferenceFieldUpdater.newUpdater(DefaultGameChannelPromise.class, Object.class, "result");

    private static final Object SUCCESS_SIGNAL = new Object();
    private static final Object UNCANCELLABLE = new Object();

    /**
     * 构造函数
     * @param executors 线程池数组
     * @param playerId 玩家ID
     */
    public DefaultGameChannelPromise(ThreadPoolExecutor[] executors, String playerId) {
        this.executors = executors;
        this.playerId = playerId;
    }

    /**
     * 根据玩家ID选择线程池
     */
    private ThreadPoolExecutor selectExecutor() {
        if (executors == null || executors.length == 0) {
            throw new IllegalStateException("No executors configured");
        }
        // 简单哈希算法选择线程池
        int index = Math.abs(playerId.hashCode()) % executors.length;
        return executors[index];
    }

    @Override
    public GameChannelPromise<V> setSuccess(V result) {
        if (setSuccess0(result)) {
            notifyListeners();
            return this;
        }
        throw new IllegalStateException("Complete already: " + this);
    }

    // @Override
    public boolean trySuccess(V result) {
        if (setSuccess0(result)) {
            notifyListeners();
            return true;
        }
        return false;
    }

    private boolean setSuccess0(V result) {
        return RESULT_UPDATER.compareAndSet(this, null, result == null ? SUCCESS_SIGNAL : result);
    }

    @Override
    public GameChannelPromise<V> setFailure(Throwable cause) {
        if (setFailure0(cause)) {
            notifyListeners();
            return this;
        }
        throw new IllegalStateException("Complete already: " + this);
    }

    // @Override
    public boolean tryFailure(Throwable cause) {
        if (setFailure0(cause)) {
            notifyListeners();
            return true;
        }
        return false;
    }

    private boolean setFailure0(Throwable cause) {
        return RESULT_UPDATER.compareAndSet(this, null, new CauseHolder(cause));
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        if (RESULT_UPDATER.compareAndSet(this, null, UNCANCELLABLE)) {
            notifyListeners();
            return true;
        }
        return false;
    }

    @Override
    public boolean isCancelled() {
        return result == UNCANCELLABLE;
    }

    @Override
    public boolean isDone() {
        return result != null;
    }

    @Override
    public boolean isSuccess() {
        Object result = this.result;
        return result != null && result != UNCANCELLABLE && !(result instanceof CauseHolder);
    }

    @Override
    public Throwable cause() {
        Object result = this.result;
        return result instanceof CauseHolder ? ((CauseHolder) result).cause : null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V getNow() {
        Object result = this.result;
        if (result instanceof CauseHolder || result == UNCANCELLABLE) {
            return null;
        }
        return (V) (result == SUCCESS_SIGNAL ? null : result);
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        Object result = this.result;
        if (!isDone()) {
            await();
            result = this.result;
        }
        return getNow();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        Object result = this.result;
        if (!isDone()) {
            if (!await(timeout, unit)) {
                throw new TimeoutException();
            }
            result = this.result;
        }
        return getNow();
    }

    @Override
    public GameChannelPromise<V> addListener(GameFutureListener<? extends GameFuture<? super V>> listener) {
        synchronized (this) {
            addListener0(listener);
        }
        if (isDone()) {
            notifyListeners();
        }
        return this;
    }

    private void addListener0(GameFutureListener<? extends GameFuture<? super V>> listener) {
        if (listeners == null) {
            listeners = listener;
        } else if (listeners instanceof GameFutureListener) {
            listeners = new GameFutureListener[]{(GameFutureListener) listeners, listener};
        } else {
            GameFutureListener[] array = (GameFutureListener[]) listeners;
            int length = array.length;
            GameFutureListener[] newArray = Arrays.copyOf(array, length + 1);
            newArray[length] = listener;
            listeners = newArray;
        }
    }

    private void notifyListeners() {
        // 确保在正确的线程执行监听器
        ThreadPoolExecutor executor = selectExecutor();
        if (executor == null) {
            // 如果没有线程池，在当前线程执行
            notifyListenersNow();
        } else {
            executor.execute(this::notifyListenersNow);
        }
    }

    private void notifyListenersNow() {
        Object listeners;
        synchronized (this) {
            if (this.listeners == null) {
                return;
            }
            listeners = this.listeners;
            this.listeners = null;
        }

        if (listeners instanceof GameFutureListener) {
            notifyListener((GameFutureListener) listeners);
        } else {
            for (GameFutureListener l : (GameFutureListener[]) listeners) {
                notifyListener(l);
            }
        }
    }

    private void notifyListener(GameFutureListener listener) {
        try {
            listener.operationComplete(this);
        } catch (Throwable t) {
            // 记录日志
        }
    }

    @Override
    public GameChannelPromise<V> await() throws InterruptedException {
        if (isDone()) {
            return this;
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        synchronized (this) {
            while (!isDone()) {
                checkDeadLock();
                incWaiters();
                try {
                    wait();
                } finally {
                    decWaiters();
                }
            }
        }
        return this;
    }

    @Override
    public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
        if (isDone()) {
            return true;
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        long nanos = unit.toNanos(timeout);
        synchronized (this) {
            while (!isDone()) {
                if (nanos <= 0) {
                    return false;
                }
                checkDeadLock();
                incWaiters();
                try {
                    // nanos = wait(nanos);
                    wait(nanos);
                } finally {
                    decWaiters();
                }
            }
            return true;
        }
    }

    private void checkDeadLock() {
        // 防止在业务线程池中阻塞等待自己的结果
        for (ThreadPoolExecutor executor : executors) {
            if (executor != null && executor.getThreadFactory() instanceof StandardThreadFactory) {
                if (((StandardThreadFactory) executor.getThreadFactory()).isCurrentThreadFromFactory()) {
                    throw new IllegalStateException("Deadlock detected");
                }
            }
        }
    }

    private void incWaiters() {
        if (waiters == Short.MAX_VALUE) {
            throw new IllegalStateException("Too many waiters");
        }
        ++waiters;
    }

    private void decWaiters() {
        --waiters;
    }

    // 异常持有者
    private static final class CauseHolder {
        final Throwable cause;
        CauseHolder(Throwable cause) {
            this.cause = cause;
        }
    }
}