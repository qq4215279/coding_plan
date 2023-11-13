/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_7_proxy.jdkDynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * DAOLogHandler
 * 自定义请求处理程序类
 * @author liuzhen
 * @version 1.0.0 2023/11/13 15:53
 */
public class DAOLogHandler implements InvocationHandler {
    private Object object;

    /**
     * 自定义有参构造函数，用于注入一个需要提供代理的真实主题对象
     * @param object object
     * @date 2023/11/13 15:54
     */
    public DAOLogHandler(Object object) {
        this.object = object;
    }

    /**
     * 实现invoke()方法，调用在真实主题类中定义的方法
     * @param proxy proxy
     * @param method method
     * @param args args
     * @return java.lang.Object
     * @date 2023/11/13 15:54
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeInvoke();
        // 转发调用
        Object result = method.invoke(object, args);
        afterInvoke();

        return result;
    }

    /**
     * 前置方法：记录方法调用时间
     * @return void
     * @date 2023/11/13 15:54
     */
    private void beforeInvoke() {
        Calendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String time = hour + ":" + minute + ":" + second;
        System.out.println("调用时间：" + time);
    }

    /**
     * 后置方法
     * @return void
     * @date 2023/11/13 15:54
     */
    private void afterInvoke() {
        System.out.println("方法调用结束！");
    }
}
