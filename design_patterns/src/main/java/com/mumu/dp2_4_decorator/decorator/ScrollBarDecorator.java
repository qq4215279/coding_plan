/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_4_decorator.decorator;

import com.mumu.dp2_4_decorator.component.Component;

public class ScrollBarDecorator extends AbstractDecorator {
	public ScrollBarDecorator(Component component) {
		super(component);
	}

	public void display() {
		this.setScrollBar();
		super.display();
	}

	public void setScrollBar() {
		System.out.println("为构件增加滚动条！");
	}
}
