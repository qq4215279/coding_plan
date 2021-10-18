//designpatterns.proxy.AccessValidator.java
package com.mumu.proxy;

//�����֤�ࣺҵ����
public class AccessValidator {
	//ģ��ʵ�ֵ�¼��֤
	public boolean validate(String userId) {
		System.out.println("�����ݿ�����֤�û�'" + userId + "'�Ƿ��ǺϷ��û���");
		if (userId.equalsIgnoreCase("���")) {
			System.out.println("'" + userId + "'��¼�ɹ���");
			return true;
		}
		else {
			System.out.println("'" + userId + "'��¼ʧ�ܣ�");
			return false;
		}
	}
}
