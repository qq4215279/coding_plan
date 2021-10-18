//designpatterns.proxy.RealSearcher.java
package com.mumu.proxy;

//�����ѯ�ࣺ��ʵ������
public class RealSearcher implements Searcher {
	//ģ���ѯ������Ϣ
	public String doSearch(String userId, String keyword) {
		System.out.println("�û�'" + userId + "'ʹ�ùؼ���'" + keyword + "'��ѯ������Ϣ��");
		return "���ؾ�������";
	}
}
