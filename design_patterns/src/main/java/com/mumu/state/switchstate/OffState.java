//designpatterns.state.switchstate.OffState.java
package com.mumu.state.switchstate;

//�ر�״̬��
public class OffState extends SwitchState{
	public void on(Switch s) {
		System.out.println("�򿪣�");
		s.setState(Switch.getState("on"));
	}

	public void off(Switch s) {
		System.out.println("�Ѿ��رգ�");
	}
}
