//designpatterns.abstractfactory.SkinFactory.java
package com.mumu.abstractfactory;

public interface SkinFactory {
	public Button createButton();
	public TextField createTextField();
	public ComboBox createComboBox();
}
