package com.mumu.dp1_1_factorymethod.core;

// 日志记录器工厂接口，充当抽象工厂角色
public interface LoggerFactory {
	Logger createLogger(); // 抽象工厂方法
}
