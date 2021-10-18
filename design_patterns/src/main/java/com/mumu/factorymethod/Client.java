//designpatterns.factorymethod.Client.java
package com.mumu.factorymethod;

public class Client {
	public static void main(String args[]) {
		LoggerFactory factory;
		Logger logger;
		//factory = new FileLoggerFactory(); //�����������ļ�ʵ��
		factory = (LoggerFactory)XMLUtil.getBean(); //getBean()�ķ�������ΪObject����Ҫ����ǿ������ת��

		logger = factory.createLogger();
		logger.writeLog();
	}
}
