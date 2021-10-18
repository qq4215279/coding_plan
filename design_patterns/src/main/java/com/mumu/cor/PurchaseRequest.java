//designpatterns.cor.PurchaseRequest.java
package com.mumu.cor;

//�ɹ�����������
public class PurchaseRequest {
	private double amount;  //�ɹ����
	private int number;  //�ɹ������
	private String purpose;  //�ɹ�Ŀ��

	public PurchaseRequest(double amount, int number, String purpose) {
		this.amount = amount;
		this.number = number;
		this.purpose = purpose;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return this.number;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getPurpose() {
		return this.purpose;
	}
}
