/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_7_proxy.staticProxy;

// 日志记录类：业务类
public class Logger {
    // 模拟实现日志记录
    public void log(String userId) {
        System.out.println("更新数据库，用户'" + userId + "'查询次数加1！");
    }
}
