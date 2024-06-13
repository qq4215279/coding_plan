
/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_tools.fastjson.json2;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import static com.mumu.java_tools.fastjson.json2.SymbolConstants.*;

/**
 * XML文档类
 * @author WangYuanSheng
 */
public class JsonDocument {
	// SerializeWriter
	private SerializeWriter out;

	// JSONSerializer
	private JSONSerializer serializer;

	// index
	private boolean first = true;

	/**
	 * Construct
	 */
	public JsonDocument() {
		out = new SerializeWriter();
		serializer = new JSONSerializer(out);
	}

	/**
	 * reset
	 */
	public void reset() {
		out = new SerializeWriter();
		serializer = new JSONSerializer(out);
		first = true;
	}

	/**
	 * 开始创建对象节点
	 * @param elementName
	 */
	public void startObject(String elementName) {
		if (!first) {
			append(B_COMMA);
		}
		append(B_QUOT).append(elementName.toCharArray()).append(B_QUOT).append(B_COLON).append(B_L_BRACE);
		first = true;
	}

	/**
	 * 开始创建对象节点
	 */
	public void startObject() {
		if (!first) {
			append(B_COMMA);
		}
		append(B_L_BRACE);
		first = true;
	}

	/**
	 * 结束对象节点
	 */
	public void endObject() {
		append(B_R_BRACE);
		first = false;
	}

	/**
	 * 开始创建数组节点
	 * @param elementName
	 */
	public void startArray(String elementName) {
		if (!first) {
			append(B_COMMA);
		}
		append(B_QUOT).append(elementName.toCharArray()).append(B_QUOT).append(B_COLON).append(B_L_BRACKET);
		first = true;
	}

	/**
	 * 开始创建数组节点
	 */
	public void startArray() {
		if (!first) {
			append(B_COMMA);
		}
		append(B_L_BRACKET);
		first = true;
	}

	/**
	 * 结束数组节点
	 */
	public void endArray() {
		append(B_R_BRACKET);
		first = false;
	}

	/**
	 * 创建元素
	 * @param elementName
	 * @param o
	 */
	public void createElement(String elementName, Object o) {
		if (!first) {
			append(B_COMMA);
		}
		append(B_QUOT).append(elementName.toCharArray()).append(B_QUOT).append(B_COLON);
		createValue(o);
		first = false;
	}

	/**
	 * 创建元素
	 * @param o
	 */
	public void createElement(Object o) {
		if (!first) {
			append(B_COMMA);
		}
		createValue(o);
		first = false;
	}

	/**
	 * 创建值
	 * @param o
	 */
	private void createValue(Object o) {
		try {
			serializer.write(o);
			//append(JSON.toJSONBytes(o, features));
			//OBJECT_MAPPER.writeValue(out, o);
		} catch (Throwable t) {
			throw new RuntimeException("", t);
		}
	}

	/**
	 * Append
	 */
	public void appendJson(final byte[] json) {
		if (!first) {
			append(B_COMMA);
		}
		append(json);
		first = false;
	}

	/**
	 * Append
	 */
	public void appendJson(final String elementName, final byte[] json) {
		if (!first) {
			append(B_COMMA);
		}
		append(B_QUOT).append(elementName.toCharArray()).append(B_QUOT).append(B_COLON);
		append(json);
		first = false;
	}

	/**
	 * Append
	 */
	public void appendObjectJson(final String elementName, final byte[] json) {
		if (!first) {
			append(B_COMMA);
		}
		append(B_QUOT).append(elementName.toCharArray()).append(B_QUOT).append(B_COLON).append(B_L_BRACE);
		append(json);
		append(B_R_BRACE);
		first = false;
	}

	/**
	 * toString
	 */
	@Override public String toString() {
		return new String(out.toBytes("UTF-8"));
	}

	/**
	 * toByte
	 * @return
	 */
	public byte[] toByte() {
		return out.toBytes("UTF-8");
	}

	/**
	 * 往输出流中写入一个对象
	 * @param bytes
	 */
	private JsonDocument append(final char[] bytes) {
		try {
			out.write(bytes);
			return this;
		} catch (Throwable t) {
			throw new RuntimeException("", t);
		}
	}

	/**
	 * 往输出流中写入一个对象
	 * @param bytes
	 */
	private JsonDocument append(final byte[] bytes) {
		try {
			out.write(new String(bytes, 0, bytes.length));
			return this;
		} catch (Throwable t) {
			throw new RuntimeException("", t);
		}
	}
}