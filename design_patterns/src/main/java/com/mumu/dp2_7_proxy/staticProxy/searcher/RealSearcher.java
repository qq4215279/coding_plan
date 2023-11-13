/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_7_proxy.staticProxy.searcher;

// 具体查询类：真实主题类
public class RealSearcher implements Searcher {
    // 模拟查询商务信息
    public String doSearch(String userId, String keyword) {
        System.out.println("用户'" + userId + "'使用关键词'" + keyword + "'查询商务信息！");
        return "返回具体内容";
    }
}
