/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_7_proxy.staticProxy;

import com.mumu.dp2_7_proxy.staticProxy.searcher.Searcher;

public class Client {
    public static void main(String args[]) {
        // 针对抽象编程，客户端无须分辨真实主题类和代理类
        Searcher searcher;
        searcher = (Searcher)XMLUtil.getBean();
        String result = searcher.doSearch("杨过", "玉女心经");

        System.out.println("result: " + result);

    }
}
