/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.framework.future;

/**
 * 游戏业务监听器接口
 */
public interface GameFutureListener<F extends GameFuture<?>> {

    void operationComplete(F future) throws Exception;
}