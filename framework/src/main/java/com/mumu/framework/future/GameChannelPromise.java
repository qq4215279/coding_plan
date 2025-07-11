/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.framework.future;

/**
 * 游戏业务Promise接口
 */
public interface GameChannelPromise<V> extends GameFuture<V>/* , Promise<V> */ {
    // @Override
    GameChannelPromise<V> setSuccess(V result);

    // @Override
    GameChannelPromise<V> setFailure(Throwable cause);

    @Override
    GameChannelPromise<V> addListener(GameFutureListener<? extends GameFuture<? super V>> listener);

    @Override
    GameChannelPromise<V> await() throws InterruptedException;
}