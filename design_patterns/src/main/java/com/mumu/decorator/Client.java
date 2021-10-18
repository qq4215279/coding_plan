//designpatterns.decorator.Client.java
package com.mumu.decorator;

public class Client {
	public static void main(String args[]) {
		Component component, componentSB, componentBB;  //ʹ�ó��󹹼�����ȫ������
		component = new Window();         //�������幹������
		componentSB = new ScrollBarDecorator(component); //����װ�κ�Ĺ�������
		componentBB = new BlackBorderDecorator(componentSB); //��װ����һ�εĶ���ע����һ��װ�����У����еڶ���װ��
		componentBB.display();
	}
}
