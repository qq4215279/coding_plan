/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_tools.fastjson.json2;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import com.alibaba.fastjson.JSON;

/**
 * 序列化和反序列化帮助类
 * @author   wangys
 * @version  1.0.0.0  2012-5-23 上午10:17:36
 */
@SuppressWarnings("unchecked")
public class Types {
	/** OBJECT_MAPPER */
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	/**
	 * 查看JSON数据
	 * 
	 * @param o
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 * $Date: 2012-5-23 上午10:17:41
	 */
	public static String toString(Object o) throws JsonGenerationException, JsonMappingException, IOException {
		if (o == null) {
			return "null";
		}
		return OBJECT_MAPPER.writeValueAsString(o);
	}

	/**
	 * 对象写为JSON字节数组
	 * @param obj
	 * @return
	 * $Date: 2012-5-23 上午10:18:02
	 */
	public static byte[] writeValueAsBytes(Object obj) {
		return JSON.toJSONString(obj).getBytes();
	}

	/**
	 * 字节数组反序列化为对象
	 * @param <T>
	 * @param bytes
	 * @param clazz
	 * @return
	 * $Date: 2012-5-23 上午10:18:22
	 */
	public static <T> T readValue(byte[] bytes, Class<T> clazz) {
		return JSON.parseObject(new String(bytes), clazz);
	}
	
	/**
	 * 字节数组反序列化为对象
	 * @param <T>
	 * @param bytes
	 * @param clazz
	 * @return
	 * $Date: 2012-5-23 上午10:18:22
	 */
	public static <T> T readValue(byte[] bytes, JavaType javaType) {
		try {
			return (T) OBJECT_MAPPER.readValue(bytes, 0, bytes.length, javaType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
