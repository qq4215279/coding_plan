//designpatterns.templatemethod.hookmethod.Client.java
package com.mumu.dp3_10_templatemethod.hookmethod;

public class Client {
	public static void main(String args[]) {
		DataViewer dv;
		dv = new XMLDataViewer();
		dv.process();
	}
}
