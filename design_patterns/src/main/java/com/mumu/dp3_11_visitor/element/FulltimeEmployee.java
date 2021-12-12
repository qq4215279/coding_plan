/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_11_visitor.element;

import com.mumu.dp3_11_visitor.visitor.Department;

// 全职员工类：具体元素类
public class FulltimeEmployee implements Employee {
	// 员工姓名
	private String name;
	// 员工周薪
	private double weeklyWage;
	// 工作时间
	private int workTime;

	public FulltimeEmployee(String name,double weeklyWage,int workTime) {
		this.name = name;
		this.weeklyWage = weeklyWage;
		this.workTime = workTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWeeklyWage(double weeklyWage) {
		this.weeklyWage = weeklyWage;
	}

	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}

	public String getName() {
		return (this.name);
	}

	public double getWeeklyWage() {
		return (this.weeklyWage);
	}

	public int getWorkTime() {
		return (this.workTime);
	}

	public void accept(Department handler) {
		// 调用访问者的访问方法
		handler.visit(this);
	}
}
