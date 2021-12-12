/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_11_visitor.element;

import com.mumu.dp3_11_visitor.visitor.Department;

// 员工类：抽象元素类
public interface Employee {
	// 接受一个抽象访问者访问
	void accept(Department handler);
}
