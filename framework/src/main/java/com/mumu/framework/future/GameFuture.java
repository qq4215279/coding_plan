/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.framework.future;

// import io.netty.util.concurrent.Future;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 游戏业务Future接口
 */
public interface GameFuture<V> extends Future<V> {

    boolean isSuccess();

    boolean isCancelled();

    Throwable cause();

    V getNow();

    GameFuture<V> addListener(GameFutureListener<? extends GameFuture<? super V>> listener);

    GameFuture<V> await() throws InterruptedException;

    boolean await(long timeout, TimeUnit unit) throws InterruptedException;
}