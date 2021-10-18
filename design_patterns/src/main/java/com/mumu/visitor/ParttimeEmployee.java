//designpatterns.visitor.ParttimeEmployee.java
package com.mumu.visitor;

//��ְԱ���ࣺ����Ԫ����
public class ParttimeEmployee implements Employee {
	private String name; //Ա������
	private double hourWage; //Ա��ʱн
	private int workTime; //����ʱ��

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
		handler.visit(this); //���÷����ߵķ��ʷ���
	}
}
