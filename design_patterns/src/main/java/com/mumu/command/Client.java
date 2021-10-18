//designpatterns.command.Client.java
package com.mumu.command;

public class Client {
	public static void main(String args[]) {
		FunctionButton fb = new FunctionButton();
		Command command; //�����������
		command = (Command)XMLUtil.getBean(); //��ȡ�����ļ����������ɶ���

		fb.setCommand(command); //���������ע�빦�ܼ�
		fb.click(); //���ù��ܼ���ҵ�񷽷�
	}
}
