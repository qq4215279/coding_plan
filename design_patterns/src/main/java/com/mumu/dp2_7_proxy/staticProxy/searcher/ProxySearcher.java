/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_7_proxy.staticProxy.searcher;

import com.mumu.dp2_7_proxy.staticProxy.AccessValidator;
import com.mumu.dp2_7_proxy.staticProxy.Logger;

// 代理查询类：代理主题类
public class ProxySearcher implements Searcher {
    // 维持一个对真实主题的引用
    private RealSearcher searcher = new RealSearcher();
    private AccessValidator validator;
    private Logger logger;

    public String doSearch(String userId, String keyword) {
        // 如果身份验证成功，则执行查询
        if (this.validate(userId)) {
            // 调用真实主题对象的查询方法
            String result = searcher.doSearch(userId, keyword);
            // 记录查询日志
            this.log(userId);
            // 返回查询结果
            return result;
        } else {
            return null;
        }
    }

    // 创建访问验证对象并调用其validate()方法实现身份验证
    public boolean validate(String userId) {
        validator = new AccessValidator();
        return validator.validate(userId);
    }

    // 创建日志记录对象并调用其log()方法实现日志记录
    public void log(String userId) {
        logger = new Logger();
        logger.log(userId);
    }
}
