//designpatterns.facade.CipherMachine.java
package com.mumu.facade;

//���ݼ����ࣺ��ϵͳ��
public class CipherMachine {
	public String encrypt(String plainText) {
		System.out.print("���ݼ��ܣ�������ת��Ϊ���ģ�");
		String es = "";
		for(int i = 0; i < plainText.length(); i++) {
			String c = String.valueOf(plainText.charAt(i) % 7);
			es += c;
		}
		System.out.println(es);
		return es;
	}
}
