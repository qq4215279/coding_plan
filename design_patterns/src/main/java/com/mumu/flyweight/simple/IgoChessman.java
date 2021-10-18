//designpatterns.flyweight.simple.IgoChessman.java
package com.mumu.flyweight.simple;

//Χ�������ࣺ������Ԫ��
public abstract class IgoChessman {
	public abstract String getColor();

	public void display() {
		System.out.println("������ɫ��" + this.getColor());
	}
}
