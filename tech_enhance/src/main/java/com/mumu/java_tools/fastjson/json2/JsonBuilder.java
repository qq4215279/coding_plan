/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_tools.fastjson.json2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.reign.framework.log.InternalLoggerFactory;
import com.reign.framework.log.Logger;

/**
 * JsonBuilder
 * @author panqq
 *
 * 2011-3-7 上午11:34:19
 */
public class JsonBuilder {
	/** log */
	private static final Logger log = InternalLoggerFactory.getLogger(JsonBuilder.class);
	
    /**
     * 默认的表示成功的json
     */
    public static final byte[] DEFAULT_SUCJSON = getJson(State.SUCCESS, "");
	
	/** COMMA */
	public static final byte[] COMMA = { ',' };
	/** QUOTE */
	public static final byte[] QUOTE = { '"' };
	/** COLON */
	public static final byte[] COLON = { ':' };
	/** START_ARRAY */
	public static final byte[] START_ARRAY = { '[' };
	/** END_ARRAY */
	public static final byte[] END_ARRAY = { ']' };
	/** START_OBJECT */
	public static final byte[] START_OBJECT = { '{' };
	/** END_OBJECT */
	public static final byte[] END_OBJECT = { '}' };
	
	/** 拦截器 */
	private static List<JsonInterceptor> INTERCEPTORS = null;
	/** 拦截标记位，默认为false */
	private static volatile boolean needInterceptor = false;
	
	/**
	 * 生成一个Json结构
	 * 
	 * @param msg	提示信息
	 * @param url	转向地址
	 * @return
	 */
	public static byte[] getRedirectJson(String msg, String url) {
		JsonDocument doc = new JsonDocument();
		
		doc.startObject();
		
		createNamedElement(doc, "state", State.REDIRECT.getValue());
		
		doc.startObject("data");
		createNamedElement(doc, "msg", msg);
		createNamedElement(doc, "url", url);
		doc.endObject();
		
		doc.endObject();
		
		// 拦截消息
		interceptor(State.REDIRECT, msg);
		
		return doc.toByte();
	}
	
	/**
	 * 生成一个Json结构
	 * 
	 * @param state
	 * @param msg
	 * @return
	 */
	public static byte[] getJson(State state, String msg) {
		JsonDocument doc = new JsonDocument();
		
		doc.startObject();
		
		createNamedElement(doc, "state", state.getValue());
		
		doc.startObject("data");
		createNamedElement(doc, "msg", msg);
		doc.endObject();
		
		doc.endObject();
		
		// 拦截消息
		interceptor(state, msg);
		
		return doc.toByte();
	}
	
	/**
	 * 生成一个Json结构
	 * 
	 * @param state
	 * @param msg
	 * @return
	 */
	public static byte[] getJsonWithKey(State state, String msg, String key) {
		JsonDocument doc = new JsonDocument();
		
		doc.startObject();
		
		createNamedElement(doc, "state", state.getValue());
		
		doc.startObject("data");
		createNamedElement(doc, "msg", msg);
		createNamedElement(doc, "key", key);
		doc.endObject();
		
		doc.endObject();
		
		// 拦截消息
		interceptor(state, msg);
		
		return doc.toByte();
	}
	
	/**
	 * 生成一个Json结构 -- 为了兼容mjcs项目
	 * 如果是FAIL或者EXCEPTION 格式:  {"state"=0|2, "message"=""}
	 * 如果是SUCCESS 格式: {"state"=1, "data"={msg="", ... }}
	 * @param state
	 * @param msg
	 * @return
	 */
	public static byte[] getMjcsJson(State state, String msg) {
		JsonDocument doc = new JsonDocument();
		
		doc.startObject();
		createNamedElement(doc, "state", state.getValue());
		
		if (state.equals(State.FAIL) || state.equals(State.EXCEPTION)) {
			createNamedElement(doc, "message", msg);
		} else {
            if( msg != "" )
            {
                doc.startObject("data");
                createNamedElement(doc, "msg", msg);
                doc.endObject();
            }
		}
		doc.endObject();
		
		// 拦截消息
		interceptor(state, msg);
		
		return doc.toByte();
	}
	
	/**
	 * 生成一个Json结构
	 * 
	 * @param state
	 * @param title
	 * @param msg
	 * @return
	 */
	public static byte[] getJson(State state, String title, String msg) {
		JsonDocument doc = new JsonDocument();
		
		doc.startObject();
		createNamedElement(doc, "state", state.getValue());
		
		doc.startObject("data");
		createNamedElement(doc, title, msg);
		doc.endObject();		
		
		doc.endObject();
		
		// 拦截消息
		interceptor(state, msg);

		return doc.toByte();
	}
	
