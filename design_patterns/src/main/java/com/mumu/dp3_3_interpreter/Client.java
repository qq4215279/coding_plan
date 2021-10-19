//designpatterns.interpreter.Client.java
package com.mumu.dp3_3_interpreter;

public class Client {
	public static void main(String args[]) {
		String instruction = "down run 10 and left move 20";
		InstructionHandler handler = new InstructionHandler();
		handler.handle(instruction);
		String outString;
		outString = handler.output();
		System.out.println(outString);
	}
}
