//designpatterns.adapter.CarController.java
package com.mumu.adapter;

//���������࣬�䵱Ŀ�������
public abstract class CarController {
	public void move() {
		System.out.println("��������ƶ���");
	}

	public abstract void phonate(); //��������
	public abstract void twinkle(); //�ƹ���˸
}
