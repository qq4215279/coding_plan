//designpatterns.mediator.Component.java
package com.mumu.mediator;

//��������ࣺ����ͬ����
public abstract class Component {
	protected Mediator mediator;

	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	//ת������
	public void changed() {
		mediator.componentChanged(this);
	}

	public abstract void update();
}
