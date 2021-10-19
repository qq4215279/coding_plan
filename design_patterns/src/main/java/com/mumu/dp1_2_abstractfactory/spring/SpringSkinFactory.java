package com.mumu.dp1_2_abstractfactory.spring;

import com.mumu.dp1_2_abstractfactory.core.Button;
import com.mumu.dp1_2_abstractfactory.core.ComboBox;
import com.mumu.dp1_2_abstractfactory.core.SkinFactory;
import com.mumu.dp1_2_abstractfactory.core.TextField;

public class SpringSkinFactory implements SkinFactory {
	public Button createButton() {
		return new SpringButton();
	}

	public TextField createTextField() {
		return new SpringTextField();
	}

	public ComboBox createComboBox() {
		return new SpringComboBox();
	}
}
