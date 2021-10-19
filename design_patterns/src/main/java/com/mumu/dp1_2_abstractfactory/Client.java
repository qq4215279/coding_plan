package com.mumu.dp1_2_abstractfactory;

import com.mumu.dp1_2_abstractfactory.core.Button;
import com.mumu.dp1_2_abstractfactory.core.ComboBox;
import com.mumu.dp1_2_abstractfactory.core.SkinFactory;
import com.mumu.dp1_2_abstractfactory.core.TextField;

public class Client {
	public static void main(String args[]) {
		// 使用抽象层定义
		SkinFactory factory;
		Button bt;
		TextField tf;
		ComboBox cb;
		factory = (SkinFactory)XMLUtil.getBean();
		bt = factory.createButton();
		tf = factory.createTextField();
		cb = factory.createComboBox();
		bt.display();
		tf.display();
		cb.display();
	}
}
