//designpatterns.observer.ConcreteAllyControlCenter.java
package com.mumu.observer;

//����ս�ӿ��������ࣺ����Ŀ����
public class ConcreteAllyControlCenter extends AllyControlCenter {
	public ConcreteAllyControlCenter(String allyName) {
		System.out.println(allyName + "ս���齨�ɹ���");
		System.out.println("----------------------------");
		this.allyName = allyName;
	}

	//ʵ��֪ͨ����
	public void notifyObserver(String name) {
		System.out.println(this.allyName + "ս�ӽ���֪ͨ������" + name + "���ܵ��˹�����");
		//�����۲��߼��ϣ�����ÿһ�����ѣ��Լ����⣩��֧Ԯ����
		for(Object obs : players) {
			if (!((Observer)obs).getName().equalsIgnoreCase(name)) {
				((Observer)obs).help();
			}
		}
	}
}
