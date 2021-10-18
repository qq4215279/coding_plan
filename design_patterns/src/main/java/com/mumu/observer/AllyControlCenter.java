//designpatterns.observer.AllyControlCenter.java
package com.mumu.observer;
import java.util.*;
import java.util.Observer;

//ս�ӿ��������ࣺĿ����
public abstract class AllyControlCenter {
	protected String allyName; //ս������
	protected ArrayList<java.util.Observer> players = new ArrayList<java.util.Observer>(); //����һ���������ڴ洢ս�ӳ�Ա

	public void setAllyName(String allyName) {
		this.allyName = allyName;
	}

	public String getAllyName() {
		return this.allyName;
	}

	//ע�᷽��
	public void join(java.util.Observer obs) {
		System.out.println(obs.getName() + "����" + this.allyName + "ս�ӣ�");
		players.add(obs);
	}

	//ע������
	public void quit(Observer obs) {
		System.out.println(obs.getName() + "�˳�" + this.allyName + "ս�ӣ�");
		players.remove(obs);
	}

	//��������֪ͨ����
	public abstract void notifyObserver(String name);
}
