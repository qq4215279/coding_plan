//designpatterns.factorymethod.DatabaseLogger.java
package com.mumu.factorymethod;

//���ݿ���־��¼�����䵱�����Ʒ��ɫ
public class DatabaseLogger implements Logger {
	public void writeLog() {
		System.out.println("���ݿ���־��¼��");
	}
}
