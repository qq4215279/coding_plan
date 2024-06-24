package com.mumu.java_tools.guava.event;

import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import com.mumu.design.timer.redis.Utility;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class EventBusExceptionHandler implements SubscriberExceptionHandler {
	@Override
	public void handleException(Throwable exception, SubscriberExceptionContext context) {
		log.error("EventBus context={}, event error msg={},cause={}", context, exception.getMessage(),
				Utility.getTraceString(exception));
	}

}
