/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_11_visitor.element;

import com.mumu.dp3_11_visitor.visitor.Department;

// 兼职员工类：具体元素类
public class ParttimeEmployee implements Employee {
	// 员工姓名
	private String name;
	// 员工时薪
	private double hourWage;
	// 工作时间
	private int workTime;

	public ParttimeEmployee(String name,double hourWage,int workTime) {
		this.name = name;
		this.hourWage = hourWage;
		this.workTime = workTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHourWage(double hourWage) {
		this.hourWage = hourWage;
	}

	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}

	public String getName() {
		return (this.name);
	}

	public double getHourWage() {
		return (this.hourWage);
	}

	public int getWorkTime() {
		return (this.workTime);
	}

	public void accept(Department handler) {
		// 调用访问者的访问方法
		handler.visit(this);
	}
}
