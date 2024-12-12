/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_tools.guava.event;

import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import com.mumu.common.redis.Utility;

import lombok.extern.slf4j.Slf4j;

/**
 * EventBusExceptionHandler 处理事件异常
 * 
 * @author liuzhen
 * @version 1.0.0 2024/6/24 16:18
 */
@Slf4j
public class EventBusExceptionHandler implements SubscriberExceptionHandler {
    @Override
    public void handleException(Throwable exception, SubscriberExceptionContext context) {
        log.error("EventBus context={}, event error msg={},cause={}", context, exception.getMessage(),
            Utility.getTraceString(exception));
    }

}
