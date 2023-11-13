/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_2_command.test2;

import com.mumu.dp3_2_command.test2.commander.Command;

public class FunctionButton {
	// 维持一个抽象命令对象的引用
	private Command command;

	// 为功能键注入命令
	public void setCommand(Command command) {
		this.command = command;
	}

	// 发送请求的方法
	public void click() {
		System.out.print("单击功能键: ");
		command.execute();
	}
}
