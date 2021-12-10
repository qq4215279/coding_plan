package com.mumu.dp2_7_proxy;

// 抽象查询类：抽象主题类
public interface Searcher {
    public String doSearch(String userId, String keyword);
}
