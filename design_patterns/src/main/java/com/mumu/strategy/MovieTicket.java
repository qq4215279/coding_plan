//designpatterns.strategy.MovieTicket.java
package com.mumu.strategy;

//��ӰƱ�ࣺ������
public class MovieTicket {
	private double price;
	private Discount discount; //ά��һ���Գ����ۿ��������

	public void setPrice(double price) {
		this.price = price;
	}

	//ע��һ���ۿ������
	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public double getPrice() {
      //�����ۿ�����ۿۼۼ��㷽��
		return discount.calculate(this.price);
	}
}