	/**
	 * 生成一个类结构的Json
	 * 
	 * @param <T>
	 * @param state
	 * @param msg
	 * @param t
	 * @return
	 */
	public static <T> byte[] getJson(State state, String name, T t) {
		JsonDocument doc = new JsonDocument();
		doc.startObject();
		
		createNamedElement(doc, "state", state.getValue());
		doc.startObject("data");
		createNamedElement(doc, name, t);
		doc.endObject();
		
		doc.endObject();

		return doc.toByte();
	}
	
	/**
	 * 生成一个类结构的Json
	 * 
	 * @param <T>
	 * @param state
	 * @param msg
	 * @param t
	 * @return
	 */
	public static <T> byte[] getJson(State state, String name, byte[] bytes) {
		JsonDocument doc = new JsonDocument();
		doc.startObject();
		
		createNamedElement(doc, "state", state.getValue());
		doc.startObject("data");
		doc.appendJson(name, bytes);
		doc.endObject();
		
		doc.endObject();

		return doc.toByte();
	}
	
	/**
	 * 生成一个json结构 - for 名将跨服国战
	 * @param <T>
	 * @param state
	 * @param command
	 * @param bytes
	 * @return
	 * $Date: 2012-7-24 下午03:20:22
	 */
	public static <T> byte[] getMjcsJson(State state, String command, byte[] bytes) {
		JsonDocument doc = new JsonDocument();
		doc.startObject();
		
		createNamedElement(doc, "state", state.getValue());
		createNamedElement(doc, "command", command);
		doc.startObject("data");
		doc.appendJson(bytes);
		doc.endObject();
		
		doc.endObject();

		return doc.toByte();
	}
	
	/**
	 * 生成一个类结构的Json
	 * 
	 * @param <T>
	 * @param state
	 * @param msg
	 * @param t
	 * @return
	 */
	public static byte[] getJson(State state, byte[] json) {
		JsonDocument doc = new JsonDocument();
		doc.startObject();
		
		createNamedElement(doc, "state", state.getValue());
		doc.appendJson("data", json);
		
		doc.endObject();

		return doc.toByte();
	}
	
	/**
	 * 生成一个类结构的Json
	 * 将Json格式用一个Object包裹起来
	 * e.g: '{' + json + '}'
	 * 
	 * @param <T>
	 * @param state
	 * @param msg
	 * @param t
	 * @return
	 */
	public static byte[] getObjectJson(State state, byte[] json) {
		JsonDocument doc = new JsonDocument();
		doc.startObject();
		
		createNamedElement(doc, "state", state.getValue());
		doc.appendObjectJson("data", json);
		
		doc.endObject();

		return doc.toByte();
	}
	
	
	/**
	 * 生成一个类结构的Json
	 * 
	 * @param <T>
	 * @param state
	 * @param msg
	 * @param t
	 * @return
	 */
	public static byte[] getJson(byte[] action) {
		JsonDocument doc = new JsonDocument();
		doc.startObject();
		
		// action
		doc.appendJson("action", action);
		
		doc.endObject();

		return doc.toByte();
	}
	
	/**
	 * 生成一个类结构的Json
	 * 
	 * @param <T>
	 * @param state
	 * @param msg
	 * @param t
	 * @return
	 */
	public static byte[] getJson(byte[] action, byte[] extra) {
		JsonDocument doc = new JsonDocument();
		doc.startObject();
		
		// action
		doc.appendJson("action", action);
		
		// extra
		doc.appendJson("extra", extra);
		
		doc.endObject();

		return doc.toByte();
	}
	
	/**
	 * 生成一个类结构的Json
	 * 
	 * @param <T>
	 * @param state
	 * @param msg
	 * @param t
	 * @return
	 */
	public static <T> byte[] getJson(State state, String msg, String name, T t) {
		JsonDocument doc = new JsonDocument();
		doc.startObject();
		
		createNamedElement(doc, "state", state.getValue());
		
		doc.startObject("data");
		createNamedElement(doc, "msg", msg);
		createNamedElement(doc, name, t);
		doc.endObject();	
		
		doc.endObject();
		
		// 拦截消息
		interceptor(state, msg);

		return doc.toByte();
	}
	
	/**
	 * 生成Map的Json
	 * @param <T>
	 * @param state
	 * @param map
	 * @return
	 * $Date: 2012-6-4 下午12:02:32
	 */
	public static <T> byte[] getJson(State state, Map<String, byte[]> map) {
		JsonDocument doc = new JsonDocument();
		doc.startObject();

		createNamedElement(doc, "state", state.getValue());
		doc.startObject("data");
		for (String key : map.keySet()) {
			doc.appendJson(key, map.get(key));
		}
		doc.endObject();

		doc.endObject();

		return doc.toByte();
	}
	
