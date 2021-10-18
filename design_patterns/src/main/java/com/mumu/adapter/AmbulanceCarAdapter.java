//designpatterns.adapter.AmbulanceCarAdapter.java
package com.mumu.adapter;

//�Ȼ������������䵱������
public class AmbulanceCarAdapter extends CarController {
	private AmbulanceSound sound;  //����������AmbulanceSound����
	private AmbulanceLamp lamp;    //����������AmbulanceLamp����

	public AmbulanceCarAdapter() {
		sound = new AmbulanceSound();
		lamp = new AmbulanceLamp();
	}

	//�����Ȼ�������
	public void phonate() {
		sound.alarmSound();  //������������AmbulanceSound�ķ���
	}

	//���־Ȼ�������˸
	public void twinkle() {
		lamp.alarmLamp();   //������������AmbulanceLamp�ķ���
	}
}
