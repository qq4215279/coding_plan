/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_2_command.test1.calculator.commander;

// 抽象命令类
public abstract class AbstractCommand {
	// 声明命令执行方法execute()
	public abstract int execute(int value);

	// 声明撤销方法undo()
	public abstract int undo();
}

