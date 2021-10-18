//designpatterns.command.calculator.Client.java
package com.mumu.command.calculator;

public class Client {
	public static void main(String args[]) {
		CalculatorForm form = new CalculatorForm();
		AbstractCommand command;
		command = new AddCommand();
		form.setCommand(command); //������ע���������

		form.compute(10);
		form.compute(5);
		form.compute(10);
		form.undo();   //����
	}
}
