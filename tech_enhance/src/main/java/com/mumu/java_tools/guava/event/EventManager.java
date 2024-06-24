/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_tools.guava.event;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.mumu.design.timer.StandardThreadFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * EventManager
 * 时间管理器
 * @author liuzhen
 * @version 1.0.0 2024/6/24 16:18
 */
public class EventManager {
    /** 通用同步事件总线 */
    protected static EventBus syncEventBus;
    /** 通用异步事件总线 */
    protected static AsyncEventBus asyncEventBus;

    public EventManager() {
        initEventBus();
        registerListener();
    }

    /**
     * 初始化bus
     * @return void
     * @date 2024/6/24 16:43
     */
    private void initEventBus() {
        syncEventBus = new EventBus();

        ThreadPoolExecutor eventExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1, new StandardThreadFactory("EventBusThreadName"));
        eventExecutor.prestartAllCoreThreads();
        asyncEventBus = new AsyncEventBus(eventExecutor, new EventBusExceptionHandler());
    }

    /**
     * 注册
     * @return void
     * @date 2024/6/24 16:43
     */
    private void registerListener() {
        syncEventBus.register(new EventSyncListener());
        asyncEventBus.register(new EventAsyncListener());
    }

    /**
     * 提交同步事件
     * @param event event
     * @return void
     * @date 2024/6/24 16:47
     */
    public void postSyncEvent(Object event) {
        syncEventBus.post(event);
    }

    /**
     * 提交异步事件
     * @param event event
     * @return void
     * @date 2024/6/24 16:47
     */
    public void postAsyncEvent(Object event) {
        asyncEventBus.post(event);
    }

    public static void main(String[] args) {
        EventManager eventManager = new EventManager();

        eventManager.postSyncEvent("同步字符串事件");
        // 注意：如果定义的事件所在的监听类，没有注册到指定的event bus，则提交事件不生效，eg：如下字符串事件
        // eventManager.postAsyncEvent("异步字符串事件");

        eventManager.postSyncEvent(new PlayerUplevelEvent(10001, 100));

        eventManager.postAsyncEvent(new PlayerItemChangeEvent(10001, 1002, 1000000));

    }

}
