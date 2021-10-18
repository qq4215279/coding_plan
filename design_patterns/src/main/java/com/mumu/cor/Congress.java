//designpatterns.cor.Congress.java
package com.mumu.cor;

//���»��ࣺ���崦����
public class Congress extends Approver {
	public Congress(String name) {
		super(name);
	}

	//������������
	public void processRequest(PurchaseRequest request) {
		System.out.println("�ٿ����»������ɹ�����" + request.getNumber() + "����" + request.getAmount() + "Ԫ���ɹ�Ŀ�ģ�" + request.getPurpose() + "��");	    //��������
	}
}
