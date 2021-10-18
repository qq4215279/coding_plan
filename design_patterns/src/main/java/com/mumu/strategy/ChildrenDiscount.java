//designpatterns.strategy.ChildrenDiscount.java
package com.mumu.strategy;

//��ͯƱ�ۿ��ࣺ���������
public class ChildrenDiscount implements Discount {
	private final double DISCOUNT = 10;
	public double calculate(double price) {
		System.out.println("��ͯƱ��");
		if(price>=20) {
			return price - DISCOUNT;
		}
		else {
			return price;
		}
	}
}
