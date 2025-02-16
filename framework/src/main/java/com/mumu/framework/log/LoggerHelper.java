/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.framework.log;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * LoggerHelper
 *
 * @author liuzhen
 * @version 1.0.0 2024/12/5 22:59
 */
@Slf4j
public class LoggerHelper {
    /** 异常堆栈行数 */
    static volatile int LINES = 100;
    /** BLANK */
    public static final String TAB = "\t";
    /** NEW_LINE */
    public static final String NEW_LINE = "\n";
    /** 异常关键串 */
    protected static final String EXCEPTION_STR = "com.mumu";

    public static void main(String[] args) {

        String msg = "";

    }

    public void info(String msg, Throwable t) {
        // TODO 打印日志
        log.error(getThrowableTrace(msg, getOriginThrowable(t)));
    }


    /**
     * 获取错误的原始原因
     * 
     * @param t t
     * @return java.lang.Throwable
     * @date 2024/12/5 22:59
     */
    public static Throwable getOriginThrowable(Throwable t) {
        if (t instanceof InvocationTargetException) {
            InvocationTargetException e = (InvocationTargetException)t;
            return getOriginThrowable(e.getTargetException());

        } else if (t instanceof UndeclaredThrowableException) {
            UndeclaredThrowableException e = (UndeclaredThrowableException)t;
            return getOriginThrowable(e.getUndeclaredThrowable());

        } else if (t.getClass() == RuntimeException.class) {
            RuntimeException e = (RuntimeException)t;
            if (null != e.getCause() && (e.getCause() instanceof InvocationTargetException
                    || e.getCause() instanceof UndeclaredThrowableException)) {
                return getOriginThrowable(e.getCause());
            }
        }

        return t;
    }

    /**
     * 获取异常Trace第num条
     * 
     * @param msg msg
     * @param arg1 arg1
     * @return java.lang.String
     * @date 2024/12/5 23:00
     */
    public static String getThrowableTrace(String msg, Throwable arg1) {
        int num = LINES;
        StackTraceElement[] stacks = arg1.getStackTrace();
        StringBuilder builder = new StringBuilder(256);
        builder.append(arg1);
        builder.append("#");
        if (null != msg) {
            builder.append(msg.replace("#", "@_@"));
        }

        int index = 1;
        boolean count = false;
        for (StackTraceElement element : stacks) {
            String value = element.toString();
            builder.append(NEW_LINE).append(TAB).append(value);
            if (!count && value.indexOf(EXCEPTION_STR) != -1) {
                // 如果出现了标志行
                count = true;
            }
            if (count) {
                // 计数
                index++;
                if (index > num) {
                    // 超过指定行
                    break;
                }
            }
        }
        return builder.toString();
    }
}
