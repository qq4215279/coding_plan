//designpatterns.visitor.Employee.java
package com.mumu.dp3_11_visitor;

//员工类：抽象元素类
public interface Employee {
	public void accept(Department handler); //接受一个抽象访问者访问
}
