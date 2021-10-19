package com.mumu.dp1_1_factorymethod.file;

import com.mumu.dp1_1_factorymethod.core.Logger;

// 文件日志记录器，充当具体产品角色
public class FileLogger implements Logger {
	public void writeLog() {
		System.out.println("文件日志记录。");
	}
}
