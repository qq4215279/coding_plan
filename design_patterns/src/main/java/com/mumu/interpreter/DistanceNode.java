//designpatterns.interpreter.DistanceNode.java
package com.mumu.interpreter;

//������ͣ��ս�����ʽ
public class DistanceNode extends AbstractNode {
	private String distance;

	public DistanceNode(String distance) {
		this.distance = distance;
	}

	//������ʽ�Ľ��Ͳ���
	public String interpret() {
		return this.distance;
	}
}
