/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */
package com.mumu.java_tools.fastjson.json2;

/**
 * JsonInterceptor Json信息拦截器
 * <p>该拦截器只能拦截消息提示，其他信息不能拦截
 * @author   wangys
 * @version  1.0.0.0  2014-1-3 上午10:44:13
 */
public interface JsonInterceptor {
	/**
	 * 拦截消息
	 * @param state
	 * @param msg
	 * $Date: 2014-1-3 上午10:45:28
	 */
	void interceptor(State state, String msg);
}
