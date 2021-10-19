package com.mumu.dp1_2_abstractfactory.spring;

import com.mumu.dp1_2_abstractfactory.core.TextField;

public class SpringTextField implements TextField {
	public void display() {
		System.out.println("显示绿色边框文本框。");
	}
}
