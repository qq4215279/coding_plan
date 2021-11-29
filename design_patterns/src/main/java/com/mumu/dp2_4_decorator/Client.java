package com.mumu.dp2_4_decorator;

import com.mumu.dp2_4_decorator.component.Component;
import com.mumu.dp2_4_decorator.component.Window;

public class Client {
	public static void main(String args[]) {
		// 使用抽象构件定义全部对象
		Component component, componentSB, componentBB;
		// 创建具体构件对象
		component = new Window();
		// 创建装饰后的构件对象
		componentSB = new ScrollBarDecorator(component);
		// 将装饰了一次的对象注入另一个装饰类中，进行第二次装饰
		componentBB = new BlackBorderDecorator(componentSB);

		componentBB.display();
	}
}
