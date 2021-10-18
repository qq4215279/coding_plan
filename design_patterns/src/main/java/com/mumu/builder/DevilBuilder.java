//designpatterns.builder.DevilBuilder.java
package com.mumu.builder;

public class DevilBuilder extends ActorBuilder {
	public void buildType() {
		actor.setType("��ħ");
	}

	public void buildSex() {
		actor.setSex("��");
	}

	public void buildFace() {
		actor.setFace("��ª");
	}

	public void buildCostume() {
		actor.setCostume("����");
	}

	public void buildHairstyle() {
		actor.setHairstyle("��ͷ");
	}
}
