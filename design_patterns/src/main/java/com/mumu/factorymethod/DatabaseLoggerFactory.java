//designpatterns.factorymethod.DatabaseLoggerFactory.java
package com.mumu.factorymethod;

//���ݿ���־��¼�������࣬�䵱���幤����ɫ
public class DatabaseLoggerFactory implements LoggerFactory {
	public Logger createLogger() {
		//�������ݿ⣬����ʡ��
		//�������ݿ���־��¼������
		Logger logger = new DatabaseLogger();
		//��ʼ�����ݿ���־��¼��������ʡ��
		return logger;
	}
}
