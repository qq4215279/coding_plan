/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.throwable;

/**
 * ExceptionStackTraceLostTest 异常堆栈丢失测试
 *
 * @author liuzhen
 * @version 1.0.0 2023/8/5 19:05
 */
public class ExceptionStackTraceLostTest {

    /**
     * 堆栈信息不打印test
     * 强制打印异常堆栈  -XX:-OmitStackTraceInFastThrow
     * @date 2023/8/5 19:17
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        boolean flag = false;
        for (int i = 0;; i++) {
            boolean isExceptionStackLoss = isExceptionStackLost();
            if (isExceptionStackLoss) {
                flag = true;
                System.out.println("异常堆栈！！ times: " + i);
            } else if (flag) {
                System.out.println("堆栈正常。 times: " + i);
            }
        }
    }

    /**
     * 打印异常堆栈
     * @date 2023/8/5 19:15
     * @param
     * @return boolean
     */
    public static boolean isExceptionStackLost() {
        boolean isExceptionStackLoss = false;
        try {
            int res = ((Integer)null);
        } catch (Exception e) {
            // 异常堆栈出现
            if (e.getStackTrace().length == 0) {
                try {
                    // NPE 异常堆栈为空，返回true
                    isExceptionStackLoss = true;
                    // 当出现 NPE 异常堆栈为空的时候，停留5秒，便于观察
                    Thread.sleep(5000);
                } catch (Exception e1) {
                }
            }
            e.printStackTrace();
        }

        return isExceptionStackLoss;
    }
}