	/**
	 * 获取简单Json格式
	 * {name: value}
	 * @param name 节点名称
	 * @param value 节点值
	 * @return
	 * $Date: 2012-11-19 下午02:14:35
	 */
	public static byte[] getSimpleJson(String name, Object value){
		JsonDocument doc = new JsonDocument();
		doc.startObject();
		doc.createElement(name, value);
		doc.endObject();
		return doc.toByte();
	}
	
	/**
	 * 获取异常的Json格式数据
	 * @author wangys
	 *
	 * @param state
	 * @param errorCode
	 * @return
	 * 2011-3-10 上午11:38:00
	 */
	public static byte[] getExceptionJson(State state, String errorCode) {
		JsonDocument doc = new JsonDocument();
		doc.startObject();
		doc.startObject("action");
		
		createNamedElement(doc, "state", state.getValue());
		
		doc.startObject("data");
		createNamedElement(doc, "errorCode", errorCode);
		doc.endObject();	
		
		doc.endObject();
		doc.endObject();

		return doc.toByte();
	}
	
	/**
	 * 获取异常的Json格式数据2
	 * @param state
	 * @param errorCode
	 * @return
	 * $Date: 2013-4-16 上午11:39:18
	 */
	public static byte[] getExceptionWithoutActionJson(State state, String errorCode) {
		JsonDocument doc = new JsonDocument();
		doc.startObject();
		createNamedElement(doc, "state", state.getValue());
		
		doc.startObject("data");
		createNamedElement(doc, "errorCode", errorCode);
		doc.endObject();	
		
		doc.endObject();
		
		// 拦截消息
		interceptor(state, errorCode);

		return doc.toByte();
	}
	
	/**
	 * 获取验证码的Json数据格式
	 * @author wangys
	 *
	 * @param state
	 * @return
	 * 2011-3-10 上午11:41:35
	 */
	public static byte[] getCodeJson(State state) {
		JsonDocument doc = new JsonDocument();
		doc.startObject();
		doc.startObject("action");
		
		createNamedElement(doc, "state", state.getValue());
		
		doc.endObject();
		doc.endObject();
		
		// 拦截消息
		interceptor(state, "code");

		return doc.toByte();
	}
	


	/**
	 * 创建一个元素
	 * @param out
	 * @param value
	 */
	public static void createElement(JsonDocument doc, Object value) {
		doc.createElement(value);
	}
	
	/**
	 * 以指定名称创建一个对象
	 * @param out
	 * @param name
	 * @param value
	 */
	public static void createNamedElement(JsonDocument doc, String name, Object value) {
		doc.createElement(name, value);
	}

    /**
     * 返回正常信息
     * @param key
     * @param value
     * @return
     */
    public static  byte[] getJsonDocumentWithSuccess(String key,Object value){
        JsonDocument doc = new JsonDocument();
        doc.startObject();
        doc.createElement(key, value);
        doc.endObject();
        return JsonBuilder.getJson( State.SUCCESS, doc.toByte() );

    }
    
    /**
     * 取得包含失败理由的json
     * @param failReason
     * @return
     */
    public static byte[] getFailJson(String failReason) {
        JsonDocument doc = new JsonDocument();
        doc.startObject();
        createNamedElement(doc, "state", State.FAIL.getValue());
        createNamedElement(doc, "msg", failReason);
        doc.endObject();
        
        return doc.toByte();
    }
    
    /**
     * 取得成功的json
     * @param info
     * @return
     */
    public static byte[] getSuccJson(byte[] info) {
        return getJson(State.SUCCESS, info);
    }
    
    /**
     * 添加拦截器
     * @param interceptor Json信息拦截器
     * $Date: 2014-1-3 上午10:53:07
     */
    public static synchronized void addInterceptor(JsonInterceptor interceptor) {
    	if (null == INTERCEPTORS) {
    		INTERCEPTORS = new ArrayList<JsonInterceptor>(1);
    	}
    	INTERCEPTORS.add(interceptor);
    	needInterceptor = true;
    }
    
    /**
     * 拦截消息
     * @param state
     * @param msg
     * $Date: 2014-1-3 上午10:54:23
     */
    private static final void interceptor(State state, String msg) {
    	if (needInterceptor) {
    		try {
	    		for (JsonInterceptor interceptor : INTERCEPTORS) {
	    			interceptor.interceptor(state, msg);
	    		}
    		} catch (Throwable t) {
    			// 发生了异常
    			log.error("interceptor json error [state:{}, msg:{}]", t, state.getValue(), msg);
    		}
    	}
    }
	
}
