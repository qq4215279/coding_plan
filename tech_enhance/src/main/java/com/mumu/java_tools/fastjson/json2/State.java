/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_tools.fastjson.json2;

public enum State {
	/** 用于与前台交互 */
	SUCCESS(1),
	FAIL(0),
	EXCEPTION(2),
	PUSH(3),
	REDIRECT(4),
	BLOCK(5),
	CODE(6),
	SPECIAL_TIPS(7);
	
	private int value;
	
	private State(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
