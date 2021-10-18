//designpatterns.cor.Manager.java
package com.mumu.cor;

//�����ࣺ���崦����
public class Manager extends Approver {
	public Manager(String name) {
		super(name);
	}

  //������������
	public void processRequest(PurchaseRequest request) {
		if (request.getAmount() < 80000) {
			System.out.println("����" + this.name + "�����ɹ�����" + request.getNumber() + "����" + request.getAmount() + "Ԫ���ɹ�Ŀ�ģ�" + request.getPurpose() + "��");  //��������
		}
		else {
			this.successor.processRequest(request);  //ת������
		}
	}
}
