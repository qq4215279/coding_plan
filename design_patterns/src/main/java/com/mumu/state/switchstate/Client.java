//designpatterns.state.switchstate.Client.java
package com.mumu.state.switchstate;

public class Client {
	public static void main(String args[]) {
		Switch s1,s2;
		s1=new Switch("����1");
		s2=new Switch("����2");

		s1.on();
		s2.on();
		s1.off();
		s2.off();
		s2.on();
		s1.on();
	}
}
