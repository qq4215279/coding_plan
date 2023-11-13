/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_2_command.test2;

import com.mumu.dp3_2_command.test2.commander.Command;

public class Client {
	public static void main(String args[]) {
		FunctionButton fb = new FunctionButton();
		// 定义命令对象
		Command command;
		// 读取配置文件，反射生成对象

		command = (Command)XMLUtil.getBean();
		// 将命令对象注入功能键
		fb.setCommand(command);
		// 调用功能键的业务方法
		fb.click();
	}
}
