/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_4_decorator;

import com.mumu.dp2_4_decorator.component.Component;
import com.mumu.dp2_4_decorator.component.Window;
import com.mumu.dp2_4_decorator.decorator.BlackBorderDecorator;
import com.mumu.dp2_4_decorator.decorator.ScrollBarDecorator;

public class Client {
	public static void main(String args[]) {
		// 创建具体构件对象
		Component component = new Window();
		// 创建装饰后的构件对象
		Component componentSB = new ScrollBarDecorator(component);
		// 将装饰了一次的对象注入另一个装饰类中，进行第二次装饰
		Component componentBB = new BlackBorderDecorator(componentSB);

		componentBB.display();
	}
}
