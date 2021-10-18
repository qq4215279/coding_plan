//designpatterns.command.HelpCommand.java
package com.mumu.command;

public class HelpCommand extends Command{
	private DisplayHelpClass hcObj;   //ά�ֶ���������ߵ�����

	public HelpCommand() {
		hcObj = new DisplayHelpClass();
	}

	//����ִ�з�������������������ߵ�ҵ�񷽷�
	public void execute() {
		hcObj.display();
	}
}
