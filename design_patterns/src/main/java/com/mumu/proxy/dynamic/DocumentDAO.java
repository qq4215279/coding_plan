//designpatterns.proxy.dynamic.DocumentDAO.java
package com.mumu.proxy.dynamic;

//����DocumentDAO�ࣺ��ʵ��ɫ
public class DocumentDAO implements AbstractDocumentDAO {
	public Boolean deleteDocumentById(String documentId) {
		if (documentId.equalsIgnoreCase("D001")) {
			System.out.println("ɾ��IDΪ" + documentId + "���ĵ���Ϣ�ɹ���");
			return true;
		}
		else {
			System.out.println("ɾ��IDΪ" + documentId + "���ĵ���Ϣʧ�ܣ�");
			return false;
		}
	}
}
