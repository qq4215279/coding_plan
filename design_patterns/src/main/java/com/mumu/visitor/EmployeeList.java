//designpatterns.visitor.EmployeeList.java
package com.mumu.visitor;
import java.util.*;

//Ա���б��ࣺ����ṹ
public class EmployeeList {
	//����һ���������ڴ洢Ա������
	private ArrayList<Employee> list = new ArrayList<Employee>();

	public void addEmployee(Employee employee) {
		list.add(employee);
	}

	//��������Ա�������е�ÿһ��Ա������
	public void accept(Department handler) {
		for(Object obj : list) {
			((Employee)obj).accept(handler);
		}
	}
}
