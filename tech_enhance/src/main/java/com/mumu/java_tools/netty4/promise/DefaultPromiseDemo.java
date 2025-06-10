/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.java_tools.netty4.promise;

import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.DefaultPromise;

/**
 * DefaultPromiseDemo
 *
 * @author liuzhen
 * @version 1.0.0 2025/6/10 21:17
 */
public class DefaultPromiseDemo {

    public static void main(String[] args) {
        DefaultPromise<String> promise = new DefaultPromise<String>(new DefaultEventExecutor());
    }
}
