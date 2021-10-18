//designpatterns.cor.VicePresident.java
package com.mumu.cor;

//�����³��ࣺ���崦����
public class VicePresident extends Approver {
	public VicePresident(String name) {
		super(name);
	}

	//������������
	public void processRequest(PurchaseRequest request) {
		if (request.getAmount() < 100000) {
			System.out.println("�����³�" + this.name + "�����ɹ�����" + request.getNumber() + "����" + request.getAmount() + "Ԫ���ɹ�Ŀ�ģ�" + request.getPurpose() + "��");  //��������
		}
		else {
			this.successor.processRequest(request);  //ת������
		}
	}
}
