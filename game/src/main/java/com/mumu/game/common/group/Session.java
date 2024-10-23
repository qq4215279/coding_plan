/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

/*
 * $Header: Session.java
 * $Revision: 1.0.0.0
 * $CreateDate: 2011-7-11 下午06:38:01
 * $Owner: wangys
 */
package com.mumu.game.common.group;



/**
 * Session
 * @author wangys
 * @version 1.0.0.0 2011-7-11 下午06:38:01
 */
public interface Session {
    /**
     * 获取SessionId
     * @return
     * @version 1.0.0.0 2011-11-2 下午04:21:10
     */
    String getId();
    
	/**
	 * 获取属性
	 * @param key
	 * @return
	 * @version 1.0.0.0 2011-7-11 下午06:48:17
	 */
	Object getAttribute(String key);
	
	/**
	 * 设置属性
	 * @param key
	 * @param value
	 * @return
	 * @version 1.0.0.0 2011-7-11 下午06:48:43
	 */
	void setAttribute(String key, Object value);
	
	/**
	 * 移除属性
	 * @param key
	 * @return
	 * @version 1.0.0.0 2011-7-11 下午06:50:25
	 */
	boolean removeAttribute(String key);
	
	/**
	 * 使所有属性失效
	 * 
	 * @version 1.0.0.0 2011-7-11 下午06:51:03
	 */
	void invalidate();
	
	/**
	 * 彻底销毁
	 * 
	 * $Date: 2015年5月27日 下午3:55:00
	 */
	void destory();
	
	/**
	 * 标记为丢失
	 * 
	 * $Date: 2012-12-14 上午10:54:53
	 */
	void markDiscard();
	
	/**
	 * 访问session对象
	 * 
	 * $Date: 2011-12-9 下午05:51:36
	 */
	void access();
	
	/**
	 * 设置连接是否有效
	 * @param isValid
	 * $Date: 2011-12-9 下午05:55:35
	 */
	void setValid(boolean isValid);
	
	/**
	 * 是否有效
	 * @return
	 * $Date: 2011-12-9 下午05:53:54
	 */
	boolean isValid();
	
	/**
	 * 是否是活动的
	 * @return
	 * $Date: 2015年5月7日 下午1:23:57
	 */
	boolean isActive();
	
	/**
	 * 是否已过期
	 * @return
	 * $Date: 2015年5月27日 下午3:32:37
	 */
	boolean isExpire();
	
	/**
	 * 是否已失效
	 * @return
	 * $Date: 2015年5月27日 下午3:10:38
	 */
	boolean isInvalidate();
	
	/**
	 * 是否是空的session
	 * @return
	 * $Date: 2017年4月24日 上午11:26:44
	 */
	boolean isEmpty();
	
	/**
	 * 重新激活
	 * $Date: 2015年5月27日 下午3:36:35
	 */
	void reActive();
	
	/**
	 * 过期连接
	 * 
	 * $Date: 2011-12-9 下午05:55:03
	 */
	void expire();
	
	/**
	 * 设置Push通道
	 * @param push
	 * $Date: 2014年7月10日 上午10:07:17
	 */
	void setPush(Push push);
	
	/**
	 * 获得推送通道
	 * @return
	 * $Date: 2014年7月10日 上午10:19:23
	 */
	Push getPush();
	
	/**
	 * 推送数据
	 * @param command
	 * @param body
	 * @return
	 * @version 1.0.0.0 2011-7-11 下午06:42:43
	 */
	void push(String command, byte[] body);
	
	/**
	 * 推送数据，推送的最终的数据结果，不会再做压缩处理
	 * @param buffer
	 * $Date: 2015年1月7日 下午5:46:34
	 */
	void push(Object buffer);
	
}
