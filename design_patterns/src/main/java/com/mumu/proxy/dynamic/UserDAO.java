//designpatterns.proxy.dynamic.UserDAO.java
package com.mumu.proxy.dynamic;

//����UserDAO�ࣺ��ʵ��ɫ
public class UserDAO implements AbstractUserDAO {
	public Boolean findUserById(String userId) {
		if (userId.equalsIgnoreCase("���޼�")) {
			System.out.println("��ѯIDΪ" + userId + "���û���Ϣ�ɹ���");
			return true;
		}
		else {
			System.out.println("��ѯIDΪ" + userId + "���û���Ϣʧ�ܣ�");
			return false;
		}
	}
}
