/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

/**
 * package-info
 * guava 事件
 * @author liuzhen
 * @version 1.0.0 2024/6/24 16:10
 */
package com.mumu.java_tools.guava.event;

/**
 * @Subscribe 注解用于标记一个方法，该方法用于订阅特定类型的事件。当 EventBus 发布该类型的事件时，所有订阅该事件类型的方法都会被调用。
 * @AllowConcurrentEvents 注解用于标记一个订阅方法，表示该方法可以并发处理事件。默认情况下，Guava EventBus 会串行地调用订阅方法，即同一时间内，只有一个事件会被处理。如果你希望订阅方法能够并发处理事件，可以使用 @AllowConcurrentEvents 注解。
 *
 * EventBus 是一个同步的事件总线。它在发布事件时，同步地调用所有订阅者的方法。这意味着发布事件的方法会等待所有订阅者的方法执行完毕后才会返回。
 * 特点:
 * - 同步调用：事件发布者和事件订阅者在同一个线程中运行。
 * - 顺序执行：事件订阅者的方法按注册顺序依次执行。
 * - 适用于快速执行的事件处理：适用于处理时间较短、不会阻塞的方法。
 *
 * AsyncEventBus 是一个异步的事件总线。它在发布事件时，会将事件提交给一个线程池，并由线程池中的线程异步地调用订阅者的方法。这意味着发布事件的方法会立即返回，而订阅者的方法会在后台线程中执行。
 * 特点:
 * - 异步调用：事件发布者和事件订阅者在不同的线程中运行。
 * - 并发执行：事件订阅者的方法可以并发执行。
 * - 适用于耗时操作：适用于处理时间较长、可能会阻塞的方法，例如 I/O 操作、网络请求等。
 *
 * 注意：如果定义的事件所在的监听类，没有注册到指定的event bus，则提交事件不生效，eg：如下字符串事件
 */
