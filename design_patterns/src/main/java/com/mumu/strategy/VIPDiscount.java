//designpatterns.strategy.VIPDiscount.java
package com.mumu.strategy;

//VIP��ԱƱ�ۿ��ࣺ���������
public class VIPDiscount implements Discount {
	private final double DISCOUNT = 0.5;
	public double calculate(double price) {
		System.out.println("VIPƱ��");
		System.out.println("���ӻ��֣�");
		return price * DISCOUNT;
	}
}
