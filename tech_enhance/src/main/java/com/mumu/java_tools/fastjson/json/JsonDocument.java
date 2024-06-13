/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_tools.fastjson.json;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

/**
 * JsonDocument
 * json文档工具
 * @author liuzhen
 * @version 1.0.0 2023/9/1 15:16
 */
public class JsonDocument {
	/** SerializeWriter */
	private SerializeWriter out;
	/** JSONSerializer */
	private JSONSerializer serializer;

	/** index */
	private boolean first = true;

	/**
	 * 构造方法
	 */
	public JsonDocument() {
		out = new SerializeWriter();
		serializer = new JSONSerializer(out);
	}

	/**
	 * 开始创建对象节点
	 * @param elementName 元素名称
	 * @return void
	 * @date 2023/9/1 15:20
	 */
	public void startObject(String elementName) {
		if (!first) {
			append(SymbolConstants.B_COMMA);
		}
		append(SymbolConstants.B_QUOT).append(elementName.toCharArray()).append(SymbolConstants.B_QUOT)
				.append(SymbolConstants.B_COLON).append(SymbolConstants.B_L_BRACE);
		first = true;
	}

	/**
	 * 开始创建对象节点
	 * @return void
	 * @date 2023/9/1 15:20
	 */
	public void startObject() {
		if (!first) {
			append(SymbolConstants.B_COMMA);
		}
		append(SymbolConstants.B_L_BRACE);
		first = true;
	}

	/**
	 * 结束对象节点
	 * @return void
	 * @date 2023/9/1 15:20
	 */
	public void endObject() {
		append(SymbolConstants.B_R_BRACE);
		first = false;
	}

	/**
	 * 开始创建数组节点
	 * @param elementName 元素名称
	 * @return void
	 * @date 2023/9/1 15:20
	 */
	public void startArray(String elementName) {
		if (!first) {
			append(SymbolConstants.B_COMMA);
		}
		append(SymbolConstants.B_QUOT).append(elementName.toCharArray()).append(SymbolConstants.B_QUOT)
				.append(SymbolConstants.B_COLON).append(SymbolConstants.B_L_BRACKET);
		first = true;
	}

	/**
	 * 开始创建数组节点
	 * @return void
	 * @date 2023/9/1 15:20
	 */
	public void startArray() {
		if (!first) {
			append(SymbolConstants.B_COMMA);
		}
		append(SymbolConstants.B_L_BRACKET);
		first = true;
	}

	/**
	 * 结束数组节点
	 * @return void
	 * @date 2023/9/1 15:20
	 */
	public void endArray() {
		append(SymbolConstants.B_R_BRACKET);
		first = false;
	}

	/**
	 * 创建元素
	 * @param elementName 元素名称
	 * @param o 对象
	 * @return void
	 * @date 2023/9/1 15:20
	 */
	public void createElement(String elementName, Object o) {
		if (!first) {
			append(SymbolConstants.B_COMMA);
		}
		append(SymbolConstants.B_QUOT).append(elementName.toCharArray()).append(SymbolConstants.B_QUOT)
				.append(SymbolConstants.B_COLON);
		createValue(o);
		first = false;
	}

	/**
	 * 创建元素
	 * @param o 对象
	 * @return void
	 * @date 2023/9/1 15:20
	 */
	public void createElement(Object o) {
		if (!first) {
			append(SymbolConstants.B_COMMA);
		}
		createValue(o);
		first = false;
	}

	/**
	 * 创建值
	 * @param o 对象
	 * @return void
	 * @date 2023/9/1 15:20
	 */
	private void createValue(Object o) {
		try {
			serializer.write(o);
			// append(JSON.toJSONBytes(o, features));
			// OBJECT_MAPPER.writeValue(out, o);
		} catch (Throwable t) {
			throw new RuntimeException("", t);
		}
	}

	/**
	 * Append json
	 * @param json json字节数组
	 * @return void
	 * @date 2023/9/1 15:21
	 */
	public void appendJson(final byte[] json) {
		if (!first) {
			append(SymbolConstants.B_COMMA);
		}
		append(json);
		first = false;
	}

	/**
	 * Append json
	 * @param elementName 元素名称
	 * @param json json字节数组
	 * @return void
	 * @date 2023/9/1 15:21
	 */
	public void appendJson(final String elementName, final byte[] json) {
		if (!first) {
			append(SymbolConstants.B_COMMA);
		}
		append(SymbolConstants.B_QUOT).append(elementName.toCharArray()).append(SymbolConstants.B_QUOT)
				.append(SymbolConstants.B_COLON);
		append(json);
		first = false;
	}

	/**
	 * Append object json
	 * @param elementName 元素名称
	 * @param json json字节数组
	 * @return void
	 * @date 2023/9/1 15:21
	 */
	public void appendObjectJson(final String elementName, final byte[] json) {
		if (!first) {
			append(SymbolConstants.B_COMMA);
		}
		append(SymbolConstants.B_QUOT).append(elementName.toCharArray()).append(SymbolConstants.B_QUOT)
				.append(SymbolConstants.B_COLON).append(SymbolConstants.B_L_BRACE);
		append(json);
		append(SymbolConstants.B_R_BRACE);
		first = false;
	}

	/**
	 * toString
	 * @return java.lang.String
	 * @date 2023/9/1 15:21
	 */
	@Override
	public String toString() {
		return new String(out.toBytes("UTF-8"));
	}

	/**
	 * toByte
	 * @return byte[]
	 * @date 2023/9/1 15:21
	 */
	public byte[] toByte() {
		return out.toBytes("UTF-8");
	}

	/**
	 * 往输出流中写入一个对象
	 * @param bytes 字符数组
	 * @return com.mumu.java_tools.fastjson.json.JsonDocument
	 * @date 2023/9/1 15:21
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
	 * @param bytes 字符数组
	 * @return com.mumu.java_tools.fastjson.json.JsonDocument
	 * @date 2023/9/1 15:22
	 */
	private JsonDocument append(final byte[] bytes) {
		try {
			out.write(new String(bytes, 0, bytes.length));
			return this;
		} catch (Throwable t) {
			throw new RuntimeException("", t);
		}
	}

	/**
	 * 重置
	 * @return void
	 * @date 2023/9/1 15:43
	 */
	private void reset() {
		out = new SerializeWriter();
		serializer = new JSONSerializer(out);
		first = true;
	}

	public static void main(String[] args) {
		// <target>mail1</target><fills><fill>content11111<subTarget>item1</subTarget><fill><fill>content222222<fill></fills>
		JsonDocument doc = new JsonDocument();
		doc.startObject();
		doc.createElement("target", "mail1");
		doc.startArray("fills");
		for (int i = 0; i < 2; i++) {
			doc.startObject();
			doc.createElement("fill", "content" + (i + 1) + "... %s, %s ");
			doc.startArray("subTargetArray");
			doc.createElement("item1");
			doc.createElement("item2");
			doc.endArray();
			doc.endObject();
		}
		doc.endArray();
		doc.endObject();

		System.out.println(doc.toString());
	}
}
