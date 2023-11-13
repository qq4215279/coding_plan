/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_4_decorator.decorator;

import com.mumu.dp2_4_decorator.component.Component;

public class BlackBorderDecorator extends AbstractDecorator {
	public BlackBorderDecorator(Component component) {
		super(component);
	}

	public void display() {
		this.setBlackBorder();
		super.display();
	}

	public void setBlackBorder() {
		System.out.println("为构件增加黑色边框！");
	}
}
